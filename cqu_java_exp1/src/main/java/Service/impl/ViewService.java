package Service.impl;

import Service.ViewInterface;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.stereotype.Service;
@Service
public class ViewService implements ViewInterface {
    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
        System.out.println("6. 退出系统");
    }
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
}
