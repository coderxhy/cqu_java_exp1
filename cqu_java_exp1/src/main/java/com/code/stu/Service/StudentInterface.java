package com.code.stu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.stu.entity.Student;

import java.util.ArrayList;

public interface StudentInterface
//        extends IService<Student>
{
    ArrayList<Student> randomGenerateInfo();
    String appendStuInfo(Student s,ArrayList<Student> students);
    String deleteStuById(String StuId,ArrayList<Student> students);
    String updateStuInfo(String StuId,Student stu,ArrayList<Student> students);
    void viewStuBasicInfo(Student s);
    void viewStuAllInfo(Student s);
}
