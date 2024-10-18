package com.code.stu.Controller;

import com.code.stu.Service.impl.TeacherService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Teacher;


import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class TeacherController {

    private TeacherService  teacherService=new TeacherService();

    private ViewService viewService=new ViewService();

    public void  TeacherInfoManagement(ArrayList<Teacher> teachers){
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            viewService.TeacherShowInterface();
            switch (sc.nextInt()){
                case 1:
                    System.out.println("请输入你要添加的教师信息：");
                    Teacher t=viewService.TeacherInterface();//需要导入ArrayList<String> classIdList的数据进行判断
                    String appendRes=teacherService.appendTeacherInfo(t,teachers);
                    System.out.println(appendRes);
                    teacherService.viewTeacherAllInfo(t);
                    break;
                case 2:
                    System.out.println("请输入你要删除的教师信息的编号：");
                    String delelteId=sc.next();
                    String deleteRes=teacherService.deleteTeacherById(delelteId,teachers);
                    System.out.println(deleteRes);
                    break;
                case 3:
                    System.out.println("请输入你要更新的教师信息的编号：");
                    String updateId=sc.next();
                    Optional<Teacher> opt=teachers.stream().filter((Teacher s)->s.getTeacherId().equals(updateId)).findFirst();
                    if(opt.isPresent()){
                        System.out.println("这是你将要更新的教师的信息：");
                        teacherService.viewTeacherAllInfo(opt.get());
                        System.out.println("请输入你要更新的教师的信息：");
                        Teacher updateT=viewService.TeacherInterface();
                        String updateRes = teacherService.updateTeacherInfo(updateT,opt);
                        System.out.println(updateRes);
                        break;
                    }else {
                        System.out.println("对应教师编号不存在，更新失败，请确认编号无误后再进行更新操作！");
                        break;
                    }
                case 4:
                    System.out.println("全部教师的信息如下：");
                    teachers.forEach(teacherService::viewTeacherAllInfo);
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
