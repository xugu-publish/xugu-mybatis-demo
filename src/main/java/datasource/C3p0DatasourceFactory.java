package datasource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * c3p0连接池
 * @author wsy
 * @date 2019/01/23
 * @since 1.8
 */
public class C3p0DatasourceFactory extends UnpooledDataSourceFactory
{
	/**
	 * 初始化数据源
	 */
	public C3p0DatasourceFactory(){
        this.dataSource=new ComboPooledDataSource();
    }
}
