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
