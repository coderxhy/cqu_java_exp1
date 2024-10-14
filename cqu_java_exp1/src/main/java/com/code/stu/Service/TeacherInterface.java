package com.code.stu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.stu.entity.Teacher;

import java.util.ArrayList;

public interface TeacherInterface
//        extends IService<Teacher>
{
    ArrayList<Teacher> randomGenerateInfo();
    String appendTeacherInfo(Teacher t,ArrayList<Teacher> teachers);
    String deleteTeacherById(String TeacherId,ArrayList<Teacher> teachers);
    String updateTeacherInfo(String TeacherId,ArrayList<Teacher>teachers);
    void viewTeacherAllInfo(Teacher t);
}
