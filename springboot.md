# `SpringBoot`

`SpringBoot`基本可以完全不使用`XML`配置信息而采用注解即可完成各项功能，其实就是`SSM`框架的集合体，当然，`SpringBoot`的整合不局限于整合`SSM`。在`IDEA`搭建`SpringBoot`项目非常简单
---> `Spring Initializr` ---> 配置信息 ---> `Web` ---> `Spring Web` ---> `Maven Reload project`
，结果如图：【创建时可以使用 `start.aliyun.com`】

细说目录结构：

1. `resources`目录下的`static`目录用于存放静态资源，如图片、`CSS`、`JS`文件等
2. `template`目录下存放`Web`页面的模板文件
3. `application.properties`存放程序各种依赖的配置信息，比如：服务端口信息、数据库连接配置信息、`SSM`集成的配置信息等
4. `.gitgnore`：使用`git`版本控制工具的时候，可以在该文件中设置一些忽略提交的文件
5. `.mvn`、`mvnw`、`mvnw.cmd`：使用脚本操作`Maven`命令，在国内不常使用，可删除
6. `xxxApplication.java`：`SpringBoot`程序的入口文件

## 1. 初步使用`SpringBoot`

`pom.xml`：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.zwm</groupId>
    <artifactId>SpringBootStudy</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>SpringBootStudy</name>
    <description>SpringBootStudy</description>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>2.3.7.RELEASE</spring-boot.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.3.7.RELEASE</version>
                <configuration>
                    <mainClass>com.zwm.springbootstudy.SpringBootStudyApplication</mainClass>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

`application.properties`：

```properties
# 应用名称
spring.application.name=SpringBootStudy
server.port=8080
server.servlet.context-path=/
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springboot?useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=123456
```

`SpringBootController`：

```java
package com.zwm.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "springBootController")
@RequestMapping(value = "/springboot")
public class SpringBootController {
    @RequestMapping(value = "/say")
    @ResponseBody
    public String say() {
        return "Hello SpringBoot!";
    }
}
```

`SpringBootStudyApplication`：

```java
package com.zwm.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }
}
```

可以使用`yml`配置：

```yaml
spring:
  application:
    name: SpringBootStudy
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ssm?useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 123456
server:
  port: 8080
  servlet:
    context-path: /
```

除此之外还可以进行多环建配置：

```yaml
spring:
  application:
    name: SpringBootStudy-Development
server:
  port: 8081
  servlet:
    context-path: /
```

```yaml
spring:
  application:
    name: SpringBootStudy-Test
server:
  port: 8082
  servlet:
    context-path: /
```

```yaml
spring:
  application:
    name: SpringBootStudy-Development
server:
  port: 8083
  servlet:
    context-path: /
```

## 2. `SpringBoot`常用用法

可以在`properties`或者`yaml/yml`文件中使用自定义的配置，然后在控制器类中使用`@value(${"xxx"})`达到使用自定义配置的目的：

```properties
server.port=8080
server.servlet.context-path=/
school.name=SCUT
websit==https://www.scut.edu.cn
```

```java
package com.zwm.springbootstudy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "springBootController")
@RequestMapping(value = "/springboot")
public class SpringBootController {

    @Value("${school.name}")
    private String schoolName;

    @Value("${website}")
    private String website;

    @RequestMapping(value = "/say")
    @ResponseBody
    public String say() {
        return "Hello SpringBoot! " + schoolName + website;
    }
}
```

`SpringBoot`还可以为某个对象赋予属性值，使用前缀指定对象类即可：

```properties
project.name=ssm
project.website=https://www.ssm.com.cn
```

```java
package com.zwm.springbootstudy.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "myProject")
@ConfigurationProperties(prefix = "project")
public class MyProject {
    private String name;
    private String website;

    public MyProject() {
    }

    public MyProject(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "MyProject{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
```

```java
package com.zwm.springbootstudy.controller;

import com.zwm.springbootstudy.pojo.MyProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "springBootController1")
@RequestMapping(value = "/springboot")
public class SpringBootController1 {

    @Autowired
    private MyProject myProject;

    @RequestMapping(value = "/say1")
    @ResponseBody
    public String say() {
        return "Hello SpringBoot! " + myProject.toString();
    }
}
```

访问：`http://localhost:8080/springboot/say1`

看到`MyProject`报错，可以在`pom`文件中加入：`spring-boot-configuration-processor`依赖并设置`optional`为`true`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

如果在`properties`中设置的对象属性值是中文则会出现乱，这时候需要在编译的时候先转化为`ASCII`码然后按住：`ctrl + alt + s`===>`Editor`===>`File Encodings` ===>勾选`Transparent native-to-ascii conversion`，然后需要把原目录的`application.properties`删除然后重新建一个最后重启项目【此步骤必须得做否则还是乱码】此时再次访问`http://localhost:8080/springboot/say1`可以正常显示中文

## 3.`SpringBoot`集成`MyBatis`

引入依赖：`mybatis-spring-boot-starter`和`mysql-connector-java`【版本可改】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.zwm</groupId>
    <artifactId>SpringBootStudy</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>SpringBootStudy</name>
    <description>SpringBootStudy</description>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>2.3.7.RELEASE</spring-boot.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.3.7.RELEASE</version>
                <configuration>
                    <mainClass>com.zwm.springbootstudy.SpringBootStudyApplication</mainClass>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

配置`application.properties`：

```properties
# 应用名称
spring.application.name=SpringBootStudy
server.port=8080
server.servlet.context-path=/
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springboot?useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=123456
school.name=SCUT
website=https://www.scut.edu.cn
project.name=ssm项目
project.website=https://www.ssm.com.cn
mybatis.type-aliases-package=com.zwm.springbootstudy.pojo
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

`pojo.Student`：

```java
package com.zwm.springbootstudy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component(value = "student")
public class Student {
    private Integer id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

`mapper`层以及`mapper`相关`xml`配置【`SQL`语句】：

```java
package com.zwm.springbootstudy.mapper;

import com.zwm.springbootstudy.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public abstract List<Student> selectAllStudents();
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zwm.springbootstudy.mapper.StudentMapper">
    <sql id="base_select_column">id, name, age</sql>
    <select id="selectAllStudents" resultType="student">
        select <include refid="base_select_column"/> from student
    </select>
</mapper>
```

`service`层：

```java
package com.zwm.springbootstudy.service;

import com.zwm.springbootstudy.mapper.StudentMapper;
import com.zwm.springbootstudy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.selectAllStudents();
    }
}
```

`controller`层：

```java
package com.zwm.springbootstudy.controller;

import com.zwm.springbootstudy.pojo.Student;
import com.zwm.springbootstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "springBootController2")
@RequestMapping(value = "/springboot")
public class SpringBootController2 {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getAllStudents")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
```

启动项目输入地址测试：`http://localhost:8080/springboot/getAllStudents`：

```javascript
[
	{
		"id": 1,
		"name": "kroll",
		"age": 18
	},
	{
		"id": 2,
		"name": "steven",
		"age": 26
	},
	{
		"id": 3,
		"name": "jack",
		"age": 26
	}
]
```

`IDEA`从`github`上拉取项目遇到的一些问题：一是`pom.xml`需要重新加载一下，二是找不到主加载类的问题需要`clean`一下然后重新`install`最后`rebuild project`，三是没有加载进`resources`的资源到`target`中，要使用`ctrl + alt + shift + s`将其设定为`resources`目录

## 4.`SpringBoot`集成`Transaction`

添加事务支持是为了防止程序异常但是更改了数据库里的数据所以要做事务管理，只需要在`service`层加上`@Transactional`就可以了，或者在`SpringBootAoolication.java`上加上`@EnableTransactionManagement`注解即可

```java
package com.zwm.springbootstudy.service;

import com.zwm.springbootstudy.mapper.StudentMapper;
import com.zwm.springbootstudy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public List<Student> getAllStudents() {
        return studentMapper.selectAllStudents();
    }
}
```

```java
package com.zwm.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }
}
```

## 5.`SpringBoot`集成`SpringMVC`

`SpringBoot`将`@Controller`和`@ResponseBody`结合在一块形成了`@RestController`，这是一个类注解，如果在一个类上加上这个注解代表给每一个方法都添加了`@ResponseBody`注解：

```java
package com.zwm.springbootstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "springBootController3")
@RequestMapping(value = "/springboot", method = RequestMethod.GET)
public class SpringBootController3 {
    @RequestMapping(value = "/some1.do")
    public String doSome1() {
        return "SpringBoot doSome1 @RestController";
    }

    @RequestMapping(value = "/some2.do")
    public String doSome2() {
        return "SpringBoot doSome2 @RestController";
    }

    @RequestMapping(value = "/some3.do")
    public String doSome3() {
        return "SpringBoot doSome3 @RestController";
    }
}
```

除此之外，如果指定`@RequestMapping()`中的`method`属性可以直接使用：`@GetMapping(value = "")`，同理还有`@PostMapping @DeleteMapping @PutMapping`，这是`Restful`的查询方式，分别对应着：查询数据、添加数据、删除数据、修改数据，关于`Restful`会在第六节中提到

## 6.`SpringBoot`集成`Restful`

通俗地理解`Restful`就是统一接口+路径变量+简化参数，只要符合`Rest`原则就使用了`Restful`架构，可以参考下面这个案例：使用`Restful`架构模拟对`springboot`数据库中的`student`表做一次完整的增删改查操作，然后可以使用`postman`进行接口测试

```java
package com.zwm.springbootstudy.controller;

import org.springframework.web.bind.annotation.*;

@RestController(value = "springBootController4")
@RequestMapping(value = "/springboot")
public class SpringBootController4 {
    @PostMapping(value = "/student/{id}/{name}/{age}")
    public String addStudent(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("age") Integer age) {
        return "添加 id 为：" + id + " 姓名为：" + name + " 年龄为：" + age + " 的学生";
    }

    @DeleteMapping(value = "/student/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        return "删除 id 为：" + id + " 的学生";
    }

    @PutMapping(value = "/student/{id}/{name}/{age}")
    public String updateStudent(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("age") Integer age) {
        return "修改 id 为：" + id + " 的学生姓名为：" + name + " 年龄为：" + age;
    }

    @GetMapping(value = "/student/{id}")
    public String selectStudent(@PathVariable("id") Integer id) {
        return "获取 id 为：" + id + " 的学生";
    }
}
```

说说如果不注意的话可能会产生`Restful`的请求冲突，比如下面这种，虽然我们写着的是`id status`第二个是`status id`，但是因为传递的都是`Integer`类型的属性所以根本无法知道到底前端传递的数据会调用哪个控制器方法，就会导致报错无法正常返回数据，解决的方式有两种：

> 1. 更改请求地址
> 2. 更改请求方式

```java
@GetMapping("/springboot/{id}/order/{status}")
public Object queryOrder1(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("id", id);
    resultMap.put("status", status);
    return resultMap;
}
/**
 * 查询数据
 * 请求方式：GET
 * 请求地址：localhost:9090/springboot/1001/order/1
 *
 * @param id
 * @param status
 * @return
 */
@GetMapping("/springboot/{status}/order/{id}")
public Object queryOrder2(@PathVariable("id") Integer id,
                          @PathVariable("status") Integer status) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("id", id);
    resultMap.put("status", status);
    return resultMap;
}
```

除此之外，使用`Reftful`架构有两条原则需要遵守：

> 1. 请求路径不要出现动词，最好是直接名词的方式呈现
> 2. 分页、排序这些操作不要使用斜杠的方式传递参数而是使用`xxx?page=1&sort=desc`这种方式

## 7.`SpringBoot`集成`Redis`

完善根据`id`查询学生的功能，先从`Redis`缓存中查询数据如果找不到就先去数据库中查询然后放到`redis`缓存中，这样下次查询的时候就可以从`Redis`中查询了。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

```java
package com.zwm.springbootstudy.mapper;

import com.zwm.springbootstudy.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public abstract List<Student> selectAllStudents();

    public abstract Integer selectAllStudentsCount();
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zwm.springbootstudy.mapper.StudentMapper">
    <sql id="base_select_column">id
    , name, age</sql>
    <select id="selectAllStudents" resultType="student">
        select
        <include refid="base_select_column"/>
        from student
    </select>
    <select id="selectAllStudentsCount" resultType="java.lang.Integer">
        select count(*) from student
    </select>
</mapper>
```

```java
package com.zwm.springbootstudy.service;

import com.zwm.springbootstudy.mapper.StudentMapper;
import com.zwm.springbootstudy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    public List<Student> getAllStudents() {
        return studentMapper.selectAllStudents();
    }

    //查询所有学生数量
    public Integer queryAllStudentsCount() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Integer allStudentsCount = (Integer) redisTemplate.opsForValue().get("allStudentsCount");
        if (null == allStudentsCount) {
            allStudentsCount = studentMapper.selectAllStudentsCount();
            redisTemplate.opsForValue().set("allStudentsCount", allStudentsCount, 15, TimeUnit.SECONDS);
        }
        return allStudentsCount;
    }
}
```

```java
package com.zwm.springbootstudy.controller;

import com.zwm.springbootstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "springBootController5")
@RequestMapping(value = "/springboot")
public class SpringBootController5 {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/getAllStudentsCount")
    public String AllStudentsCount() {
        Integer allStudentsCount = studentService.queryAllStudentsCount();
        return "学生总人数：" + allStudentsCount;
    }
}
```

启动项目，浏览器访问：`http://localhost:8080/springboot/getAllStudentsCount`即可，因为在`application.properties`加入了`mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl`，所以访问数据库的时候都可以在终端看到有`SQL`语句的输出，如果没有访问数据库则没有`SQL`语句的输出，依次可以判断访问的是`Redis`

## 8.`SpringBoot`集成`Dubbo`

公司电脑太。。。，回家搞

## 9.`SpringBoot`与非`Web`应用程序

非`Web`应用程序这里指的是纯`Java`程序，有两种实现方式：

1. 直接在`xxxApplication.java`中使用`SpringApplication.run()`方法获取返回的`Spring`容器对象，再获取业务`bean`进行调用。`SpringBoot`程序启动之后将返回`ConfigurableApplicationContext`对象，相当于之前在`Spring`学习的：`ApplicationContext applicationContext = new ClassPathXmlApplicationContext("")`。

   ```java
   package com.zwm.springbootstudy;
   
   import com.zwm.springbootstudy.service.StudentService;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.context.ConfigurableApplicationContext;
   import org.springframework.transaction.annotation.EnableTransactionManagement;
   
   @SpringBootApplication
   @EnableTransactionManagement
   public class SpringBootStudyApplication {
       public static void main(String[] args) {
           ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SpringBootStudyApplication.class, args);
           StudentService studentService = (StudentService) configurableApplicationContext.getBean("studentService");
           System.out.println(studentService.queryAllStudentsCount());
       }
   }
   ```

2. 如果需要预先加载一些东西可以使用`CommandLineRunner`接口：

   ```java
   package com.zwm.springbootstudy;
   
   import com.zwm.springbootstudy.service.StudentService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.CommandLineRunner;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.transaction.annotation.EnableTransactionManagement;
   
   @SpringBootApplication
   @EnableTransactionManagement
   public class SpringBootStudyApplication implements CommandLineRunner {
   
       @Autowired
       private StudentService studentService;
   
       public static void main(String[] args) {
           //ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SpringBootStudyApplication.class, args);
           //StudentService studentService = (StudentService) configurableApplicationContext.getBean("studentService");
           //System.out.println(studentService.queryAllStudentsCount());
           SpringApplication.run(SpringBootStudyApplication.class, args);
       }
   
       public void run(String... args) throws Exception {
           System.out.println(studentService.queryAllStudentsCount());
       }
   }
   ```

题外话：可以看到启动`SpringBoot`的时候会有一个`Logo`，有时候不想看到这个`Logo`可以将其关掉。

```java
package com.zwm.springbootstudy;

import com.zwm.springbootstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootStudyApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        //ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SpringBootStudyApplication.class, args);
        //StudentService studentService = (StudentService) configurableApplicationContext.getBean("studentService");
        //System.out.println(studentService.queryAllStudentsCount());
        SpringApplication springApplication = new SpringApplication(SpringBootStudyApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
        //SpringApplication.run(SpringBootStudyApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println(studentService.queryAllStudentsCount());
    }
}
```

当然你还可以更换`Logo`，可以到`https://www.bootschool.net/ascii`制作生成`banner.txt`然后将`banner.txt`放到`resources`目录下即可：

```java
@@@@@@@@ @@@@@@@@ @@@@@@@   @@@@@@   @@@@@@@  @@@@@@   @@@@@@  @@@
     @@! @@!      @@!  @@@ @@!  @@@ !@@      @@!  @@@ @@!  @@@ @@!
   @!!   @!!!:!   @!@!!@!  @!@  !@! !@!      @!@  !@! @!@  !@! @!!
 !!:     !!:      !!: :!!  !!:  !!! :!!      !!:  !!! !!:  !!! !!:  
```

## 10.`SpringBoot`与拦截器`Interceptor`

拦截器这位兄弟在`SpringMVC`就见过了，当时使用的是`XML`进行配置的，现在使用`SpringBoot`可以不用`XML`而使用的是拦截器实现类：`WebMvcConfigurer`来替换掉配置文件：`com.zwm.config.InterceptorConfig ---> Configurer`是配置者的意思：

```java
package com.zwm.springbootstudy.handler.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        //定义拦截路径
        String[] addPathPatterns = {"/user/**"};
        //定义不拦截的路径
        String[] excludePathPatterns = {"/user/login", "/user/error"};
        //注册拦截器 ---> 首先你得有一个拦截器：创建拦截器 SpringBootInterceptor
        registry.addInterceptor(new SpringBootInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
```

拦截器：

```java
package com.zwm.springbootstudy.handler.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringBootInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--------编写拦截规则-------");
        Integer code = (Integer) request.getSession().getAttribute("code");
        if (null == code) {
            response.sendRedirect(request.getContextPath() + "/springboot/error");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
```

控制器：

```java
package com.zwm.springbootstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class SpringBootController6 {
    @GetMapping(value = "/account")
    public Object queryAccount() {
        return "帐户可用余额：897213445元";
    }

    @GetMapping(value = "/login")
    public Object verifyRealName(HttpServletRequest request) {
        request.getSession().setAttribute("code", 0);
        return "用户实名认证成功";
    }

    @RequestMapping(value = "/error")
    public Object error() {
        return "用户没有实名认证";
    }
}
```

如果没有登录也就是`code == null`跳转至`error`，无法访问账户`/user/account`。

## 11.`SpringBoot`与`Servlet`

创建`Servlet`有多种方式，第一种：注解方式

```java
package com.zwm.springbootstudy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwm.springbootstudy.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/one")
public class ServletOne extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student(1, "smith", 3);
        ObjectMapper objectMapper = new ObjectMapper();
        String studentJson = objectMapper.writeValueAsString(student);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(studentJson);
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

然后需要在主文件中加入扫描`Servlet`注解的注解`@ServletComponentScan`：

```java
package com.zwm.springbootstudy;

import com.zwm.springbootstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan(basePackages = {"com.zwm.springbootstudy.service"})
public class SpringBootStudyApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        //ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SpringBootStudyApplication.class, args);
        //StudentService studentService = (StudentService) configurableApplicationContext.getBean("studentService");
        //System.out.println(studentService.queryAllStudentsCount());
        //SpringApplication springApplication = new SpringApplication(SpringBootStudyApplication.class);
        //springApplication.setBannerMode(Banner.Mode.LOG);
        //springApplication.run(args);
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println(studentService.queryAllStudentsCount());
    }
}
```

第二种方法：

```java
package com.zwm.springbootstudy.handler;

import com.zwm.springbootstudy.service.ServletTwo;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean myServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ServletTwo(), "/servlet/two");
        return servletRegistrationBean;
    }
}
```

```java
package com.zwm.springbootstudy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwm.springbootstudy.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletTwo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student(2, "smith", 8);
        ObjectMapper objectMapper = new ObjectMapper();
        String studentJson = objectMapper.writeValueAsString(student);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(studentJson);
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

## 12.`SpringBoot`与过滤器`Filter`

第一种：注解方式

```java
package com.zwm.springbootstudy.service;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/filter/one")
public class FilterOne implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入第一个过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
```

```java
package com.zwm.springbootstudy;

import com.zwm.springbootstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan(basePackages = {"com.zwm.springbootstudy.service"})
public class SpringBootStudyApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        //ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SpringBootStudyApplication.class, args);
        //StudentService studentService = (StudentService) configurableApplicationContext.getBean("studentService");
        //System.out.println(studentService.queryAllStudentsCount());
        //SpringApplication springApplication = new SpringApplication(SpringBootStudyApplication.class);
        //springApplication.setBannerMode(Banner.Mode.LOG);
        //springApplication.run(args);
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println(studentService.queryAllStudentsCount());
    }
}
```

同样也可以使用配置类的方式进行配置：

```java
package com.zwm.springbootstudy;

import com.zwm.springbootstudy.service.FilterTwo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        String[] urlPatterns = {"/filter/two"};
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new FilterTwo());
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }
}
```

```java
package com.zwm.springbootstudy.service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class FilterTwo implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入第二个过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
```

配置文件方式配置`CharacterEncodingFilter`[需要关闭`application.properties`中的`spring.http.encoding.enabled=false` ]：

```java
package com.zwm.springbootstudy;

import com.zwm.springbootstudy.service.FilterTwo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        //String[] urlPatterns = {"/filter/two"};
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new FilterTwo());
        //filterRegistrationBean.addUrlPatterns(urlPatterns);
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        characterEncodingFilter.setForceEncoding(true);
        filterRegistrationBean.setFilter(characterEncodingFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
```

当然也可以不用配置文件式的配置，直接配置`application.properties`：

```properties
spring.http.encoding.enabled=true
spring.http.encoding.force=true
spring.http.encoding.charset=utf-8
```

## 13.`SpringBoot`集成`logback`

配置`pom.xml`即可：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<optional>true</optional>
</dependency>
```

