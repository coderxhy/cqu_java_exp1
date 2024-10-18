package com.code.stu.Service.impl;

import com.code.stu.Service.ClassesInterface;
import com.code.stu.entity.Classes;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ClassesService implements ClassesInterface {
    public final String[] beginTerms = {"2022年秋", "2023年春", "2023年秋", "2024年春", "2024年秋"};

    @Override
    public Map<String, Long> countStudentPerClass(ArrayList<Student> students) {

            return students.stream()
                    .flatMap(student -> student.getSelectedCourses().values().stream())
                    .collect(Collectors.groupingBy(classId -> classId, Collectors.counting()));
    }

    @Override
    public ArrayList<Classes> buildClasses(ArrayList<String> classIdArray, ArrayList<Courses> coursesArray,
    ArrayList<Student> stuArray, ArrayList<Teacher> teacherArray) {
        ArrayList<Classes> classes = new ArrayList<>();
        Random r = new Random();
        for(String classId : classIdArray) {
            Optional<Teacher> teacherOpt = teacherArray.stream().filter(t -> t.getClassId().contains(classId)).findFirst();
            Optional<Courses> coursesOpt = coursesArray.stream().filter(c -> c.getClassId().contains(classId)).findFirst();
            Map<String,Long> countMap =countStudentPerClass(stuArray);
            //由于分配一定是存在的，直接teacherOpt.get()即可
            Classes eachClass=Classes.builder().teacherId(teacherOpt.get().getTeacherId())
                    .courseName(coursesOpt.get().getCourseName())
                    .totalStudentNum(Math.toIntExact(countMap.get(classId)))
                    .classId(classId)
                    .BeginTerm(beginTerms[r.nextInt(beginTerms.length)])
                    .build();
            classes.add(eachClass);
        }
        return classes;
    }

    @Override
    public void showClasses(Classes signalClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("教学班号：").append(signalClass.getClassId())
                .append("\t课程名称：").append(signalClass.getCourseName())
                .append("\t教师编号：").append(signalClass.getTeacherId())
                .append("\t总人数:").append(signalClass.getTotalStudentNum())
                .append("\t开课学期:").append(signalClass.getBeginTerm())
                .append("\n");
        System.out.println(sb);
    }
}
