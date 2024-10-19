package com.code.stu.Service;

import com.code.stu.Controller.ScoresController;
import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.ScoresService;
import com.code.stu.Service.impl.StudentService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoresServiceTest {
    ArrayList<Courses> courses=CoursesService.getInstance().randomGenerateInfo();
    ArrayList<Student> stu = StudentService.getInstance().randomGenerateInfo(courses);
    @Test
    public void testScoresQueryScoresByClassId(){
//        stu.forEach(Student.getInstance( :viewStuAllInfo);
        String res =  ScoresService.getInstance().queryScoresByClassId("CLASS0",stu,courses);
        System.out.println(res);
    }
    @Test
    public void testScoresQueryScoresByStudentId(){
//        ArrayList<Student> stu = Student.getInstance( randomGenerateInfo(courses);
        stu.forEach(StudentService.getInstance()::viewStuAllInfo);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生学号：");
        String stuId = sc.next();
        String res =  ScoresService.getInstance().queryScoresByStuId(stuId,stu);
        System.out.println(res);
    }
    @Test
    public void testScoresQueryScoresByStudentName(){
//        ArrayList<Student> stu = Student.getInstance( randomGenerateInfo(courses);
        stu.forEach(StudentService.getInstance()::viewStuAllInfo);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生姓名：");
        String stuName = sc.next();
        String res =  ScoresService.getInstance().queryScoresByStuName(stuName,stu);
        System.out.println(res);
    }
    @Test
    public void testScoresQueryScoresShowDistributionOfScores(){
//        ArrayList<Student> stu = Student.getInstance( randomGenerateInfo(courses);
        stu.forEach(StudentService.getInstance()::viewStuAllInfo);
        ScoresService.getInstance().showDistributionOfScores(stu);


    }
    @Test
    public void showScoresOfAllStudents(){
//        ArrayList<Student> stu = Student.getInstance( randomGenerateInfo(courses);
        //stu.forEach(Student.getInstance( :viewStuAllInfo);
        String res =  ScoresService.getInstance().showScoresOfAllStudents(stu);
        System.out.println(res);
    }
    @Test
    public void testScoresController(){
        ScoresController.getInstance().ScoresInfoQuery(stu,courses);
    }
}
