package exam.curator;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperCuratorAPITest {
	private static CuratorFramework curator;
	private static final String HOSTS = "hdp01:2181,hdp02:2181,hdp03:2181";
	private static final int TIMEOUT = 10 * 1000;
	private static final int MAX_RETRIES = 3;

	@Test
	public void testTransaction() throws Exception {
		Collection<CuratorTransactionResult> collection = curator.inTransaction().create()
				.forPath("/transaction", "commit".getBytes()).and().setData().forPath("/eclipse", "commit".getBytes())
				.and().commit();
		for (CuratorTransactionResult txResult : collection) {
			System.out.println(txResult.getForPath());
			System.out.println(txResult.getResultStat());
			System.out.println(txResult.getType());
			System.out.println("------");
		}
	}

	/**
	 * 删除节点数据
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		curator.delete().deletingChildrenIfNeeded().forPath("/consumers");
	}

	/**
	 * 更新节点数据
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetData() throws Exception {
		Stat stat = curator.setData().forPath("/eclipse", "helloZookeeper".getBytes());
		System.out.println(stat);
	}

	/**
	 * 获取子节点
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetChildren() throws Exception {
		List<String> list = curator.getChildren().forPath("/");
		for (String str : list) {
			System.out.println(str);
		}
	}

	/**
	 * 获取节点详细信息
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetStat() throws Exception {
		Stat stat = new Stat();
		byte[] bytes = curator.getData().storingStatIn(stat).forPath("/eclipse");
		System.out.println(new String(bytes));
		System.out.println(stat);
	}

	/**
	 * 获取节点内容
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetData() throws Exception {
		byte[] bytes = curator.getData().forPath("/eclipse");
		System.out.println(bytes.length);
		System.out.println(new String(bytes));
		System.in.read();
	}

	/**
	 * 异步操作
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateAsync() throws Exception {
		int cores = Runtime.getRuntime().availableProcessors() + 1;
		ExecutorService executor = Executors.newFixedThreadPool(cores);
		curator.create().creatingParentsIfNeeded().inBackground(new BackgroundCallback() {

			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				System.out.println(event.getType());
				System.out.println(event.getStat());
				System.out.println(event.getResultCode());
			}
		}, executor).forPath("/async", "threadpool".getBytes());

		System.in.read();
	}

	/**
	 * 创建子父节点
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
		String result = curator.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
				.forPath("/curator/c1/c2", "test".getBytes());
		System.out.println(result);
		System.in.read();
	}

	@Before
	public void init() {
		/**
		 * 重试策略：<br/>
		 * ExponentialBackoffRetry() 衰减重试 <br/>
		 * RetryNTimes 指定最大重试次数<br/>
		 * RetryOneTime 仅重试一次 <br/>
		 * RetryUnitilElapsed 一直重试知道规定的时间<br/>
		 */
		curator = CuratorFrameworkFactory.newClient(HOSTS, TIMEOUT, TIMEOUT,
				new ExponentialBackoffRetry(TIMEOUT, MAX_RETRIES));
		curator.start();
	}
}
