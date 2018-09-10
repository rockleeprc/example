package exam.mybatis.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import exam.mybatis.mapper.UserMapper;
import exam.mybatis.model.User;

public class PluginTest {
	@Test
	public void queryLimitPlugin() throws IOException {
		String resource = "mybatis/mybatis-config-plugin.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			// 业务操作
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User params =new User();
			params.setAddress("BJ");
			List<User> list = userMapper.selectToWhere(params);
			System.err.println(list);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
