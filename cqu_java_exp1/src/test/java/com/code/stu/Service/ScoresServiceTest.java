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
    private ScoresController scoresController = new ScoresController();
    private ViewService viewService = new ViewService();
    private StudentService studentService=new StudentService();
    private CoursesService coursesService = new CoursesService();
    ArrayList<Courses> courses=coursesService.randomGenerateInfo();
    private ScoresService scoresService = new ScoresService();
    ArrayList<Student> stu = studentService.randomGenerateInfo(courses);
    @Test
    public void testScoresQueryScoresByClassId(){
//        stu.forEach(studentService::viewStuAllInfo);
        String res =  scoresService.queryScoresByClassId("CLASS0",stu,courses);
        System.out.println(res);
    }
    @Test
    public void testScoresQueryScoresByStudentId(){
//        ArrayList<Student> stu = studentService.randomGenerateInfo(courses);
        stu.forEach(studentService::viewStuAllInfo);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生学号：");
        String stuId = sc.next();
        String res =  scoresService.queryScoresByStuId(stuId,stu);
        System.out.println(res);
    }
    @Test
    public void testScoresQueryScoresByStudentName(){
//        ArrayList<Student> stu = studentService.randomGenerateInfo(courses);
        stu.forEach(studentService::viewStuAllInfo);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生姓名：");
        String stuName = sc.next();
        String res =  scoresService.queryScoresByStuName(stuName,stu);
        System.out.println(res);
    }
    @Test
    public void testScoresQueryScoresShowDistributionOfScores(){
//        ArrayList<Student> stu = studentService.randomGenerateInfo(courses);
        stu.forEach(studentService::viewStuAllInfo);
        scoresService.showDistributionOfScores(stu);


    }
    @Test
    public void showScoresOfAllStudents(){
//        ArrayList<Student> stu = studentService.randomGenerateInfo(courses);
        //stu.forEach(studentService::viewStuAllInfo);
        String res =  scoresService.showScoresOfAllStudents(stu);
        System.out.println(res);
    }
    @Test
    public void testScoresController(){
        scoresController.ScoresInfoQuery(stu,courses);
    }
}
