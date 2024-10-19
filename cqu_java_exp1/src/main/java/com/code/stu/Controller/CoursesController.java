package com.code.stu.Controller;

import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.ViewService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CoursesController {

    public void CoursesInfoManagement(ArrayList<Student> students, ArrayList<Teacher> teachers,ArrayList<Courses> courses){
        Scanner sc=new Scanner(System.in);
        boolean courseFlag=true;
        while(courseFlag){
            ViewService.getInstance().CourseShowInterface();
            switch(sc.nextInt()){
                case 1:
                    System.out.println("请输入你要添加的课程信息：");
                    Optional<Courses> courseOpt= ViewService.getInstance().CourseInterface(CoursesService.getInstance().classIdArray,courses);
                    if (courseOpt.isPresent()) {
                        String appendRes = CoursesService.getInstance().appendCourses(courseOpt.get(), courses);
                        System.out.println(appendRes);
                        CoursesService.getInstance().showCourseAllInfo(courseOpt.get());
                        System.out.println("请尽快为该课程分配老师和学生!");
                    }
                    break;
                case 2:
                    CoursesService.getInstance().assignCourse(students,teachers,courses);
                    break;
                case 3:
                    CoursesService.getInstance().alterCourseShow(courses);
                    break;
                case 4:
                    courseFlag=false;
                    break;
                default:
                    System.out.println("输入数字无效，请重新输入！");
                    break;
            }
        }
    }
}
