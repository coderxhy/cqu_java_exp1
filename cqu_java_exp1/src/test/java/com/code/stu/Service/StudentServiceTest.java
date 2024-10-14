package com.code.stu.Service;

import com.code.stu.Service.impl.StudentService;
import com.code.stu.entity.Student;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.cquer.Main.testStudent;


public class StudentServiceTest {

    private StudentService studentService=new StudentService();


    @Test
    public void testStudentGenerator() {
        ArrayList<Student> stu = studentService.randomGenerateInfo();
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
                .sex("男").department("Computer Science Department").major("Computer Science Major")
                .grade("Grade Third").classId("03")
                .build();
        String res=studentService.updateStuInfo("20221545",stu,stuArray);
        System.out.println(res);
    }
}
