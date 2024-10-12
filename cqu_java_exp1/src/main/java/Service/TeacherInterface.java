package Service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Teacher;

import java.util.ArrayList;

public interface TeacherInterface extends IService<Teacher> {
    ArrayList<Teacher> randomGenerateInfo();
    void appendTeacherInfo(Teacher t);
    void deleteTeacherInfo(Teacher t);
    void updateTeacherInfo(Teacher t);
    void viewTeacherAllInfo(Teacher t);
}
