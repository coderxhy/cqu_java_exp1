package com.code.stu.Service;

import com.code.stu.Service.impl.TeacherService;
import com.code.stu.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TeacherServiceTest {
    private TeacherService teacherService=new TeacherService();

    @Test
    public void testTeacherGenerator(){
        ArrayList<Teacher> teacher=teacherService.randomGenerateInfo();
        teacher.forEach(teacherService::viewTeacherAllInfo);
    }
}
