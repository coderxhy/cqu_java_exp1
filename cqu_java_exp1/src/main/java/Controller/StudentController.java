package Controller;

import Service.impl.StudentService;
import Service.impl.ViewService;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class StudentController {
//    @Autowired
    private StudentService studentService=new StudentService();
//    @Autowired
    private ViewService viewService=new ViewService();

    private ArrayList<Student> students=studentService.randomGenerateInfo();

    public void StudentInfoManagement(){
        Scanner sc=new Scanner(System.in);
        switch (sc.nextInt()){
            case 1:
                viewService.StudentShowInterface();
                Student stu=viewService.AppendStudentInterface();
                String result=studentService.appendStuInfo(stu,students);
                System.out.println(result);
                studentService.viewStuAllInfo(stu);
                break;
            case 2:
                viewService.StudentShowInterface();
                break;
        }
    }
}
