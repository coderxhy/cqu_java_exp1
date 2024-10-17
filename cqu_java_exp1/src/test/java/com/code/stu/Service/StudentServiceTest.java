package com.code.stu.Service;

import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.StudentService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Optional;

import static org.cquer.Main.testStudent;


public class StudentServiceTest {

    private StudentService studentService=new StudentService();
    private CoursesService coursesService = new CoursesService();
    ArrayList<Courses> courses=coursesService.randomGenerateInfo();

    @Test
    public void testStudentGenerator() {
        ArrayList<Student> stu = studentService.randomGenerateInfo(courses);
        stu.forEach(studentService::viewStuBasicInfo);
    }

    @Test
    public void testDeleteStuById(){
        ArrayList<Student> arrayStu=testStudent();
        String res=studentService.deleteStuById("20221545",arrayStu);
        System.out.println(res);
        arrayStu.forEach(studentService::viewStuBasicInfo);
    }

    @Test
    public void testAddStudent(){
        ArrayList<Student> stuArray=testStudent();
        Student stu=Student.builder().stuId("20221545").stuName("lzy").sex("Man").build();
        String res=studentService.appendStuInfo(stu,stuArray);
        System.out.println(res);
    }

    @Test
    public void testUpdateStudent(){
        StudentService studentService = new StudentService();
        ArrayList<Student> stuArray=testStudent();
        Student stu=Student.builder().stuId("20221545").stuName("BabyLiu")
                .sex("").department("Computer Science Department").major("Computer Science Major")
                .grade("Grade Third")
                .build();
        Optional<Student> opt=stuArray.stream().filter((Student s)->s.getStuId().equals((stu.getStuId()))).findFirst();
        String res=studentService.updateStuInfo(stu,opt);
        System.out.println(res);
    }
}
