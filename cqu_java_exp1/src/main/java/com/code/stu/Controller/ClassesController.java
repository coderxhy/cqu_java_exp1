package com.code.stu.Controller;

import com.code.stu.Service.impl.ClassesService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Classes;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassesController {
    private ClassesService classesService = new ClassesService();
    private ViewService viewService = new ViewService();

    public void ClassesInfoManagement(ArrayList<Classes> classes){
        Scanner sc = new Scanner(System.in);
        boolean classFlag = true;
        while(classFlag){
            viewService.ClassShowInterface();
            switch (sc.nextInt()){
                case 1:
                    classes.forEach(classesService::showClasses);
                    break;
                case 2:
                    classFlag = false;
                    break;
                default:
                    System.out.println("输入数字无效，请重新输入！");
                    break;
            }
        }
    }
}
