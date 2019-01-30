# xugu-mybatis-demo 虚谷数据库使用MyBatis Demo程序(含第三方数据源)

1. 代码结构(src.main.java)：
   datasource：第三方数据源封装
   entity：数据库表实体类
   handler：mybatis类型处理器，针对特殊数据类型进行处理
   mapper:数据库访问接口
2. 代码结构(src.test.java)：
   mapper:测试方法
   AnnotationTest:使用MyBatis注解方式访问数据库
   DatabaseDdlTest:使用MyBatis执行DDL语句(使用Statement方式)
   DatabaseDmlTest:使用MyBatis执行DML语句(默认Preparestatment)
   MyBatisCacheTest:MyBatis二级缓存使用测试
3. 资源文件(src.test.sources):
   ClassMapper.xml:对应实体ClassEntity的MyBaits接口映射文件
   InitSqlMapper.xml:对应MyBatis的DDL语句执行接口映射文件
   StudentMapper.xml:对应实体StudentEntity的MyBaits接口映射文件
   mybatis-config.xml:mybatis配置文件(数据源、映射文件、缓存机制、类型处理器定义等)
   local-xugu.properties:虚谷数据库连接信息
   log4j.properties:log4j日志配置
   init_db.sql:程序将要使用到的数据库创建语句
