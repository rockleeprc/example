package exam.mybatis.typehandler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import exam.mybatis.mapper.UserMapper;
import exam.mybatis.model.User;

public class TypeHandlerTest {
	/**
	 * 配置文件方式创建SqlSessionFactory
	 * 
	 * @throws IOException
	 */
	@Test
	public void myStringTypeHandler() throws IOException {
		String resource = "mybatis/mybatis-config-typeHandler.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			// 业务操作
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User user = userMapper.selectByID(1);
			System.err.println(user);

			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}
}
