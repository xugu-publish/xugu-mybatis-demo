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
 * 字符串处理
 * @author xugu-publish
 * @date 2019/01/24
 * @since 1.8
 */
@MappedTypes({String[].class})  
@MappedJdbcTypes({JdbcType.VARCHAR})  
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {
	
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
	public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType)
			throws SQLException {
		// 由于BaseTypeHandler中已经把parameter为null的情况做了处理，所以这里我们就不用在判断parameter是否为空，直接用就可以了
		StringBuffer result = new StringBuffer();
		for (String value : parameter) {
			result.append(value).append(",");
		}
		result.deleteCharAt(result.length() - 1);
		ps.setString(i, result.toString());
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
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return this.getStringArray(rs.getString(columnName));
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
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return this.getStringArray(rs.getString(columnIndex));
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
	public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return this.getStringArray(cs.getString(columnIndex));
	}

	/**
	 * 将”string1,string2"转化为数组对象
	 * 
	 * @param columnValue
	 * @return String[]
	 */
	private String[] getStringArray(String columnValue) {
		String[] ret;
		if (columnValue == null) {
			ret = null;
		} else {
			ret = columnValue.split(",");
		}
		logger.info("字符串数组特殊化处理[处理前，处理后]：[" + columnValue + "], [" + ret + "]");
		return ret;
	}
}
