package org.example;


import Service.impl.StudentService;
import Service.impl.TeacherService;
import Service.impl.ViewService;
import entity.Student;
import entity.Teacher;

import java.util.ArrayList;

public class Main {
    public static  void testStudentGenerator(){
        StudentService studentService = new StudentService();
        ArrayList<Student> stu=studentService.randomGenerateInfo();
        stu.forEach(studentService::viewStuBasicInfo);
    }
    public static void testTeacherGenerator(){
        TeacherService teacherService = new TeacherService();
        ArrayList<Teacher> teacher=teacherService.randomGenerateInfo();
        teacher.forEach(teacherService::viewTeacherAllInfo);
    }
    public static void main(String[] args) {
//        testStudentGenerator();
        testTeacherGenerator();
    }
}