package org.cquer;


import com.code.stu.Controller.StudentController;
import com.code.stu.Service.impl.StudentService;
import com.code.stu.Service.impl.TeacherService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;

import java.util.ArrayList;

public class Main {
    public static  ArrayList<Student> testStudent(){
        Student stu=Student.builder().stuId("20221545").stuName("ZiyuLiu").sex("Man").build();
        Student stu2=Student.builder().stuId("20221537").stuName("HongyuXu").sex("Man").build();
        ArrayList<Student> arrayStu=new ArrayList<>();
        arrayStu.add(stu);
        arrayStu.add(stu2);
        return arrayStu;
    }
    public static void testView(){
        ViewService viewService = new ViewService();
        viewService.welcomeInterface();
    }

    public static void main(String[] args) {
        StudentController sc=new StudentController();
        testView();
        sc.StudentInfoManagement();
    }
}