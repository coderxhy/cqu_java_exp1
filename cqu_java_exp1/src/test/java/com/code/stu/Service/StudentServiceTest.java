package com.code.stu.Service;

import com.code.stu.Service.impl.CoursesService;
import com.code.stu.Service.impl.StudentService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Optional;



public class StudentServiceTest {
    public static  ArrayList<Student> testStudent(){
        Student stu=Student.builder().stuId("20221545").stuName("ZiyuLiu").sex("Man").build();
        Student stu2=Student.builder().stuId("20221537").stuName("HongyuXu").sex("Man").build();
        ArrayList<Student> arrayStu=new ArrayList<>();
        arrayStu.add(stu);
        arrayStu.add(stu2);
        return arrayStu;
    }
    ArrayList<Courses> courses=CoursesService.getInstance().randomGenerateInfo();

    @Test
    public void testStudentGenerator() {
        ArrayList<Student> stu = StudentService.getInstance().randomGenerateInfo(courses);
        stu.forEach(StudentService.getInstance()::viewStuBasicInfo);
    }

    @Test
    public void testDeleteStuById(){
        ArrayList<Student> arrayStu=testStudent();
        String res=StudentService.getInstance().deleteStuById("20221545",arrayStu);
        System.out.println(res);
        arrayStu.forEach(StudentService.getInstance()::viewStuBasicInfo);
    }

    @Test
    public void testAddStudent(){
        ArrayList<Student> stuArray=testStudent();
        Student stu=Student.builder().stuId("20221545").stuName("lzy").sex("Man").build();
        String res=StudentService.getInstance().appendStuInfo(stu,stuArray);
        System.out.println(res);
    }

    @Test
    public void testUpdateStudent(){
        ArrayList<Student> stuArray=testStudent();
        Student stu=Student.builder().stuId("20221545").stuName("BabyLiu")
                .sex("").department("Computer Science Department").major("Computer Science Major")
                .grade("Grade Third")
                .build();
        Optional<Student> opt=stuArray.stream().filter((Student s)->s.getStuId().equals((stu.getStuId()))).findFirst();
        String res=StudentService.getInstance().updateStuInfo(stu,opt);
        System.out.println(res);
    }
}
