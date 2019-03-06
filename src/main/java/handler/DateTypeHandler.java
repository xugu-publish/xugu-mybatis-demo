package handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * 1.MappedJdbcTypes定义的是JdbcType类型，这里的类型不可自己随意定义，必须要是枚举类org.apache.ibatis.type.JdbcType所枚举的数据类型。
 * 2.MappedTypes定义的是JavaType的数据类型，描述了哪些Java类型可被拦截。
 * 3.在我们启用了我们自定义的这个TypeHandler之后，数据的读写都会被这个类所过滤
 * 4.在setNonNullParameter方法中，我们重新定义要写往数据库的数据。 
 * 5.在另外三个方法中我们将从数据库读出的数据类型进行转换。
 * 
 * @author xugu-publish
 * @date 2019/01/24
 * @since 1.8
 */
@MappedJdbcTypes({ JdbcType.VARCHAR })
@MappedTypes({ Date.class })
public class DateTypeHandler extends BaseTypeHandler<Date> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, String.valueOf(parameter.getTime()));
	}

	@Override
	public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return new Date(rs.getLong(columnName));
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return new Date(rs.getLong(columnIndex));
	}

	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return cs.getDate(columnIndex);
	}
}
