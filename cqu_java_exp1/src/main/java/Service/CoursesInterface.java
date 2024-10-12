package Service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Courses;
import mapper.CoursesMapper;

import java.util.ArrayList;

public interface CoursesInterface extends IService<Courses> {
    ArrayList<Courses> randomGenerateInfo();
}
