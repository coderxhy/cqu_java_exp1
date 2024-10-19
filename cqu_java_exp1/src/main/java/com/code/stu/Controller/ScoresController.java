package com.code.stu.Controller;

import com.code.stu.Service.impl.ScoresService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class ScoresController {

    public void ScoresInfoQuery(ArrayList<Student> students, ArrayList<Courses> courses){
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            ViewService.getInstance().QueryScoresShowInterface();
            switch (sc.nextInt()){
                case 1: //
                    System.out.println("请输入你要查询的教学班号：");
                    String classId=sc.next();
                    String res = ScoresService.getInstance().queryScoresByClassId(classId,students,courses);
                    System.out.println(res);
                    break;

                case 2:
                    System.out.println("请输入你要查询的学生学号：");
                    String stuId=sc.next();
                    String res1 = ScoresService.getInstance().queryScoresByStuId(stuId,students);
                    System.out.println(res1);
                    break;

                case 3:
                    System.out.println("请输入你要查询的学生姓名：");
                    String stuName=sc.next();
                    String res2 = ScoresService.getInstance().queryScoresByStuName(stuName,students);
                    System.out.println(res2);
                    break;

                case 4:
                    ScoresService.getInstance().showDistributionOfScores(students);
                    break;

                case 5:
                    String res4 = ScoresService.getInstance().showScoresOfAllStudents(students);
                    System.out.println(res4);
                    break;

                case 6:
                    flag=false;
                    ViewService.getInstance().welcomeInterface();
                    break;

                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }
}
