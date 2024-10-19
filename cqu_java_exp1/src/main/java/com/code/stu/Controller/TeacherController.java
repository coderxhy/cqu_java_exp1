package com.code.stu.Controller;

import com.code.stu.Service.impl.TeacherService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Teacher;


import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class TeacherController {

    public void  TeacherInfoManagement(ArrayList<Teacher> teachers){
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            ViewService.getInstance().TeacherShowInterface();
            switch (sc.nextInt()){
                case 1:
                    System.out.println("请输入你要添加的教师信息：");
                    Teacher t=ViewService.getInstance().TeacherInterface();//需要导入ArrayList<String> classIdList的数据进行判断
                    String appendRes=TeacherService.getInstance().appendTeacherInfo(t,teachers);
                    System.out.println(appendRes);
                    TeacherService.getInstance().viewTeacherAllInfo(t);
                    break;
                case 2:
                    System.out.println("请输入你要删除的教师信息的编号：");
                    String delelteId=sc.next();
                    String deleteRes=TeacherService.getInstance().deleteTeacherById(delelteId,teachers);
                    System.out.println(deleteRes);
                    break;
                case 3:
                    System.out.println("请输入你要更新的教师信息的编号：");
                    String updateId=sc.next();
                    Optional<Teacher> opt=teachers.stream().filter((Teacher s)->s.getTeacherId().equals(updateId)).findFirst();
                    if(opt.isPresent()){
                        System.out.println("这是你将要更新的教师的信息：");
                        TeacherService.getInstance().viewTeacherAllInfo(opt.get());
                        System.out.println("请输入你要更新的教师的信息：");
                        Teacher updateT=ViewService.getInstance().TeacherInterface();
                        String updateRes = TeacherService.getInstance().updateTeacherInfo(updateT,opt);
                        System.out.println(updateRes);
                        break;
                    }else {
                        System.out.println("对应教师编号不存在，更新失败，请确认编号无误后再进行更新操作！");
                        break;
                    }
                case 4:
                    System.out.println("全部教师的信息如下：");
                    teachers.forEach(TeacherService.getInstance()::viewTeacherAllInfo);
                    break;
                case 5:
                    flag=false;
                    break;
                default:
                    System.out.println("输入数字无效，请重新输入！");
                    break;
            }
        }
    }
}
