package Service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Student;

import java.util.ArrayList;

public interface StudentInterface extends IService<Student> {
    ArrayList<Student> randomGenerateInfo();
    void appendStuInfo(Student s,ArrayList<Student> students);
    String deleteStuById(String StuId,ArrayList<Student> students);
    void updateStuInfo(Student s);
    void viewStuBasicInfo(Student s);
    Student viewStuAllInfo(Student s);
}
