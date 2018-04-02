package exam.curator;

import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

public class CuratorEventTest {

	private static CuratorFramework curator;
	private static final String HOSTS = "hdp01:2181,hdp02:2181,hdp03:2181";
	private static final int TIMEOUT = 10 * 1000;
	private static final int MAX_RETRIES = 3;

	/**
	 * PathChildrenCache+NodeCache 的合体（监视路径下的创建、更新、删除事件），
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTreeCache() throws Exception {
		TreeCache cache = new TreeCache(curator, "/curator");
		cache.start();

		cache.getListenable().addListener(new TreeCacheListener() {

			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				switch (event.getType()) {
				case NODE_ADDED:
					System.out.println("增加节点");
					System.out.println(new String(event.getData().getData()));
					break;
				case NODE_REMOVED:
					System.out.println("删除节点");
					System.out.println(new String(event.getData().getData()));
					break;
				case NODE_UPDATED:
					System.out.println("更新节点");
					System.out.println(new String(event.getData().getData()));
					break;
				case INITIALIZED:
					System.out.println("初始化节点");
					System.out.println(event.getData());
					System.out.println(new String());
					break;
				default:
					break;
				}
			}
		});
		System.in.read();
	}

	/**
	 * 监视一个路径下子节点的创建、删除、节点数据更新
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPathChildrenCache() throws Exception {
		PathChildrenCache cache = new PathChildrenCache(curator, "/curator", true);
		cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

		cache.getListenable().addListener(new PathChildrenCacheListener() {

			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("增加子节点");
					System.out.println(new String(event.getData().getData()));
					break;
				case CHILD_REMOVED:
					System.out.println("删除子节点");
					System.out.println(new String(event.getData().getData()));
					break;
				case CHILD_UPDATED:
					System.out.println("更新子节点");
					System.out.println(new String(event.getData().getData()));
					break;
				default:
					break;
				}
			}
		});
		System.in.read();
	}

	/**
	 * 监视一个节点的创建、更新、删除
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNodeCache() throws Exception {
		final NodeCache nodeCache = new NodeCache(curator, "/curator", false);
		nodeCache.start(true);
		nodeCache.getListenable().addListener(new NodeCacheListener() {

			@Override
			public void nodeChanged() throws Exception {
				System.out.println(new String(nodeCache.getCurrentData().getData()));
			}
		});
		// curator.setData().forPath("/curator","菲菲".getBytes());
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
