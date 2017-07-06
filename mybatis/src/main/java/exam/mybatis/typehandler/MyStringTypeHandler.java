package exam.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

//定义java类型
@MappedTypes({ String.class })
//定义jdbc类型
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyStringTypeHandler implements TypeHandler<String> {

	private Logger log = Logger.getLogger(MyStringTypeHandler.class.getName());

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		log.info("自定义MyStringTypeHandler.setParameter");
		ps.setString(i, parameter);
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		log.info("自定义MyStringTypeHandler.getResult");
		return rs.getString(columnName);
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		log.info("自定义MyStringTypeHandler.getResult");
		return rs.getString(columnIndex);
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		log.info("自定义MyStringTypeHandler.getResult");
		return cs.getString(columnIndex);
	}

}
