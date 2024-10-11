package org.example;


import Service.impl.StudentService;
import Service.impl.ViewService;
import entity.Student;

import java.util.ArrayList;

public class Main {
    public static  void testStudentGenerator(){
        StudentService studentService = new StudentService();
        ArrayList<Student> stu=studentService.randomGenerateInfo();
        stu.forEach(studentService::viewStuBasicInfo);
    }
    public static void main(String[] args) {
        testStudentGenerator();
    }
}