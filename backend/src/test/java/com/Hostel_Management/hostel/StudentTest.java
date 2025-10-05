package com.Hostel_Management.hostel;

import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class StudentTest {
    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void teststudentrep(){
        List<Student>studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

}
