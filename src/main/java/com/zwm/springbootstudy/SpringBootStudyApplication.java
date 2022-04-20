package com.zwm.springbootstudy;

import com.zwm.springbootstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.Banner;
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