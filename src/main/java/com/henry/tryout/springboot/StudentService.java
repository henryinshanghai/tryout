package com.henry.tryout.springboot;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void addStudent(Student student) {
        System.out.println(student + "has been added.");
    }
}
