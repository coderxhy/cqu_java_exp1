package com.code.stu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;

import java.util.ArrayList;
import java.util.Optional;

public interface StudentInterface
//        extends IService<Student>
{
    ArrayList<Student> randomGenerateInfo(ArrayList<Courses> coursesList);
    String appendStuInfo(Student s,ArrayList<Student> students);
    String deleteStuById(String StuId,ArrayList<Student> students);
    String updateStuInfo(Student stu, Optional<Student> opt);
    void viewStuBasicInfo(Student s);
    void viewStuAllInfo(Student s);
}
