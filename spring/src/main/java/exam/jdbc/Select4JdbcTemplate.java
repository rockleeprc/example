package exam.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.pojo.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jdbc/applicationContext-jdbc.xml")
public class Select4JdbcTemplate {
	@Autowired
	private JdbcTemplate jdbc;

	/**
	 * select 查询一条数据，通过BeanPropertyRowMapper返回entity
	 */
	@Test
	public void select4ObjectByBeanPropertyRowMapper() {
		String sql = "select id,name,age from person where id=?";
		Object[] params = new Object[] { 1 };
		RowMapper<Person> rowMapper = new BeanPropertyRowMapper<Person>(Person.class);
		Person person = jdbc.queryForObject(sql, rowMapper, params);
		System.out.println(person);
	}

	/**
	 * select 查询一条数据，通过RowMapper返回对象
	 */
	@Test
	public void select4ObjectByRowMapper() {
		String sql = "select id,name,age from person where id=?";
		Object[] params = new Object[] { 2 };
		Person per = jdbc.queryForObject(sql, params, new RowMapper<Person>() {

			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person p = new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
				return p;
			}
		});
		System.out.println(per);

	}

	/**
	 * RowMapper：总是返回List<?>，结果集较大时容易OutOfMemery<br/>
	 * RowCallbackHandler：结果集较大时，可以把业务逻辑放在processRow()中处理
	 */
	@Test
	public void selectByRowMapper() {
		String sql = "select id,name,age from person";
		// Object[] params = new Object[] { 2 };
		// jdbc.query(sql, params, new RowMapper<Person>() {});
		List<Person> pers = jdbc.query(sql, new RowMapper<Person>() {

			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person p = new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
				return p;
			}
		});
		for (Person p : pers) {
			System.out.println(p);
		}
	}

	/**
	 * select语句，返回多条数据数据
	 */
	@Test
	public void selectAllByBeanPropertyRowMapper() {
		String sql = "select id,name,age from person";
		RowMapper<Person> rowMapper = new BeanPropertyRowMapper<Person>(Person.class);
		List<Person> list = jdbc.query(sql, rowMapper);
		for (Person per : list) {
			System.out.println(per);
		}
	}

	/**
	 * select语句，返回多条数据数据
	 */
	@Test
	public void selectAll() {
		String sql = "select id,name,age from person";
		final List<Person> pers = new ArrayList<Person>();

		jdbc.query(sql, new RowCallbackHandler() {
			/**
			 * Spring jdbc会遍历结果集，对每条数据调用该方法
			 * @param rs
			 * @throws SQLException
			 */
			public void processRow(ResultSet rs) throws SQLException {
				Person per = new Person();
				per.setId(rs.getInt("id"));
				per.setName(rs.getString("name"));
				per.setAge(rs.getInt("age"));
				pers.add(per);
			}
		});
		for (Person p : pers) {
			System.out.println(p);
		}
	}

	/**
	 * 查询单列值
	 */
	@Test
	public void select4column() {
		String sql = "select age from person where id=?";
		Integer age = jdbc.queryForObject(sql, new Object[] { 1 }, Integer.class);
		System.out.println(age);
	}

	/**
	 * select语句，返回单条数据
	 */
	@Test
	public void selectByID() {
		String sql = "select id,name,age from person where id=?";
		Object[] params = new Object[] { 2 };
		final Person per = new Person();
		jdbc.query(sql, params, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				per.setId(rs.getInt("id"));
				per.setName(rs.getString("name"));
				per.setAge(rs.getInt("age"));
			}

		});
		System.out.println(per);
	}

	/**
	 * 测试环境
	 */
	@Test
	public void test() {
		System.out.println(jdbc);
	}
}
