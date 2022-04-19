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

## 3. `SpringBoot`集成`JSP`

## 4.`SpringBoot`集成`MyBatis`

## 5.`SpringBoot`集成`Transaction`

## 6.`SpringBoot`集成`SpringMVC`

## 7.`SpringBoot`集成`Restful`

## 8.`SpringBoot`集成`Redis`

## 9.`SpringBoot`集成`Dubbo`

## 10.`SpringBoot`与非`Web`应用程序

## 11.`SpringBoot`与拦截器`Interceptor`

## 12.`SpringBoot`与`Servlet`

## 13.`SpringBoot`与过滤器`Filter`

## 14.`SpringBoot`打包与部署

## 15.`SpringBoot`集成`logback`
