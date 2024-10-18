package org.cquer;


import com.code.stu.Controller.CoursesController;
import com.code.stu.Controller.StudentController;
import com.code.stu.Controller.TeacherController;
import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.StudentService;
import com.code.stu.Service.impl.TeacherService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Courses;
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

        CoursesService coursesService = new CoursesService();
        ArrayList<Courses> courses=coursesService.randomGenerateInfo();

        StudentService studentService = new StudentService();
        ArrayList<Student> students=studentService.randomGenerateInfo(courses);

        TeacherService teacherService = new TeacherService();
        ArrayList<Teacher> teachers=teacherService.randomGenerateInfo(courses);


        boolean inSystem = true;
        while (inSystem) {
            testView();
            switch (scanner.nextInt()){
                case 1:
                    //学生信息管理
                    StudentController sc=new StudentController();
                    sc.StudentInfoManagement(students,courses);
                    break;
                case 2:
                    //教师信息管理
                    TeacherController tc=new TeacherController();
                    tc.TeacherInfoManagement(teachers);
                    break;
                case 3:
                    //课程信息管理
                    CoursesController cc=new CoursesController();
                    cc.CoursesInfoManagement(students,teachers,courses);
                    break;
                case 4:
                    //成绩信息管理
                    break;
                case 5:
                    //教学班信息管理
                    break;
                case 6:
                    //成绩查询系统
                    break;
                case 7:
                    //退出系统
                    inSystem=false;
                    break;
                default:
                    System.out.println("输入的选择命令错误，请依照提示在1-7中进行输入");
            }
        }
        System.out.println("成功退出系统，欢迎您的再次使用！");
    }
}