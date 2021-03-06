package exam.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.pojo.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jdbc/applicationContext-jdbc.xml")
public class Insert4JdbcTemplate {
	@Autowired
	private JdbcTemplate jdbc;

	/**
	 * 批量insert
	 */
	@Test
	public void batchInsert() {
		final List<Person> pers = new ArrayList<Person>();
		for (int i = 0; i < 10; i++) {
			Person p = new Person("name_" + i, i);
			pers.add(p);
		}
		final String sql = "insert into person (name,age) values (?,?)";
		// 一次性提交所有集合数据
		jdbc.batchUpdate(sql, new BatchPreparedStatementSetter() {

			/**
			 * @param ps
			 * @param i 集合的索引值
			 * @throws SQLException
			 */
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, pers.get(i).getName());
				ps.setInt(2, pers.get(i).getAge());
			}

			public int getBatchSize() {
				return pers.size();
			}
		});
	}

	/**
	 * insert语句，返回数数据库主键id
	 */
	@Test
	public void insertReturnID() {
		final String sql = "insert into person (name,age) values (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, "f");
				ps.setInt(2, 18);
				return ps;
			}
		}, keyHolder);
		System.out.println(keyHolder.getKey());
	}

	/**
	 * insert语句，参数采用PreparedStatement实例设置
	 */
	@Test
	public void insertParams4InnerClassCallback() {
		final String sql = "insert into person (name,age) values (?,?)";
		/**
		 * 内部类创建PreparedStatement实例设置参数
		 */
		jdbc.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, "e");
				ps.setInt(2, 18);
				return ps;
			}
		});
	}

	/**
	 * insert语句，参数采用接口方法回调
	 */
	@Test
	public void insertParams4Callback() {
		String sql = "insert into person (name,age) values (?,?)";
		/**
		 * 使用PreparedStatementSetter借口回调方式设置参数<br/>
		 * 参数从1开始
		 */
		jdbc.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, "d");
				ps.setInt(2, 18);
			}
		});
	}

	/**
	 * insert语句
	 */
	@Test
	public void insert() {
		String sql = "insert into person (name,age) values (?,?)";
		Object[] params = new Object[] { "c", 32 };
		// 声明参数类型保证类型安全
		int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
		/**
		 * 参数类型通过PreparedStatement推测<br/>
		 * jdbc.update(sql, params);
		 */
		int update = jdbc.update(sql, params, types);
	}

	/**
	 * 测试环境
	 */
	@Test
	public void test() {
		System.out.println(jdbc);
	}
}
