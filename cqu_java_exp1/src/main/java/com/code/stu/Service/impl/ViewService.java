package com.code.stu.Service.impl;

import com.code.stu.Service.ViewInterface;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.code.stu.Service.impl.CoursesService.CLASS_ID_LIST_SIZE;
import static com.code.stu.Service.impl.CoursesService.CLASS_NUM;

@Service
public class ViewService implements ViewInterface {
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("/bin/sh", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final int RANGE = 80;

    Random r = new Random();
    Scanner sc=new Scanner(System.in);
    @Override
    public void welcomeInterface(){
        System.out.println("欢迎使用学生成绩管理系统！！！");
        String art =
                        " W   W  EEEEE  L      CCCC  OOO  M   M  EEEEE \n" +
                        " W W W  E      L     C     O   O MM MM  E     \n" +
                        " W W W  EEEEE  L     C     O   O M M M  EEEEE \n" +
                        " W W W  E      L     C     O   O M   M  E     \n" +
                        " W   W  EEEEE  LLLLL  CCCC  OOO  M   M  EEEEE \n";

        System.out.println(art);
        System.out.println("请按照提示进行操作：");
        System.out.println("1. 学生信息管理");
        System.out.println("2. 教师信息管理");
        System.out.println("3. 课程信息管理");
        System.out.println("4. 成绩信息管理");
        System.out.println("5. 教学班信息管理");
        System.out.println("6. 成绩查询系统");
        System.out.println("7. 退出系统");
    }
    @Override
    public void StudentShowInterface(){
        System.out.println("学生信息管理");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 删除学生信息");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 查看学生基本信息");
        System.out.println("5. 查看学生所有信息");
        System.out.println("6. 返回上一级");
    }
    @Override
    public Student StudentInterface(){
        System.out.println("姓名：");
        String name = sc.nextLine();
        System.out.println("学号：");
        String id = sc.nextLine();
        System.out.println("性别：");
        String sex=sc.nextLine();
        System.out.println("学院：");
        String department=sc.nextLine();
        System.out.println("专业：");
        String major=sc.nextLine();
        System.out.println("年级：");
        String grade=sc.nextLine();
        Student stu=Student.builder().stuName(name).stuId(id)
                .sex(sex).department(department).major(major)
                .grade(grade).build();
        return stu;
    }
    @Override
    public void TeacherShowInterface(){
        System.out.println("教师信息管理");
        System.out.println("1. 添加教师信息");
        System.out.println("2. 删除教师信息");
        System.out.println("3. 修改教师信息");
        System.out.println("4. 查看教师信息");
        System.out.println("5. 返回上一级");
    }

    @Override
    public Teacher TeacherInterface() {
        System.out.println("教师编号：");
        String id = sc.nextLine();
        System.out.println("教师姓名：");
        String name = sc.nextLine();
        Teacher t = Teacher.builder().teacherId(id).teacherName(name).build();
        return t;
    }

    @Override
    public void CourseShowInterface(){
        System.out.println("课程管理");
        System.out.println("1. 添加课程");
        System.out.println("2. 选择课程");
        System.out.println("3. 查看课程");//Todo:查看所有课程的信息 或 通过课程编号/课程名称查看其信息
        System.out.println("4. 返回上一级");
    }

    @Override
    public Optional<Courses> CourseInterface(List<String> classIdArray,ArrayList<Courses> courses) {
        StringBuilder sb = new StringBuilder();
        System.out.println("所属学院：");//ToDo:验证是否在已有的学院里，否则不予通过
        String department=sc.nextLine();
        System.out.println("课程编号：");//Todo:可以加上课程编号的正则匹配式
        String courseId = sc.nextLine();
        Optional<Courses> opId = courses.stream().filter((Courses c) -> c.getCourseId().equals(courseId)).findFirst();
        System.out.println("课程名称：");
        String courseName = sc.nextLine();
        Optional<Courses> optName=courses.stream().filter((Courses c)->c.getCourseName().equals(courseName)).findFirst();
        ArrayList<String> curClassId=new ArrayList<>();
        //将对应的课程也加入到classIdArray中，更新含有的classId
        if(optName.isPresent()){
            sb.append("该课程名称已存在，添加失败，请确保要添加的课程为新课程：").append(courseName);
            System.out.println(sb);
            return Optional.empty();
        }
        else if(opId.isPresent()){
            sb.append("该课程编号已存在，添加失败，请确保要添加的课程为新课程：").append(courseId);
            System.out.println(sb);
            return Optional.empty();
        }
        else{
            for (int i = 0; i <CLASS_ID_LIST_SIZE ; i++) {
                int randomNum = r.nextInt(RANGE) + CLASS_NUM;
                while(classIdArray.contains("CLASS"+randomNum)){
                    randomNum = (randomNum + 1) % (RANGE + CLASS_NUM);
                }
                classIdArray.add("CLASS" +randomNum );
                curClassId.add("CLASS" + randomNum);
            }
            ArrayList<Double> weight = new ArrayList<>(Arrays.asList(0.3, 0.1, 0.4, 0.2));
            Courses course = Courses.builder()
                    .department(department)
                    .courseId(courseId)
                    .courseName(courseName)
                    .classId(curClassId)
                    .weight(weight)
                    .isAssigned(false)
                    .build();
            return Optional.of(course);
        }
    }

    @Override
    public void ScoresShowInterface(){
        System.out.println("成绩信息管理");
        System.out.println("1. 添加成绩信息");
        System.out.println("2. 删除成绩信息");
        System.out.println("3. 修改成绩信息");
        System.out.println("4. 查看成绩信息");
        System.out.println("5. 返回上一级");
    }
    @Override
    public void ClassShowInterface(){
        System.out.println("教学班信息管理");
        System.out.println("1.查看教学班信息");
        System.out.println("2. 返回上一级");
    }
    public void QueryScoresShowInterface(){
        System.out.println("成绩查询系统");
        System.out.println("1. 教学班号查询教学班级学生成绩"); //需要键入一个classId
        System.out.println("2. 学号查询学生的成绩"); //需要键入一个stuId
        System.out.println("3. 姓名查询学生的成绩"); //需要键入一个stuName
        System.out.println("4. 查看成绩分布情况");
        System.out.println("5. 查看所有学生的成绩");
        System.out.println("6. 返回上一级");
    }

}
