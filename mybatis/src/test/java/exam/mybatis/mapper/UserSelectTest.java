package exam.mybatis.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import exam.mybatis.model.User;
import exam.mybatis.util.SqlSessionFactoryUtil;

public class UserSelectTest {
	@Test
	public void updateById() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User u = new User();
			u.setId(4);
			u.setSex(1);
//			u.setName("tom");
			u.setAddress("NY");
			int result = userMapper.updateById(u);
			System.out.println(result);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@Test
	public void selectToWhwere() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User u = new User();
			u.setName("tom");
			u.setAddress("SH");
			List<User> list = userMapper.selectToWhere(u);
			System.out.println(list);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@Test
	public void paramsToBean() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User u = new User();
			u.setName("tom");
			u.setAddress("SH");
			List<User> list = userMapper.paramsToBean(u);
			System.out.println(list);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Test
	public void paramsToAnnotation() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			List<User> list = userMapper.paramsToAnnotation("java", "BJ");
			System.out.println(list);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Test
	public void paramsToMap() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			Map<String, String> params = new HashMap<>();
			params.put("userName", "liyan");
			params.put("userAddress", "BJ");
			List<User> list = userMapper.paramsToMap(params);
			System.out.println(list);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Test
	public void countLikeName() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			int count = userMapper.countLikeName("孙");
			System.out.println(count);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
}
