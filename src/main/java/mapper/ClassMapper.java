package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import entity.ClassEntity;

/**
 * MyBatis数据库访问接口定义(XML映射方式)
 * 
 * @author xugu-publish
 * @date 2019/01/22
 * @since 1.8
 */
public interface ClassMapper {

	/**
	 * 根据ID值获取对象
	 * @param classId
	 * @return ClassEntity
	 */
	public ClassEntity getClassById(@Param("classId") int classId);

	/**
	 * 获取所有对象
	 * @return List
	 */
	public List<ClassEntity> getClassByList();

	/**
	 * 添加对象
	 * @param entity
	 * @return int
	 */
	public int addClass(ClassEntity entity);

	/**
	 * 更新对象，返回更新条数
	 * @param entity
	 * @return int
	 */
	public int updateClass(ClassEntity entity);

	/**
	 * 更具id值删除对象，返回删除条数
	 * @param classId
	 * @return int
	 */
	public int deleteClass(@Param("classId") int classId);

	/**
	 * 情况对象表
	 */
	public void truncateClass();
}
