package exam.mybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {

	private static SqlSessionFactory sqlSessionFactory = null;
	// 线程锁
	private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

	/**
	 * 构造私有化
	 */
	private SqlSessionFactoryUtil() {
	}

	/**
	 * 构建SqlSessionFactory
	 * 
	 * @return
	 */
	public static SqlSessionFactory initSqlSessionFactory() {
		String resource = "mybatis/mybatis-config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		synchronized (CLASS_LOCK) {
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
				Logger.getLogger(SqlSessionFactoryUtil.class.getName()).info("sqlSessionFactory实例化");
			}
		}
		return sqlSessionFactory;
	}

	/**
	 * 打开SqlSession
	 * 
	 * @return
	 */
	public static SqlSession openSqlSession() {
		if (sqlSessionFactory == null) {
			initSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
	}
}