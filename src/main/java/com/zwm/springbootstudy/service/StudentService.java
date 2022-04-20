package com.zwm.springbootstudy.service;

import com.zwm.springbootstudy.mapper.StudentMapper;
import com.zwm.springbootstudy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service(value = "studentService")
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    public List<Student> getAllStudents() {
        return studentMapper.selectAllStudents();
    }

    //查询所有学生数量
    public Integer queryAllStudentsCount() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Integer allStudentsCount = (Integer) redisTemplate.opsForValue().get("allStudentsCount");
        if (null == allStudentsCount) {
            allStudentsCount = studentMapper.selectAllStudentsCount();
            redisTemplate.opsForValue().set("allStudentsCount", allStudentsCount, 15, TimeUnit.SECONDS);
        }
        return allStudentsCount;
    }
}
