package exam.mybatis.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import exam.mybatis.model.User;
import exam.mybatis.util.SqlSessionFactoryUtil;

/**
 * 基本CRUD操作
 * 
 * @author mint
 *
 */
public class UserCRUCTest {

	public static void main(String[] args) {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User user = userMapper.selectByID(1);
			System.out.println(user);
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
	public void selectById() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User user = userMapper.selectByID(1);
			System.out.println(user);
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
	public void delete() {
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.delete(5);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Test
	public void update() {
		User u = new User();
		u.setId(1);
		u.setName("孙艺珍");
		u.setAge("40");
		u.setAddress("韩国");
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.update(u);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Test
	public void insert() {
		User u = new User();
		u.setName("C");
		u.setAge("21");
		u.setAddress("HK");
		SqlSession session = null;
		try {
			session = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.insert(u);
			session.commit();
			System.out.println("User.id:" + u.getId());
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
}
