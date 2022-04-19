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
