package com.code.stu.Service;

import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.TeacherService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TeacherServiceTest {
    private TeacherService teacherService=new TeacherService();
    private CoursesService coursesService=new CoursesService();
    @Test
    public void testTeacherGenerator(){
        ArrayList<Courses> coursesList = coursesService.randomGenerateInfo();
        coursesList.forEach(System.out::println);
        System.out.println();
        ArrayList<Teacher> teacher=teacherService.randomGenerateInfo(coursesList);
        teacher.forEach(teacherService::viewTeacherAllInfo);
    }
}
