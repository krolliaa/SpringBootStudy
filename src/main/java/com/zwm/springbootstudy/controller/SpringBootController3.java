package com.zwm.springbootstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "springBootController3")
@RequestMapping(value = "/springboot", method = RequestMethod.GET)
public class SpringBootController3 {
    //@RequestMapping(value = "/some1.do")
    @GetMapping(value = "/some1.do")
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
