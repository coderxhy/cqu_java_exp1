package com.code.stu.Service.impl;

import com.code.stu.Service.TeacherInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Teacher;
import com.code.stu.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.*;

import static com.code.stu.Service.impl.CoursesService.*;

@Service
public class TeacherService
//        extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherInterface {
    private static final String[] TEACHER_NAMES = {
            "John Smith", "Emma Johnson", "Michael Brown", "Olivia Davis",
            "James Wilson", "Sophia Garcia", "William Martinez",
            "Ava Hernandez", "Benjamin Lee", "Charlotte Taylor",
            "Daniel Anderson", "Mia Thomas", "Lucas Jackson",
            "Ella White", "Henry Harris", "Grace Martin"
    };
    public  static final int CLASS_SELECT_NUM=2;
    @Override
    public ArrayList<Teacher> randomGenerateInfo(ArrayList<Courses> coursesList) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Random r = new Random();
        //为每一个教学班都要至少分配一个老师
        HashSet<String> classSet = new HashSet<>();
        int id = 0;
        //生成10个教师对象
        for (int i = 0; i < 10; i++) {
            //为每个教师对象选CLASS_SELECT_NUM个课程 ，随机选取到其中的教学班
            ArrayList<String> classIdList = new ArrayList<>();
            for(int j = 0; j < CLASS_SELECT_NUM; j++) {
                List<String> classLists=coursesList.get(id).getClassId();
                int randomNum = r.nextInt(CLASS_ID_LIST_SIZE);
                if (classSet.contains(classLists.get(randomNum))){
                    randomNum = (randomNum + 1) % CLASS_ID_LIST_SIZE;
                }
                classIdList.add(classLists.get(randomNum));
                classSet.add(classLists.get(randomNum));
                id=(id+1)%COURSE_NUM;
            }
            Teacher teacher = Teacher.builder()
                    .teacherName(TEACHER_NAMES[r.nextInt(TEACHER_NAMES.length)])
                    .teacherId("100"+r.nextInt(1000))
                    .classId(classIdList)
                    .build();
            teachers.add(teacher);
        }
        return teachers;
    }
    @Override
    public String appendTeacherInfo(Teacher t,ArrayList<Teacher>teachers){
        StringBuilder sb=new StringBuilder();
        Optional<Teacher> opt = teachers.stream().filter(teacher -> teacher.getTeacherId().equals(t.getTeacherId())).findFirst();
        if (opt.isPresent()) {
            sb.append("教师编号已存在，无法添加教师信息：教师编号：").append(t.getTeacherId());
            return sb.toString();
        } else {
            teachers.add(t);
            sb.append("成功添加教师信息：添加的教师的信息:");
            return sb.toString();
        }
    }
    @Override
    public String deleteTeacherById(String TeacherId,ArrayList<Teacher> teachers){
        StringBuilder sb=new StringBuilder();
        Optional<Teacher> opt= teachers.stream().filter((Teacher t)-> Objects.equals(TeacherId, t.getTeacherId())).findFirst();
        if(opt.isPresent()){
            teachers.remove(opt.get());
            sb.append("成功通过教室编号删除教室信息，删除的教师的信息——教师编号：").append(opt.get().getTeacherId())
                    .append("\t姓名：").append(opt.get().getTeacherName());
            return sb.toString();
        }
        else{
            sb.append("对应教师编号不存在，删除失败，请确认教师编号无误后再进行删除操作！");
            return sb.toString();
        }
    }
    @Override
    public String updateTeacherInfo(Teacher teacher, Optional<Teacher> opt){
        StringBuilder sb = new StringBuilder();
        if(teacher.getTeacherId()!=null&&!teacher.getTeacherId().equals("")){
            opt.get().setTeacherId(teacher.getTeacherId());
        }
        if(teacher.getTeacherName()!=null&&!teacher.getTeacherName().equals("")){
            opt.get().setTeacherName(teacher.getTeacherName());
        }
        sb.append("更新成功！");
        viewTeacherAllInfo(opt.get());
        return sb.toString();
    }
    @Override
    public void viewTeacherAllInfo(Teacher t){
        StringBuilder sb=new StringBuilder();
        List<String> classList=t.getClassId();
        sb.append("教师编号：").append(t.getTeacherId())
                .append("\t教师姓名：").append(t.getTeacherName());
        if(classList!=null&&classList.size()>0){
            String classStr = classList.toString();
            sb.append("\t教学班级编号列表：").append(classStr);
        }
        System.out.println(sb);
    }
}
