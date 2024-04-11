package com.springbootreact.reactSpringboot.Service;

import com.springbootreact.reactSpringboot.Entity.Student;
import com.springbootreact.reactSpringboot.Exception.StudentAlreadyExistsException;
import com.springbootreact.reactSpringboot.Exception.StudentNotFoundException;
import com.springbootreact.reactSpringboot.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService implements iStudentService{

    @Autowired
    private final StudentRepository studentRepository;
    

    @Override
    public Student addStudent(Student student){
        
        if (studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail()+" already exists!");
        }
            
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Integer id) {
        return studentRepository.findById(id).map(st->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(()->new StudentNotFoundException("Student not found.."));
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("No student found with this ID."));
    }

    @Override
    public void deleteStudent(Integer id) {
        if(!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("student not found");
        }
        else
        {
            studentRepository.deleteById(id);
        }
    }

    private boolean studentAlreadyExists(String email) {
        
        return studentRepository.findByEmail(email).isPresent();
        
    }
}
