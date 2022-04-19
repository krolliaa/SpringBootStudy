package com.zwm.springbootstudy.controller;

import org.springframework.web.bind.annotation.*;

@RestController(value = "springBootController4")
@RequestMapping(value = "/springboot")
public class SpringBootController4 {
    @PostMapping(value = "/student/{id}/{name}/{age}")
    public String addStudent(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("age") Integer age) {
        return "添加 id 为：" + id + " 姓名为：" + name + " 年龄为：" + age + " 的学生";
    }

    @DeleteMapping(value = "/student/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        return "删除 id 为：" + id + " 的学生";
    }

    @PutMapping(value = "/student/{id}/{name}/{age}")
    public String updateStudent(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("age") Integer age) {
        return "修改 id 为：" + id + " 的学生姓名为：" + name + " 年龄为：" + age;
    }

    @GetMapping(value = "/student/{id}")
    public String selectStudent(@PathVariable("id") Integer id) {
        return "获取 id 为：" + id + " 的学生";
    }
}
