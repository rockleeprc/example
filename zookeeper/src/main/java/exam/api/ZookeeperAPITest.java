package exam.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperAPITest {
	private static final String HOSTS = "hdp01:2181,hdp02:2181,hdp03:2181";
	private static final int TIMEOUT = 10 * 1000;
	private static final CountDownLatch LATCH = new CountDownLatch(1);
	private static ZooKeeper zkClient = null;

	@Test
	public void testExist() throws KeeperException, InterruptedException {
		Stat stat = zkClient.exists("/none", true);
		// 节点不存在Stat对象为null
		System.out.println(stat);
	}

	@Test
	public void testDelete() throws InterruptedException, KeeperException {
		// -1 删除任何节点的版本数据
		zkClient.delete("/eclipse/ide2", -1);
	}

	@Test
	public void testSetData() throws KeeperException, InterruptedException, IOException {
		// -1 任何节点的版本数据
		zkClient.setData("/eclipse/ide1", "i'm a ide".getBytes(), -1);
		// System.in.read();
	}

	@Test
	public void testGetData() throws KeeperException, InterruptedException, UnsupportedEncodingException {
		// watch==true为使用new ZooKeeper()构造方法中的Watcher
		byte[] bytes = zkClient.getData("/eclipse", true, null);
		System.out.println(new String(bytes, "UTF-8"));
	}

	@Test
	public void testGetChildren() throws KeeperException, InterruptedException, IOException {
		List<String> children = zkClient.getChildren("/eclipse", true);
		for (String string : children) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testCreateSequential() throws KeeperException, InterruptedException, IOException {
		zkClient.getChildren("/eclipse", true);// 注册Watcher
		//只有创建SEQUENTIAL节点时才能用“xxx/”，自动创建带序号的节点
		zkClient.create("/eclipse/", "ide1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		zkClient.create("/eclipse/", "ide2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		System.in.read();
	}

	@Test
	public void testCreateChildren() throws KeeperException, InterruptedException, IOException {
		zkClient.getChildren("/eclipse", true);// 注册Watcher
		zkClient.create("/eclipse/ide1", "ide1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zkClient.create("/eclipse/ide2", "ide2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.in.read();
	}

	@Test
	public void testCreate() throws KeeperException, InterruptedException {
		// 参数1：要创建的节点的路径
		// 参数2：节点的数据
		// 参数3：节点的权限
		// 参数4：节点的类型
		String result = zkClient.create("/eclipse", "helloZK".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(result);
	}

	@Before
	public void init() throws IOException, InterruptedException {
		zkClient = new ZooKeeper(HOSTS, TIMEOUT, new Watcher() {
			// 事件监听回调方法
			public void process(WatchedEvent event) {
				System.out.println("getType:" + event.getType());
				System.out.println("getPath:" + event.getPath());
				System.out.println("getState:" + event.getState());
				if (LATCH.getCount() > 0 && event.getState() == KeeperState.SyncConnected) {
					System.out.println("KeeperState is SyncConnected");
					System.out.println("--------------");
					LATCH.countDown();
				}
			}
		});
		// latch.await()方法执行时，方法所在的线程会等待，当latch的count减为0时，才会唤醒等待的线程
		LATCH.await();
	}

	@After
	public void destroy() throws InterruptedException {
		zkClient.close();
		System.out.println("zkClient is closed");
	}
}
