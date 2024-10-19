package com.code.stu.Service;

import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.TeacherService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TeacherServiceTest {
    @Test
    public void testTeacherGenerator(){
        ArrayList<Courses> coursesList = CoursesService.getInstance().randomGenerateInfo();
        coursesList.forEach(System.out::println);
        System.out.println();
        ArrayList<Teacher> teacher=TeacherService.getInstance().randomGenerateInfo(coursesList);
        teacher.forEach(TeacherService.getInstance()::viewTeacherAllInfo);
    }
}
