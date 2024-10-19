package com.code.stu.Service;

import com.code.stu.entity.Classes;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;

import java.util.ArrayList;
import java.util.Map;

public interface ClassesInterface {
    Map<String, Long> countStudentPerClass(ArrayList<Student> students);
    ArrayList<Classes> buildClasses(ArrayList<String> classIdArray, ArrayList<Courses> coursesArray,
                                    ArrayList <Student> stuArray, ArrayList<Teacher> teacherArray);
/*    ArrayList<Classes> updateClasses(ArrayList<String> classIdArray,ArrayList<Courses> coursesArray,
                                     ArrayList <Classes> classes);*/
    void showClasses(Classes signalClass);
}
