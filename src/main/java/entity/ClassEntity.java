package entity;

import java.io.Serializable;

/**
 * 班级信息Entity
 * 
 * @author xugu-publish
 * @date 2019/1/22
 * @since 1.8
 */
public class ClassEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 班级ID号
	 */
	private int classId;

	/**
	 * 班级名称
	 */
	private String className;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Class{");
		sb.append("classId=").append(classId);
		sb.append(", className='").append(className).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
