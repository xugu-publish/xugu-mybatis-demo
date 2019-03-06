package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import entity.StudentEntity;

/**
 * 表基本操作接口
 * 
 * @author xugu-publish
 * @date 2019/01/22
 * @since 1.8
 */
public interface StudentMapper {

	/**
	 * 根据唯一ID值获取对象
	 * 
	 * @param id
	 * @return StudentEntity
	 */
	public StudentEntity getStudentById(int id);

	/**
	 * 根据名称获取对象列表
	 * 
	 * @param name
	 * @return List
	 */
	public List<StudentEntity> getStudentByName(@Param("name") String name);

	/**
	 * 插入对象，返回插入条数
	 * @param student
	 * @return int
	 */
	public int addStudent(StudentEntity student);

	/**
	 * 根据唯一ID更新name字段，返回更新条数
	 * @param name
	 * @param id
	 * @return int
	 */
	public int updateStudentName(@Param("name") String name, @Param("id") int id);

	/**
	 * 多表联合查询，联合条件ID
	 * @param id
	 * @return StudentEntity
	 */
	public StudentEntity getStudentByIdWithClassInfo(int id);

	/**
	 * 根据name名称删除对象
	 * @param name
	 * @return int
	 */
	public int deleteStudentName(@Param("name") String name);
}
