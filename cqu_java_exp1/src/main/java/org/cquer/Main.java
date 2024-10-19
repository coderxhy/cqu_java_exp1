package org.cquer;

import com.code.stu.Controller.*;
import com.code.stu.Service.impl.*;
import com.code.stu.entity.Classes;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Courses> courses=CoursesService.getInstance().randomGenerateInfo();
        ArrayList<Student> students=StudentService.getInstance().randomGenerateInfo(courses);
        ArrayList<Teacher> teachers=TeacherService.getInstance().randomGenerateInfo(courses);
        ArrayList<Classes> classes;
        boolean inSystem = true;
        while (inSystem) {
            ViewService.getInstance().welcomeInterface();
            switch (scanner.nextInt()){
                case 1:
                    //学生信息管理
                    StudentController sc=new StudentController();
                    classes = ClassesService.getInstance().buildClasses(CoursesService.getInstance().classIdArray, courses, students, teachers);
                    sc.StudentInfoManagement(students,classes);
                    break;
                case 2:
                    //教师信息管理
                    TeacherController tc=new TeacherController();
                    tc.TeacherInfoManagement(teachers);
                    break;
                case 3:
                    //课程信息管理
                    CoursesController cc=new CoursesController();
                    classes = ClassesService.getInstance().buildClasses(CoursesService.getInstance().classIdArray, courses, students, teachers);
                    cc.CoursesInfoManagement(students,teachers,courses,classes);
                    break;
                case 4:
                    //成绩信息管理
                    ScoresController.getInstance().ScoresUpdateInfo(students,courses);
                    break;
                case 5:
                    ClassesController cct=new ClassesController();
                    classes = ClassesService.getInstance().buildClasses(CoursesService.getInstance().classIdArray, courses, students, teachers);
                    cct.ClassesInfoManagement(classes);
                    break;
                case 6:
                    //成绩查询系统
                    ScoresController.getInstance().ScoresInfoQuery(students,courses);
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