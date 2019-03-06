package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import entity.ClassEntity;

/**
 * MyBatis数据库访问接口定义(注解方式)
 * 
 * @author xugu-publish
 * @date 2019/01/22
 * @since 1.8
 */
@Mapper
public interface ClassAnnotationMapper {

	/**
	 * 根据ID值获取对象
	 * @param classId
	 * @return ClassEntity
	 */
	@Select("SELECT * FROM CLASS WHERE classId = #{classId}")
	public ClassEntity getClassById(@Param("classId") int classId);

	/**
	 * 获取所有对象
	 * @return List
	 */
	@Select("SELECT * FROM CLASS")
	public List<ClassEntity> getClassByList();

	/**
	 * 添加对象
	 * @param entity
	 * @return int
	 */
	@Insert("INSERT INTO CLASS(className) VALUES(#{className})")
	public int addClass(ClassEntity entity);

	/**
	 * 更新对象，返回更新条数
	 * @param entity
	 * @return int
	 */
	@Update("UPDATE CLASS SET className = #{className} WHERE classId = #{classId}")
	public int updateClass(ClassEntity entity);

	/**
	 * 更具id值删除对象，返回删除条数
	 * @param classId
	 * @return int
	 */
	@Delete("DELETE FROM CLASS WHERE classId = #{classId}")
	public int deleteClass(@Param("classId") int classId);

	/**
	 * 情况对象表
	 */
	@Update("TRUNCATE TABLE CLASS;")
	public void truncateClass();
}
