package mapper;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.ClassEntity;

/**
 * 测试基于MyBatis的数据库基本DML操作
 * 
 * @author wsy
 * @date 2019/01/22
 * @since 1.8
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseDmlTest {

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
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

		ClassMapper classMapper = sqlSession1.getMapper(ClassMapper.class);

		logger.trace("ClassMapper增加数据: " + classMapper.addClass(buildClass()) + " 条！");
		sqlSession1.close();
	}

	/**
	 * 从数据库读取数据Select
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

		ClassMapper classMapper = sqlSession1.getMapper(ClassMapper.class);

		List<ClassEntity> entityList = classMapper.getClassByList();
		logger.trace("ClassMapper数据总数: " + entityList.size() + " 条！");
		for (ClassEntity entity : entityList) {
			logger.trace("ClassMapper查询结果集数据: " + entity);
		}
	}

	/**
	 * 向数据库插入更新数据Update
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

		ClassMapper classMapper = sqlSession1.getMapper(ClassMapper.class);
		List<ClassEntity> entityList = classMapper.getClassByList();

		ClassEntity entity = null;
		if (entityList.size() > 0) {
			entity = entityList.get(0);
			logger.trace("ClassMapper更新数据(更新前): " + entity);
		} else {
			testInsert();
			sqlSession1.commit();
			
			entityList = classMapper.getClassByList();

			entity = entityList.get(0);
			logger.trace("ClassMapper更新数据(更新前): " + entity);
		}
		entity.setClassName("更新数据库字段！");
		logger.trace("ClassMapper更新数据: " + classMapper.updateClass(entity) + " 条！");
		logger.trace("ClassMapper更新数据(更新后): " + classMapper.getClassById(entity.getClassId()));
	}

	/**
	 * 数据库插入删除数据Delete
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

		ClassMapper classMapper = sqlSession1.getMapper(ClassMapper.class);
		List<ClassEntity> entityList = classMapper.getClassByList();

		ClassEntity entity = null;
		if (entityList.size() > 0) {
			entity = entityList.get(0);
			logger.trace("ClassMapper删除数据(删除前): " + entity);
		} else {
			testInsert();
			entityList = classMapper.getClassByList();
			entity = entityList.get(0);
			logger.trace("ClassMapper删除数据(删除前): " + entity);
		}
		logger.trace("ClassMapper删除数据: " + classMapper.deleteClass(entity.getClassId()) + " 条！");
		logger.trace("ClassMapper删除数据(删除后): " + classMapper.getClassById(entity.getClassId()));
	}

	/**
	 * 数据库事务执行s
	 * 
	 * @throws Exception
	 */
	//@Test
	public void testTruncate() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

		ClassMapper classMapper = sqlSession1.getMapper(ClassMapper.class);
		List<ClassEntity> entityList = classMapper.getClassByList();

		logger.trace("ClassMapper清空数据(清空前): " + entityList.size() + " 条！");
		
		classMapper.truncateClass();
		entityList = classMapper.getClassByList();
		logger.trace("ClassMapper清空数据(清空后): " + entityList.size() + " 条！");
	}
}
