package Service.impl;

import Service.ViewInterface;
import entity.Student;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
                         "W   W  EEEEE  L      CCCC  OOO  M   M  EEEEE \n" +
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
        System.out.println("6. 退出系统");
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
}
