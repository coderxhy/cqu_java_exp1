package com.code.stu.Service;

import com.code.stu.Service.impl.ClassesService;
import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.StudentService;
import com.code.stu.Service.impl.TeacherService;
import com.code.stu.entity.Classes;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ClassesServiceTest {



    @Test
    public void testBuildClasses(){
        System.out.println("以下是课程ArrayList信息：");
        ArrayList<Courses> coursesList=CoursesService.getInstance().randomGenerateInfo();
        coursesList.forEach(System.out::println);
        System.out.println();

        System.out.println("以下是学生ArrayList信息：");
        ArrayList<Student> stu = StudentService.getInstance().randomGenerateInfo(coursesList);
        stu.forEach(StudentService.getInstance()::viewStuAllInfo);
        System.out.println();

        System.out.println("以下是教师ArrayList信息：");
        ArrayList<Teacher> teacher=TeacherService.getInstance().randomGenerateInfo(coursesList);
        teacher.forEach(TeacherService.getInstance()::viewTeacherAllInfo);
        System.out.println();

        System.out.println("以下是教学班ArrayList信息：");
        ArrayList<Classes> classes=ClassesService.getInstance().buildClasses(CoursesService.getInstance().classIdArray,coursesList,stu,teacher);
        classes.forEach(System.out::println);
        System.out.println();
    }
}
