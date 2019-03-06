package mapper;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import entity.ClassEntity;

/**
 * 通过注解方式操作MyBatis
 * 
 * @author xugu-publish
 * @Title:
 * @Description:
 * @date 2019/01/22
 * @sine 1.8
 */
public class AnnotationTest {

	private SqlSessionFactory factory;

	private Logger logger = Logger.getLogger(DatabaseDmlTest.class);

	@Before
	public void setUp() throws Exception {
		factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));

	}
	
	private ClassEntity buildClass() {
		ClassEntity classEntity = new ClassEntity();
		classEntity.setClassName("一班");
		return classEntity;
	}

	/**
	 * 向数据库插入数据Insert
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInsert() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true);

		ClassAnnotationMapper classAnnotationMapper = sqlSession1.getMapper(ClassAnnotationMapper.class);

		logger.trace("ClassAnnotationMapper增加数据: " + classAnnotationMapper.addClass(buildClass()) + " 条！");
		sqlSession1.close();
	}

	/**
	 * 从数据库读取数据Select
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true);

		ClassAnnotationMapper classAnnotationMapper = sqlSession1.getMapper(ClassAnnotationMapper.class);

		List<ClassEntity> entityList = classAnnotationMapper.getClassByList();
		logger.trace("ClassAnnotationMapper数据总数: " + entityList.size() + " 条！");
		for (ClassEntity entity : entityList) {
			logger.trace("ClassAnnotationMapper查询结果集数据: " + entity);
		}
	}

	/**
	 * 向数据库插入更新数据Update
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true);

		ClassAnnotationMapper classAnnotationMapper = sqlSession1.getMapper(ClassAnnotationMapper.class);
		List<ClassEntity> entityList = classAnnotationMapper.getClassByList();

		ClassEntity entity = null;
		if (entityList.size() > 0) {
			entity = entityList.get(0);
			logger.trace("ClassAnnotationMapper更新数据(更新前): " + entity);
		} else {
			testInsert();
			sqlSession1.commit();
			
			entityList = classAnnotationMapper.getClassByList();

			entity = entityList.get(0);
			logger.trace("ClassAnnotationMapper更新数据(更新前): " + entity);
		}
		entity.setClassName("更新数据库字段！");
		logger.trace("ClassAnnotationMapper更新数据: " + classAnnotationMapper.updateClass(entity) + " 条！");
		logger.trace("ClassAnnotationMapper更新数据(更新后): " + classAnnotationMapper.getClassById(entity.getClassId()));
	}

	/**
	 * 数据库插入删除数据Delete
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true);

		ClassAnnotationMapper classAnnotationMapper = sqlSession1.getMapper(ClassAnnotationMapper.class);
		List<ClassEntity> entityList = classAnnotationMapper.getClassByList();

		ClassEntity entity = null;
		if (entityList.size() > 0) {
			entity = entityList.get(0);
			logger.trace("ClassAnnotationMapper删除数据(删除前): " + entity);
		} else {
			testInsert();
			entityList = classAnnotationMapper.getClassByList();
			entity = entityList.get(0);
			logger.trace("ClassAnnotationMapper删除数据(删除前): " + entity);
		}
		logger.trace("ClassAnnotationMapper删除数据: " + classAnnotationMapper.deleteClass(entity.getClassId()) + " 条！");
		logger.trace("ClassAnnotationMapper删除数据(删除后): " + classAnnotationMapper.getClassById(entity.getClassId()));
	}

	/**
	 * 数据库事务执行s
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTruncate() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true);

		ClassAnnotationMapper classAnnotationMapper = sqlSession1.getMapper(ClassAnnotationMapper.class);
		List<ClassEntity> entityList = classAnnotationMapper.getClassByList();

		logger.trace("ClassAnnotationMapper清空数据(清空前): " + entityList.size() + " 条！");
		
		classAnnotationMapper.truncateClass();
		entityList = classAnnotationMapper.getClassByList();
		logger.trace("ClassAnnotationMapper清空数据(清空后): " + entityList.size() + " 条！");
	}
}
