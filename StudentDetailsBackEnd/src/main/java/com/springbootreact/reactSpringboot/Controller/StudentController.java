package com.springbootreact.reactSpringboot.Controller;


import com.springbootreact.reactSpringboot.Entity.Student;
import com.springbootreact.reactSpringboot.Service.iStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final iStudentService studentService;


    @GetMapping
    public ResponseEntity<List<Student>> getStudent(){
        return new ResponseEntity<>(studentService.getStudent(), HttpStatus.FOUND);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
     return studentService.addStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Integer id)
    {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Integer id)
    {
        studentService.deleteStudent(id);
            }

    @GetMapping("/fetch/{id}")
    public Student getStudentById(@PathVariable Integer id)
    {
        return studentService.getStudentById(id);
    }

}
