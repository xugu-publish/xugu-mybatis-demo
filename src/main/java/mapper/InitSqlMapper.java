package mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 使用MyBatis执行DDL语句
 * 
 * @author wsy
 * @date 2019/01/22
 * @since 1.8
 */
public interface InitSqlMapper {

	/**
	 * 删除表
	 * @param tableName 表名
	 */
	public void dropTabel(@Param("tableName") String tableName);

	/**
	 * 创建表 
	 * @param tableName
	 */
	public void createTabel(@Param("tableName") String tableName);

	/**
	 * 为表添加注释 
	 * @param tableName
	 */
	public void commentTabel(@Param("tableName") String tableName);

	/**
	 * 创建表索引
	 * @param tableName
	 */
	public void createTabelIndex(@Param("tableName") String tableName);

	/**
	 * 添加表约束 
	 * @param tableName
	 */
	public void addTabelConstraint(@Param("tableName") String tableName);
}
