package mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class DatabaseDdlTest {
	private SqlSessionFactory factory;

	private Logger logger = Logger.getLogger(DatabaseDmlTest.class);

	@Before
	public void setUp() throws Exception {
		factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));

	}
	
	/**
	 * 通过Statement从数据库查询数据
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStatement() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

        StudentMapper studentMapper = sqlSession1.getMapper(StudentMapper.class);

        logger.info("studentMapper读取数据: " + studentMapper.getStudentByName("明明"));
        sqlSession1.close();
	}
	
	/**
	 * 通过Statement在数据库中创建表对象
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTable() throws Exception {
		
		try {
			testDropTabel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		testCreateTabel();
		testCommentTabel();
		testAddConstraint();
		testAddIndex();
	}
	
	/**
	 * 删除表
	 * 
	 * @throws Exception
	 */
	public void testDropTabel() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

        InitSqlMapper initSqlMapper = sqlSession1.getMapper(InitSqlMapper.class);

        initSqlMapper.dropTabel("CREAT_TABLE");
        logger.info("删除表成功！");
	}
	
	/**
	 * 创建表
	 * 
	 * @throws Exception
	 */
	public void testCreateTabel() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

        InitSqlMapper initSqlMapper = sqlSession1.getMapper(InitSqlMapper.class);

        initSqlMapper.createTabel("CREAT_TABLE");
        logger.info("创建表成功！");
	}
	
	public void testCommentTabel() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

        InitSqlMapper initSqlMapper = sqlSession1.getMapper(InitSqlMapper.class);

        initSqlMapper.commentTabel("CREAT_TABLE");
        logger.info("添加表注释成功！");
	}
	
	public void testAddConstraint() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

        InitSqlMapper initSqlMapper = sqlSession1.getMapper(InitSqlMapper.class);

        initSqlMapper.addTabelConstraint("CREAT_TABLE");
        logger.info("添加表约束成功！");
	}
	
	public void testAddIndex() throws Exception {
		SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务

        InitSqlMapper initSqlMapper = sqlSession1.getMapper(InitSqlMapper.class);

        initSqlMapper.createTabelIndex("CREAT_TABLE");
        logger.info("创建表索引成功！");
	}
}
