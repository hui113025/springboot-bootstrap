# springcloud-config
### 如何运行

请使用```mvn:spring-boot:run```命令运行，不要使用```main()```方法运行，否则对静态资源修及模板文件的修改不会立即生效，影响开发效率。

### 开发规范
1. 尽量精简项目大小，**不要随便添加类和造轮子**，需要工具类的话首先从apache-commons-*和guava中找，那里面的工具类基本满足本项目90%开发需求。
2. **Java代码风格保持统一**，可以参考[阿里巴巴Java开发手册(正式版)](https://www.baidu.com/s?ie=utf8&oe=utf8&wd=%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C(%E6%AD%A3%E5%BC%8F%E7%89%88)&tn=98010089_dg&ch=3)。
3. 类与方法不应该过大，如果方法内代码行数过多应拆分并重构该方法，如果类内方法过多应拆分并重构该类，以便复用及**减少重复代码**。
4. **```Model```对象不允许添加任何与其对应数据表无关的成员变量（字段）**，如果需要添加的话请在```com.module.product.common.dto```下创建相应的DTO，如```StudentDto```。
5. 单表查询可不用写SQL，直接使用通用```Mapper```的```Condition```对象以编程方式完成，连表查询必须在对应的XML中编写SQL。无论使用哪种方式，请考虑SQL的性能，**至少做到用到什么字段查什么字段，严禁无脑 ```select *```**。
6. 通常情况下不要自行处理异常，遇到业务异常应创建```com.module.core.exception.ServiceException```并抛出交由异常处理器统一处理。
注意，```ServiceException```内的```message```会作为返回JSON的消息部分，请根据业务认真填写，有的甚至直接作为文案显示，比如```throw new ServiceException("验证码不正确");```）。
7. ```js、css、html、jsp、properties、xml```等文件名称包含多单词时必需**用分次符（-）拼接**，如```user-center.html、user-center.js```，HTML内元素id、attribute命名也应遵守该命名约定，如```<button id="btn-create-order"></button>```。


### 开发流程
1. 克隆项目至IDE并切换至dev分支
2. 使用```com.module.product.CodeGenerator```代码生成工具生成对应数据表(```Model```)的基础代码。
3. 创建对应的jsp、js文件。

### 框架&文档

- 以上架构克隆同事，作为种子项目
- Spring Boot([文档](http://docs.spring.io/spring-boot/docs/1.5.3.RELEASE/reference/htmlsingle/))
- Thymeleaf([文档](http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html))
- MyBaits([文档](http://www.mybatis.org/mybatis-3/zh/index.html))
- MyBaits通用Mapper、分页插件([文档](http://www.mybatis.tk/))
