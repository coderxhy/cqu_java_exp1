package org.example;


import Service.impl.StudentService;
import Service.impl.ViewService;
import entity.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    @Test
    public  void testStudentGenerator(){
        StudentService studentService = new StudentService();
        ArrayList<Student> stu=studentService.randomGenerateInfo();
        stu.forEach(studentService::viewStuBasicInfo);
    }
    @Test
    public void testViewService(){
        ViewService viewService = new ViewService();
        viewService.welcomeInterface();
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextInt()==1){
            viewService.StudentShowInterface();
        }
    }
//    public static void main(String[] args) {
//        testStudentGenerator();
//    }
}