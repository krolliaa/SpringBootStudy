package com.zwm.springbootstudy.service;

import com.zwm.springbootstudy.mapper.StudentMapper;
import com.zwm.springbootstudy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public List<Student> getAllStudents() {
        return studentMapper.selectAllStudents();
    }
}
