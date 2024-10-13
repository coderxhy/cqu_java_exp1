package org.example;


import Service.impl.StudentService;
import Service.impl.TeacherService;
import Service.impl.ViewService;
import entity.Student;
import entity.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

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
    public static void testView(){
        ViewService viewService = new ViewService();
        viewService.welcomeInterface();
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextInt()==1){
            viewService.StudentShowInterface();
        }
    }
    public static void testDeleteStuById(){
        StudentService studentService = new StudentService();
        Student stu=Student.builder().stuId("20221545").stuName("ZiyuLiu").sex("Man").build();
        Student stu2=Student.builder().stuId("20221537").stuName("HongyuXu").sex("Man").build();
        ArrayList<Student> arrayStu=new ArrayList<>();
        arrayStu.add(stu);
        arrayStu.add(stu2);
        String res=studentService.deleteStuById("20221545",arrayStu);
        System.out.println(res);
        arrayStu.forEach(studentService::viewStuBasicInfo);
    }
    public static void main(String[] args) {
//        testStudentGenerator();
//        testTeacherGenerator();
//        testView();
        testDeleteStuById();
    }
}