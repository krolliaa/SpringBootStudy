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
