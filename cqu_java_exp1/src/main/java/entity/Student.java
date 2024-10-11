package entity;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class Student {
    //学生至少包含学号、姓名、性别等信息
    private String stuId;  //学号
    private String stuName; //姓名
    private String sex; //性别  女 男

    private String department; //所属学院
    private String major; //所属专业
    private String grade; //年级
    private String classNum; //班级号
    private Map<String,String>stuCourses; //选择课程 <课程名称，课程编号>

}
