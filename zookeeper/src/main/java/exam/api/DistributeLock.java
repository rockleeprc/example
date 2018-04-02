package exam.api;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class DistributeLock {
	private static final String HOSTS = "hdp01:2181,hdp02:2181,hdp03:2181";
	private static final int TIMEOUT = 10000;
	// latch.await()方法执行时，方法所在的线程会等待，当latch的count减为0时，才会唤醒等待的线程

	private static final String LOCK_NODE = "/lock";
	private static final String separator = "/";
	private final static byte[] data = { 1, 2 };

	private  ZooKeeper zkClient = null;
	private String lockID;
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public DistributeLock(ZooKeeper zkClient) {
		this.zkClient = zkClient;
	}

	public boolean lock() {
		try {
			lockID = zkClient.create(LOCK_NODE + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);

			System.out.println(Thread.currentThread().getName() + "->成功创建了lock节点[" + lockID + "], 开始去竞争锁");

			List<String> childrenNodes = zkClient.getChildren(LOCK_NODE, true);// 获取根节点下的所有子节点
			SortedSet<String> sortedSet = new TreeSet<String>();
			for (String children : childrenNodes) {
				sortedSet.add(LOCK_NODE + "/" + children);
			}
			String first = sortedSet.first();
			if (lockID.equals(first)) {
				System.out.println(Thread.currentThread().getName() + "->成功获得锁，lock节点为:[" + lockID + "]");
				return true;
			}
			SortedSet<String> lessThanLockId = sortedSet.headSet(lockID);
			if (!lessThanLockId.isEmpty()) {
				String prevLockID = lessThanLockId.last();
				zkClient.exists(prevLockID, new LockWatcher(countDownLatch));
				countDownLatch.await(TIMEOUT, TimeUnit.MILLISECONDS);
				System.out.println(Thread.currentThread().getName() + " 成功获取锁：[" + lockID + "]");
			}
			return true;

		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean unlock() {
		System.out.println(Thread.currentThread().getName() + "->开始释放锁:[" + lockID + "]");
		try {
			zkClient.delete(lockID, -1);
			System.out.println("节点[" + lockID + "]成功被删除");
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		final ZooKeeper zkClient = init();
		final CountDownLatch latch = new CountDownLatch(10);
		final Random random = new Random();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					DistributeLock lock = null;
					try {
						lock = new DistributeLock(zkClient);
						latch.countDown();
						latch.await();
						lock.lock();
						//Thread.sleep(random.nextInt(500));
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						
						if (lock != null) {
							lock.unlock();
						}
					}
				}
			}).start();
		}
	}

	public static ZooKeeper init() throws IOException, InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		ZooKeeper zkClient = new ZooKeeper(HOSTS, TIMEOUT, new Watcher() {
			// 事件监听回调方法
			public void process(WatchedEvent event) {
				System.out.println("getType:" + event.getType());
				System.out.println("getPath:" + event.getPath());
				System.out.println("getState:" + event.getState());
				if (latch.getCount() > 0 && event.getState() == KeeperState.SyncConnected) {
					System.out.println("KeeperState is SyncConnected");
					System.out.println("--------------");
					latch.countDown();
				}
			}
		});
		latch.await();
		return zkClient;
	}

}