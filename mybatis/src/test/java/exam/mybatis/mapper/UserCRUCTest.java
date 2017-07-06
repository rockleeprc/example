package exam.mybatis.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import exam.mybatis.mapper.UserMapper;
import exam.mybatis.model.User;
import exam.mybatis.util.SqlSessionFactoryUtil;

/**
 * 基本CRUD操作
 * 
 * @author mint
 *
 */
public class UserCRUCTest {

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
		u.setUserName("孙艺珍");
		u.setUserAge("40");
		u.setUserAddress("韩国");
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
		u.setUserName("Hadoop");
		u.setUserAge("14");
		u.setUserAddress("TW");
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
