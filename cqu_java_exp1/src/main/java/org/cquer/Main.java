package org.cquer;


import com.code.stu.Controller.StudentController;
import com.code.stu.Service.impl.StudentService;
import com.code.stu.Service.impl.TeacherService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;

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
    public static void testView(){
        ViewService viewService = new ViewService();
        viewService.welcomeInterface();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean inSystem = true;
        while (inSystem) {
            testView();
            switch (scanner.nextInt()){
                case 1:
                    StudentController sc=new StudentController();
                    sc.StudentInfoManagement();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    inSystem=false;
                    break;
                default:
                    System.out.println("输入的选择命令错误，请依照提示在1-7中进行输入");
            }
        }
        System.out.println("成功退出系统，欢迎您的再次使用！");
    }
}