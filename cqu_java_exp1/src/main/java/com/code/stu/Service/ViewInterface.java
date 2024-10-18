package com.code.stu.Service;

import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ViewInterface {
     void welcomeInterface();
     void StudentShowInterface();
     Student StudentInterface();
     void TeacherShowInterface();
     Teacher TeacherInterface();
     void CourseShowInterface();
     Optional<Courses> CourseInterface(List<String> classIdArray, ArrayList<Courses> courses);
     void ScoresShowInterface();
     void ClassShowInterface();
}
