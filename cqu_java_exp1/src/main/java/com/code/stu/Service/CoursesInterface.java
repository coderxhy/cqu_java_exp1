package com.code.stu.Service;

import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface CoursesInterface
//        extends IService<Courses>
{
    ArrayList<Courses> randomGenerateInfo();
    Map.Entry<String, String> GenerateEntry();
    HashMap<String, String> GenerateCourseIds();
    ArrayList<String> GenerateClassId();
    String appendCourses(Courses course,ArrayList<Courses> courses);
    Optional<Courses> findCourse(ArrayList<Courses> courses);
    void  showCourseAllInfo(Courses c);
    void alterCourseShow(ArrayList<Courses> courses);
    void assignCourse(ArrayList<Student> students, ArrayList<Teacher> teachers,ArrayList<Courses> courses);
}
