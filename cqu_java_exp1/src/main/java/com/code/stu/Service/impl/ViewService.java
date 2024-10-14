package com.code.stu.Service.impl;

import com.code.stu.Service.ViewInterface;
import com.code.stu.entity.Student;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Service
public class ViewService implements ViewInterface {
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("/bin/sh", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void welcomeInterface(){
        clearScreen();
        System.out.println("欢迎使用学生成绩管理系统！！！");
        String art =
                        " W   W  EEEEE  L      CCCC  OOO  M   M  EEEEE \n" +
                        " W W W  E      L     C     O   O MM MM  E     \n" +
                        " W W W  EEEEE  L     C     O   O M M M  EEEEE \n" +
                        " W W W  E      L     C     O   O M   M  E     \n" +
                        " W   W  EEEEE  LLLLL  CCCC  OOO  M   M  EEEEE \n";

        System.out.println(art);
        System.out.println("请按照提示进行操作：");
        System.out.println("1. 学生信息管理");
        System.out.println("2. 教师信息管理");
        System.out.println("3. 课程信息管理");
        System.out.println("4. 成绩信息管理");
        System.out.println("5. 教学班信息管理");
        System.out.println("6. 查询系统");
        System.out.println("7. 退出系统");
    }
    @Override
    public void StudentShowInterface(){
        clearScreen();
        System.out.println("学生信息管理");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 删除学生信息");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 查看学生信息");
        System.out.println("5. 查看所有学生信息");
        System.out.println("6. 返回上一级");
    }
    @Override
    public Student AppendStudentInterface(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你要添加的学生信息：");
        System.out.println("姓名：");
        String name = sc.next();
        System.out.println("学号：");
        String id = sc.next();
        System.out.println("性别：");
        String sex=sc.next();
        System.out.println("学院：");
        String department=sc.next();
        System.out.println("专业：");
        String major=sc.next();
        System.out.println("年级：");
        String grade=sc.next();
        System.out.println("教学班号：");
        String classId=sc.next();
        Student stu=Student.builder().stuName(name).stuId(id)
                .sex(sex).department(department).major(major)
                .classId(classId).grade(grade).build();
        return stu;
    }
    @Override
    public void TeacherShowInterface(){
        clearScreen();
        System.out.println("教师信息管理");
        System.out.println("1. 添加教师信息");
        System.out.println("2. 删除教师信息");
        System.out.println("3. 修改教师信息");
        System.out.println("4. 查看教师信息");
        System.out.println("5. 返回上一级");
    }
    @Override
    public void CourseShowInterface(){
        clearScreen();
        System.out.println("课程信息管理");
        System.out.println("1. 添加课程信息");
        System.out.println("2. 删除课程信息");
        System.out.println("3. 修改课程信息");
        System.out.println("4. 查看课程信息");
        System.out.println("5. 返回上一级");
    }@Override
    public void ScoresShowInterface(){
        clearScreen();
        System.out.println("成绩信息管理");
        System.out.println("1. 添加成绩信息");
        System.out.println("2. 删除成绩信息");
        System.out.println("3. 修改成绩信息");
        System.out.println("4. 查看成绩信息");
        System.out.println("5. 返回上一级");
    }
    @Override
    public void ClassShowInterface(){
        clearScreen();
        System.out.println("教学班信息管理");
        System.out.println("1. 添加教学班信息");
        System.out.println("2. 删除教学班信息");
        System.out.println("3. 修改教学班信息");
        System.out.println("4. 查看教学班信息");
        System.out.println("5. 返回上一级");
    }
    public void QueryScoresShowInterface(){
        clearScreen();
        System.out.println("成绩查询系统");
        System.out.println("1. 教学班号查询教学班级学生成绩"); //需要键入一个classId
        System.out.println("2. 学号查询学生的成绩"); //需要键入一个stuId
        System.out.println("3. 姓名查询学生的成绩"); //需要键入一个stuName
        System.out.println("4. 查看成绩分布情况");
        System.out.println("5. 查看所有学生的成绩");
        System.out.println("6. 返回上一级");
    }

}
