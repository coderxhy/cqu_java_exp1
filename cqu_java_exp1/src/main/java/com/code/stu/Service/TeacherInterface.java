package com.code.stu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Teacher;

import java.util.ArrayList;
import java.util.Optional;

public interface TeacherInterface
//        extends IService<Teacher>
{
    ArrayList<Teacher> randomGenerateInfo(ArrayList<Courses> coursesList);
    String appendTeacherInfo(Teacher t,ArrayList<Teacher> teachers);
    String deleteTeacherById(String TeacherId,ArrayList<Teacher> teachers);
    String updateTeacherInfo(Teacher teacher,Optional<Teacher> opt);
    void viewTeacherAllInfo(Teacher t);
}
