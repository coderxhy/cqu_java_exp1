package Service.impl;

import Service.TeacherInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Teacher;
import mapper.TeacherMapper;

import java.util.ArrayList;
import java.util.Random;

public class TeacherService extends ServiceImpl<TeacherMapper, Teacher> implements TeacherInterface {
    private static final String[] TEACHER_NAMES = {
            "John Smith", "Emma Johnson", "Michael Brown", "Olivia Davis",
            "James Wilson", "Sophia Garcia", "William Martinez",
            "Ava Hernandez", "Benjamin Lee", "Charlotte Taylor",
            "Daniel Anderson", "Mia Thomas", "Lucas Jackson",
            "Ella White", "Henry Harris", "Grace Martin"
    };
    @Override
    public ArrayList<Teacher> randomGenerateInfo() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Random r = new Random();
        //生成10个教师对象
        for (int i = 0; i < 10; i++) {
            Teacher teacher = Teacher.builder()
                    .teacherName(TEACHER_NAMES[r.nextInt(TEACHER_NAMES.length)])
                    .teacherId("100"+String.valueOf(r.nextInt(1000)))
                    .build();
            teachers.add(teacher);
        }
        return teachers;
    }
    @Override
    public void appendTeacherInfo(Teacher s){

    }
    @Override
    public void deleteTeacherInfo(Teacher s){

    }
    @Override
    public void updateTeacherInfo(Teacher s){

    }
    @Override
    public Teacher viewTeacherBasicInfo(Teacher s){
        return null;
    }
    @Override
    public Teacher viewTeacherAllInfo(Teacher s){
        return null;
    }
}
