package com.zwm.springbootstudy.mapper;

import com.zwm.springbootstudy.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public abstract List<Student> selectAllStudents();
}
