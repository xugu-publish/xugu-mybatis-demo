package handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.log4j.Logger;

import mapper.DatabaseDmlTest;

/**
 * 1.MappedJdbcTypes定义的是JdbcType类型，这里的类型不可自己随意定义，必须要是枚举类org.apache.ibatis.type.JdbcType所枚举的数据类型。
 * 2.MappedTypes定义的是JavaType的数据类型，描述了哪些Java类型可被拦截。
 * 3.在我们启用了我们自定义的这个TypeHandler之后，数据的读写都会被这个类所过滤
 * 4.在setNonNullParameter方法中，我们重新定义要写往数据库的数据。 
 * 5.在另外三个方法中我们将从数据库读出的数据类型进行转换。
 * 字符串特殊化处理
 * @author xugu-publish
 * @date 2019/01/24
 * @since 1.8
 */
@MappedTypes({ String.class })
@MappedJdbcTypes({ JdbcType.VARCHAR })
public class StringTypeHandler extends BaseTypeHandler<String> {

	private Logger logger = Logger.getLogger(DatabaseDmlTest.class);

	/**
	 * 把Java类型参数转换为对应的数据库类型
	 * 
	 * @param ps
	 *            当前的PreparedStatement对象
	 * @param i
	 *            当前参数位置
	 * @param parameter
	 *            当前参数的Java对象
	 * @param jdbcType
	 *            当前参数的数据库类型
	 * @throws SQLException
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, this.getString(parameter));
	}

	/**
	 * 获取数据结果集时把数据库类型转换为对应的Java类型
	 * 
	 * @param rs
	 *            当前的结果集
	 * @param columnName
	 *            当前的字段名称
	 * @return 转换后的Java对象
	 * @throws SQLException
	 */
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return this.getString(rs.getString(columnName));
	}

	/**
	 * 通过字段位置获取字段数据时把数据库类型转换为对应的Java类型
	 * 
	 * @param rs
	 *            当前的结果集
	 * @param columnIndex
	 *            当前字段的位置
	 * @return 转换后的Java对象
	 * @throws SQLException
	 */
	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return this.getString(rs.getString(columnIndex));
	}

	/**
	 * 调用存储过程后把数据库类型的数据转换为对应的Java类型
	 * 
	 * @param cs
	 *            当前的CallableStatement执行后的CallableStatement
	 * @param columnIndex
	 *            当前输出参数的位置
	 * @return String[]
	 * @throws SQLException
	 */
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return this.getString(cs.getString(columnIndex));
	}

	/**
	 * 字符串特殊化处理(空串、NULL、特殊字符)
	 * 
	 * @param columnValue
	 * @return String[]
	 */
	private String getString(String columnValue) {
		String ret;
		if (columnValue == null) {
			ret = null;
		} else {
			ret = columnValue;
		}
		
		logger.info("字符串特殊化处理[处理前，处理后]：" + columnValue + ", " + ret);
		return ret;
	}
}
