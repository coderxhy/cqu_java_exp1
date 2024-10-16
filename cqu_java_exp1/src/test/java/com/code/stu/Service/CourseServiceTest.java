package com.code.stu.Service;

import com.code.stu.Service.impl.CoursesService;
import com.code.stu.entity.Courses;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CourseServiceTest {
    private CoursesService coursesService=new CoursesService();

    @Test
    public void testRandomGenerateInfo(){
        ArrayList<Courses> courseLists=coursesService.randomGenerateInfo();
        courseLists.forEach(System.out::println);
    }
}
