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
