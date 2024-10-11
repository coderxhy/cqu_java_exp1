package Service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Teacher;

import java.util.ArrayList;

public interface TeacherInterface extends IService<Teacher> {
    ArrayList<Teacher> randomGenerateInfo();
    void appendTeacherInfo(Teacher s);
    void deleteTeacherInfo(Teacher s);
    void updateTeacherInfo(Teacher s);
    Teacher viewTeacherBasicInfo(Teacher s);
    Teacher viewTeacherAllInfo(Teacher s);
}
