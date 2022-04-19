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



## 7.`SpringBoot`集成`Redis`



## 8.`SpringBoot`集成`Dubbo`



## 9.`SpringBoot`与非`Web`应用程序



## 10.`SpringBoot`与拦截器`Interceptor`



## 11.`SpringBoot`与`Servlet`



## 12.`SpringBoot`与过滤器`Filter`



## 13.`SpringBoot`打包与部署



## 14.`SpringBoot`集成`logback`

