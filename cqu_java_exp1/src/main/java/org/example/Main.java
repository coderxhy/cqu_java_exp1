package org.example;


import Controller.StudentController;
import Service.impl.StudentService;
import Service.impl.TeacherService;
import Service.impl.ViewService;
import entity.Student;
import entity.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static  ArrayList<Student> testStudent(){
        Student stu=Student.builder().stuId("20221545").stuName("ZiyuLiu").sex("Man").build();
        Student stu2=Student.builder().stuId("20221537").stuName("HongyuXu").sex("Man").build();
        ArrayList<Student> arrayStu=new ArrayList<>();
        arrayStu.add(stu);
        arrayStu.add(stu2);
        return arrayStu;
    }
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
//        Scanner scanner = new Scanner(System.in);
//        if(scanner.nextInt()==1){
//            viewService.StudentShowInterface();
//        }
    }
    public static void testDeleteStuById(){
        StudentService studentService = new StudentService();
        ArrayList<Student> arrayStu=testStudent();
        String res=studentService.deleteStuById("20221545",arrayStu);
        System.out.println(res);
        arrayStu.forEach(studentService::viewStuBasicInfo);
    }
    public static void testAddStudent(){
        StudentService studentService = new StudentService();
        ArrayList<Student> stuArray=testStudent();
        Student stu=Student.builder().stuId("20221545").stuName("lzy").sex("Man").build();
        String res=studentService.appendStuInfo(stu,stuArray);
        System.out.println(res);
    }
    public static void testUpdateStudent(){
        StudentService studentService = new StudentService();
        ArrayList<Student> stuArray=testStudent();
        Student stu=Student.builder().stuId("20221545").stuName("BabyLiu")
                .sex("男").department("Computer Science Department").major("Computer Science Major")
                .grade("Grade Third").classId("03")
                .build();
        String res=studentService.updateStuInfo("20221545",stu,stuArray);
        System.out.println(res);
    }
    public static void main(String[] args) {
//        testStudentGenerator();
//        testTeacherGenerator();
//        testView();
//        testDeleteStuById();
//        testAddStudent();
//        testUpdateStudent();
        StudentController sc=new StudentController();
        testView();
        sc.StudentInfoManagement();
    }
}