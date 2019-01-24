package datasource;

import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.DataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * druid 连接池
 * 
 * @author wsy
 * @date 2019/01/23
 * @since 1.8
 */
public class DruidDataSourceFactory implements DataSourceFactory {
	/**
	 * 连接池属性
	 */
	private Properties props;

	private String initialSize = "initialSize";
	private String minIdle = "minIdle";
	private String maxActive = "maxActive";
	private String maxWait = "maxWait";
	private String timeBetweenEvictionRunsMillis = "timeBetweenEvictionRunsMillis";
	private String minEvictableIdleTimeMillis = "minEvictableIdleTimeMillis";
	private String validationQuery = "validationQuery";
	private String testWhileIdle = "testWhileIdle";
	private String testOnBorrow = "testOnBorrow";
	private String testOnReturn = "testOnReturn";
	private String poolPreparedStatements = "poolPreparedStatements";
	private String filters = "filters";
	private String connectionProperties = "connectionProperties";
	private String maxPoolPreparedStatementPerConnectionSize = "maxPoolPreparedStatementPerConnectionSize";

	@Override
	public void setProperties(Properties props) {
		this.props = props;
	}

	/**
	 * 初始化druid数据源
	 */
	@Override
	public DataSource getDataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDriverClassName(this.props.getProperty("driver"));
		datasource.setUrl(this.props.getProperty("url"));
		datasource.setUsername(this.props.getProperty("username"));
		datasource.setPassword(this.props.getProperty("password"));
		try {
			if (this.props.containsKey(initialSize)) {
				datasource.setInitialSize(Integer.parseInt(this.props.getProperty("initialSize")));
			}
			if (this.props.containsKey(minIdle)) {
				datasource.setMinIdle(Integer.parseInt(this.props.getProperty("minIdle")));
			}
			if (this.props.containsKey(maxActive)) {
				datasource.setMaxActive(Integer.parseInt(this.props.getProperty("maxActive")));
			}
			if (this.props.containsKey(maxWait)) {
				datasource.setMaxWait(Long.parseLong(this.props.getProperty("maxWait")));
			}
			if (this.props.containsKey(timeBetweenEvictionRunsMillis)) {
				datasource.setTimeBetweenEvictionRunsMillis(
						Long.parseLong(this.props.getProperty("timeBetweenEvictionRunsMillis")));
			}
			if (this.props.containsKey(minEvictableIdleTimeMillis)) {
				datasource.setMinEvictableIdleTimeMillis(
						Long.parseLong(this.props.getProperty("minEvictableIdleTimeMillis")));
			}
			if (this.props.containsKey(validationQuery)) {
				datasource.setValidationQuery(this.props.getProperty("validationQuery"));
			}
			if (this.props.containsKey(testWhileIdle)) {
				datasource.setTestWhileIdle(Boolean.parseBoolean(this.props.getProperty("testWhileIdle")));
			}
			if (this.props.containsKey(testOnBorrow)) {
				datasource.setTestOnBorrow(Boolean.parseBoolean(this.props.getProperty("testOnBorrow")));
			}
			if (this.props.containsKey(testOnReturn)) {
				datasource.setTestOnBorrow(Boolean.parseBoolean(this.props.getProperty("testOnReturn")));
			}
			if (this.props.containsKey(poolPreparedStatements)) {
				datasource.setPoolPreparedStatements(
						Boolean.parseBoolean(this.props.getProperty("poolPreparedStatements")));
			}
			if (this.props.containsKey(maxPoolPreparedStatementPerConnectionSize)) {
				datasource.setMaxPoolPreparedStatementPerConnectionSize(
						Integer.parseInt(this.props.getProperty("maxPoolPreparedStatementPerConnectionSize")));
			}
			if (this.props.containsKey(filters)) {
				datasource.setFilters(this.props.getProperty("filters"));
			}
			if (this.props.containsKey(connectionProperties)) {
				datasource.setConnectionProperties(this.props.getProperty("connectionProperties"));
			}
			datasource.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 其他配置可以根据MyBatis主配置文件进行配置
		return datasource;
	}
}
