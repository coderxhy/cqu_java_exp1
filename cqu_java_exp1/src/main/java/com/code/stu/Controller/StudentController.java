package com.code.stu.Controller;

import com.code.stu.Service.impl.StudentService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class StudentController {

    public void StudentInfoManagement(ArrayList<Student> students, ArrayList<Courses> courses){
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            ViewService.getInstance().StudentShowInterface();
            switch (sc.nextInt()){
                case 1:
                    System.out.println("请输入你要添加的学生信息：");
                    Student stu=ViewService.getInstance().StudentInterface();
                    String appendRes=StudentService.getInstance().appendStuInfo(stu,students);
                    System.out.println(appendRes);
                    StudentService.getInstance().viewStuAllInfo(stu);
                    break;
                case 2:
                    System.out.println("请输入你要删除的学生信息的学号：");
                    String delelteId=sc.next();
                    String deleteRes=StudentService.getInstance().deleteStuById(delelteId,students);
                    System.out.println(deleteRes);
                    break;
                case 3:
                    System.out.println("请输入你要更新的学生信息的学号：");
                    String updateId=sc.next();
                    Optional<Student> opt=students.stream().filter((Student s)->s.getStuId().equals(updateId)).findFirst();
                    if(opt.isPresent()){
                        System.out.println("这是你将要更新的学生的基本信息：");
                        StudentService.getInstance().viewStuBasicInfo(opt.get());
                        System.out.println("请输入你要更新的学生信息：");
                        Student updateStu= ViewService.getInstance().StudentInterface();
                        String updateRes = StudentService.getInstance().updateStuInfo(updateStu,opt);
                        System.out.println(updateRes);
                        break;
                    }else {
                        System.out.println("对应学号学生不存在，更新失败，请确认学号无误后再进行更新操作！");
                        break;
                    }
                case 4:
                    System.out.println("全部学生的基本信息如下：");
                    students.forEach(StudentService.getInstance()::viewStuBasicInfo);
                    break;
                case 5:
                    System.out.println("全部学生的所有信息如下：");
                    students.forEach(StudentService.getInstance()::viewStuAllInfo);
                    break;
                case 6:
                    flag=false;
                    break;
                default:
                    System.out.println("输入数字无效，请重新输入！");
                    break;
            }
        }
    }
}
