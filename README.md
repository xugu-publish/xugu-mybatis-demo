
# xugu-mybatis-demo #

虚谷数据库使用MyBatis Demo程序(含第三方数据源)

Introduction
--------------

- git clone https://github.com/xugu-publish/xugu-mybatis-demo.git
- cd xugu-mybatis-demo && mvn install
- have fun.

### Project Structure
   
* **datasource:** 
封装的第三方数据源产品使用(c3p0、druid)
* **entity:** 
数据库表实体类Bean
* **handler:** 
MyBatis类型处理器，针对特殊数据类型进行封装
* **mapper:** 
数据库访问接口及映射关系


How to build
--------------

xugu-mybatis-demo uses the maven framework. 


#### Pre-requisites for building the project:
* Maven, version 3+
* Java JDK 1.8

#### Building it

This is a maven project, and to build it use the following command

```
$ mvn clean install
```
Optionally you can specify -Drelease to trigger obfuscation and/or uglification (as needed)

Optionally you can specify -Dmaven.test.skip=true to skip the tests (even though
you shouldn't as you know)

The build result will be a Pentaho package located in ```target```.

#### Running the tests

__Unit tests__

This will run all unit tests in the project (and sub-modules). To run integration tests as well, see Integration Tests below.

```
$ mvn test
```

If you want to remote debug a single java unit test (default port is 5005):

```
$ cd core
$ mvn test -Dtest=<<YourTest>> -Dmaven.surefire.debug
```

__Integration tests__

In addition to the unit tests, there are integration tests that test cross-module operation. This will run the integration tests.

```
$ mvn verify -DrunITs
```

To run a single integration test:

```
$ mvn verify -DrunITs -Dit.test=<<YourIT>>
```

To run a single integration test in debug mode (for remote debugging in an IDE) on the default port of 5005:

```
$ mvn verify -DrunITs -Dit.test=<<YourIT>> -Dmaven.failsafe.debug
```

To skip test

```
$ mvn clean install -DskipTests
```

To get log as text file

```
$ mvn clean install test >log.txt
```


__IntelliJ__

* Don't use IntelliJ's built-in maven. Make it use the same one you use from the commandline.
  * Project Preferences -> Build, Execution, Deployment -> Build Tools -> Maven ==> Maven home directory



### Asking for help
Please go to https://www.xugucn.com to ask questions and get help.

Documentation
--------------

#### Demo程序使用步骤

1. 从https://github.com/xugu-publish/xugu-mybatis-demo.git下载最新源代码，并引入工程
2. 在pom.xml中配置lib下的虚谷数据库jdbc依赖包，将依赖包拷贝只maven工程对应的环境目录结构中，并update maven工程
3. 执行init_db.sql中的SQL语句(创建表及初始化数据)
4. 执行测试类，查看运行结果

