package Service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Student;

import java.util.ArrayList;

public interface StudentInterface extends IService<Student> {
    ArrayList<Student> randomGenerateInfo();
    void appendStuInfo(Student s);
    void deleteStuInfo(Student s);
    void updateStuInfo(Student s);
    void viewStuBasicInfo(Student s);
    Student viewStuAllInfo(Student s);
}
