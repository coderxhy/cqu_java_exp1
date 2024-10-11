package entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class student {
    private String stuId;  //学号
    private String stuName; //姓名
    private boolean sex; //性别  0:女 1:男
    private String department; //所属学院
    private String major; //所属专业
    private String grade; //年级
    private String classNum; //班级号
    private Map<String,String>stuCourses; //选择课程 <课程名称，课程编号>

}
