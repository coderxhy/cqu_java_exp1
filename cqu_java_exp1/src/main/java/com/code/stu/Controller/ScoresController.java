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
    private ScoresService scoresService = new ScoresService();
    private ViewService viewService = new ViewService();

    public void ScoresInfoQuery(ArrayList<Student> students, ArrayList<Courses> courses){
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            viewService.QueryScoresShowInterface();
            switch (sc.nextInt()){
                case 1: //
                    System.out.println("请输入你要查询的教学班号：");
                    String classId=sc.next();
                    String res = scoresService.queryScoresByClassId(classId,students,courses);
                    System.out.println(res);
                    break;

                case 2:
                    System.out.println("请输入你要查询的学生学号：");
                    String stuId=sc.next();
                    String res1 = scoresService.queryScoresByStuId(stuId,students);
                    System.out.println(res1);
                    break;

                case 3:
                    System.out.println("请输入你要查询的学生姓名：");
                    String stuName=sc.next();
                    String res2 = scoresService.queryScoresByStuName(stuName,students);
                    System.out.println(res2);
                    break;

                case 4:
                    scoresService.showDistributionOfScores(students);
                    break;

                case 5:
                    String res4 = scoresService.showScoresOfAllStudents(students);
                    System.out.println(res4);
                    break;

                case 6:
                    flag=false;
                    viewService.welcomeInterface();
                    break;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }


        }

    }

}
