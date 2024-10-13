package Service.impl;

import Service.TeacherInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Student;
import entity.Teacher;
import mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
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
    public String appendTeacherInfo(Teacher t,ArrayList<Teacher>teachers){
        StringBuilder sb=new StringBuilder();
        Optional<Teacher> opt = teachers.stream().filter(teacher -> teacher.getTeacherId().equals(t.getTeacherId())).findFirst();
        if (opt.isPresent()) {
            sb.append("教师编号已存在，无法添加教师信息：教师编号：").append(t.getTeacherId());
            return sb.toString();
        } else {
            teachers.add(t);
            sb.append("成功添加教师信息：添加的教师基本信息——教师编号：").append(t.getTeacherId())
                    .append("\t姓名：").append(t.getTeacherName());
            return sb.toString();
        }
    }
    @Override
    public String deleteTeacherById(String TeacherId,ArrayList<Teacher> teachers){
        StringBuilder sb=new StringBuilder();
        Optional<Teacher> opt= teachers.stream().filter((Teacher t)-> Objects.equals(TeacherId, t.getTeacherId())).findFirst();
        if(opt.isPresent()){
            teachers.remove(opt.get());
            sb.append("成功通过教室编号删除教室信息：删除的教师基本信息——教师编号：").append(opt.get().getTeacherId())
                    .append("\t姓名：").append(opt.get().getTeacherName());
            return sb.toString();
        }
        else{
            sb.append("对应教师编号不存在，删除失败，请确认教师编号无误后再进行删除操作！");
            return sb.toString();
        }
    }
    @Override
    public String updateTeacherInfo(String TeacherId,ArrayList<Teacher>teachers){
        StringBuilder sb = new StringBuilder();
        Optional<Teacher> opt= teachers.stream().filter((Teacher t)->Objects.equals(TeacherId,t.getTeacherId())).findFirst();
        if(opt.isPresent()){
            Teacher teacher=opt.get();
            teacher.setTeacherName("NewName");
            sb.append("成功通过教师编号更新教师信息：更新后的教师基本信息——教师编号：").append(teacher.getTeacherId())
                    .append("\t姓名：").append(teacher.getTeacherName());
            return sb.toString();
        }
        else{
            sb.append("对应教师编号不存在，更新失败，请确认教师编号无误后再进行更新操作！");
            return sb.toString();
        }

    }
    @Override
    public void viewTeacherAllInfo(Teacher t){
        StringBuilder sb=new StringBuilder();
        List<String> classList=t.getClassId();
        sb.append("教师编号：").append(t.getTeacherId())
                .append("\t教师姓名：").append(t.getTeacherName());
        if(classList!=null&&classList.size()>0){
            String classStr = classList.toString();
            sb.append("教学班级编号列表：").append(classStr);
        }
        System.out.println(sb);
    }
}
