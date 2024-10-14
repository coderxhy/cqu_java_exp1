package com.code.stu.Service.impl;

import com.code.stu.Service.StudentInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.stu.entity.Student;
import com.code.stu.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class StudentService
//        extends ServiceImpl<StudentMapper, Student>
        implements StudentInterface {
    private static final String[] FIRST_NAMES = {
            "Liam", "Emma", "Noah", "Olivia", "Oliver",
            "Ava", "Elijah", "Sophia", "James", "Isabella",
            "William", "Mia", "Benjamin", "Charlotte", "Lucas"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson"
    };
    private static final String[] SEX={"男","女"};

    @Override
    public ArrayList<Student> randomGenerateInfo(){
        ArrayList<Student> students = new ArrayList<>();
        Random r = new Random();
        //随机出firstName与lastName的个数 进行随机组合 [10-15]*[10-15]=totalStudents
        int firstNameNum = r.nextInt(FIRST_NAMES.length-9)+10;
        int lastNameNum = r.nextInt(LAST_NAMES.length-9)+10;

        for(int i=0;i<firstNameNum;i++){
            for(int j=0;j<lastNameNum;j++){
                Student student = Student.builder()
                        .stuName(FIRST_NAMES[r.nextInt(FIRST_NAMES.length)]+LAST_NAMES[r.nextInt(LAST_NAMES.length)])
                        .sex(SEX[r.nextInt(SEX.length)])
                        .stuId("2022"+String.valueOf(r.nextInt(10000)))
                        .build();
                students.add(student);
            }
        }
        return students;
    }
    @Override
    public String appendStuInfo(Student s,ArrayList<Student> students){
        //在添加学生信息时需要确保不存在相同的stuId
        StringBuilder sb=new StringBuilder();
        Optional<Student> opt=students.stream().filter((Student stu)->s.getStuId().equals(stu.getStuId())).findFirst();
        if(opt.isPresent()){
            sb.append("该学生信息已存在，请确保要添加的学生学号无误：").append(s.getStuId());
            return sb.toString();
        }
        else{
            students.add(s);
            sb.append("添加学生信息成功：");
//                    .append(s.getStuId())
//                    .append("\t姓名：").append(s.getStuName())
//                    .append("\t性别：").append(s.getSex());
            return sb.toString();
        }
    }
    @Override
    public String deleteStuById(String StuId,ArrayList<Student> students){
        StringBuilder sb=new StringBuilder();
        Optional<Student> opt= students.stream().filter((Student s)->s.getStuId().equals(StuId)).findFirst();
        if(opt.isPresent()){
            students.remove(opt.get());
            sb.append("成功通过学号删除学生信息");
//                    .append(opt.get().getStuId())
//                    .append("\t姓名：").append(opt.get().getStuName())
//                    .append("\t性别：").append(opt.get().getSex());
            return sb.toString();
        }
        else{
            sb.append("对应学号学生不存在，删除失败，请确认学号无误后再进行删除操作！");
            return sb.toString();
        }
    }
    @Override
    public String updateStuInfo(Student stu,Optional<Student> opt){
        //更新时首先需要查找到要更新的对象,对于stu中的属性，如果不为空，那么就对应更新查找到的对象。
        StringBuilder sb=new StringBuilder();

        if(stu.getStuName()!=null&&!stu.getStuName().equals("")){
            opt.get().setStuName(stu.getStuName());
        }
        if(stu.getSex()!=null&&!stu.getSex().equals("")){
            opt.get().setSex(stu.getSex());
        }
        if(stu.getDepartment()!=null&&!stu.getDepartment().equals("")){
            opt.get().setDepartment(stu.getDepartment());
        }
        if(stu.getMajor()!=null&&!stu.getMajor().equals("")){
            opt.get().setMajor(stu.getMajor());
        }
        if(stu.getGrade()!=null&&!stu.getGrade().equals("")){
            opt.get().setGrade(stu.getGrade());
        }
        if(stu.getClassId()!=null&&!stu.getClassId().equals("")){
            opt.get().setClassId(stu.getClassId());
        }
        sb.append("更新成功！");
        viewStuAllInfo(opt.get());
        return sb.toString();

    }
    @Override
    public void viewStuBasicInfo(Student s){
        StringBuilder sb=new StringBuilder();
        sb.append("姓名：").append(s.getStuName())
                .append("\t学号：").append(s.getStuId())
                .append("\t性别：").append(s.getSex());
        System.out.println(sb);
    }
    @Override
    public void viewStuAllInfo(Student s){
        StringBuilder sb=new StringBuilder();
        sb.append("姓名：").append(s.getStuName())
                .append("\t学号：").append(s.getStuId())
                .append("\t性别：").append(s.getSex())
                .append("\t学院：").append(s.getDepartment())
                .append("\t专业：").append(s.getMajor())
                .append("\t年级：").append(s.getGrade())
                .append("\t教学班号：").append(s.getClassId());
        System.out.println(sb);
    }
}
