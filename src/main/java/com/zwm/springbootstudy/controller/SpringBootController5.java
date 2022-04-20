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
