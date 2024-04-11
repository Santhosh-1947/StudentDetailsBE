package com.springbootreact.reactSpringboot.Service;

import com.springbootreact.reactSpringboot.Entity.Student;
import com.springbootreact.reactSpringboot.Exception.StudentAlreadyExistsException;

import java.util.List;

public interface iStudentService {


    Student addStudent(Student student) throws StudentAlreadyExistsException;
    List<Student> getStudent();
    Student updateStudent(Student student, Integer id);
    Student getStudentById(Integer id);
     void deleteStudent(Integer id);

}
