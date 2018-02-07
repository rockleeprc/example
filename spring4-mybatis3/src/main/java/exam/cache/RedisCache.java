package exam.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisCache implements Cache // 实现类
{
	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private static JedisConnectionFactory jedisConnectionFactory;

	private final String id;

	private final long ONE_SECOND = 60L * 1L;
	private final long ONE_HOUR = ONE_SECOND * 60L;
	private final Expiration expiration = Expiration.seconds(ONE_HOUR*2);

	/**
	 * The {@code ReadWriteLock}.
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug("MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public void clear() {
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection(); // 连接清除数据
			connection.flushDb();
			connection.flushAll();
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		Object result = null;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(); // 借用spring_data_redis.jar中的JdkSerializationRedisSerializer.class
			result = serializer.deserialize(connection.get(serializer.serialize(key))); // 利用其反序列化方法获取值
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		int result = 0;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		RedisConnection connection = null;
		try {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(); // 借用spring_data_redis.jar中的JdkSerializationRedisSerializer.class
			//connection.set(serializer.serialize(key), serializer.serialize(value)); // 利用其序列化方法将数据写入redis服务的缓存中
			connection.set(serializer.serialize(key), serializer.serialize(value), expiration, SetOption.UPSERT);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public Object removeObject(Object key) {
		RedisConnection connection = null;
		Object result = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}

}