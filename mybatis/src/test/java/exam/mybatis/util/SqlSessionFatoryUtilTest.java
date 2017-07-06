package exam.mybatis.util;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import exam.mybatis.util.SqlSessionFactoryUtil;

public class SqlSessionFatoryUtilTest {
	@Test
	public void test() {
		SqlSession session = SqlSessionFactoryUtil.openSqlSession();
		System.out.println(session);
	}
}
