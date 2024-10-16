package com.code.stu.Service;

import com.code.stu.entity.Courses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface CoursesInterface
//        extends IService<Courses>
{
    ArrayList<Courses> randomGenerateInfo();
    Map.Entry<String, String> GenerateEntry();
    HashMap<String, String> GenerateCourseIds();
    ArrayList<String> GenerateClassId();
}
