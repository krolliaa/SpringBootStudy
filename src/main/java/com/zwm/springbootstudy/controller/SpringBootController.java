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
