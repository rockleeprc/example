package exam.mybatis.mapperproxy;

import org.junit.Test;

import exam.mybatis.mapper.UserMapper;
import exam.mybatis.model.User;

public class TestMyMapperProxy {

	@Test
	public void testMyMapperProxy() {
		MySqlSession session = new MySqlSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User id = mapper.selectByID(1);
		System.out.println(id);
	}
}
