package entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Courses {
//    课程至少包含课程编号、课程名字等信息 类似于计算机网络
    private String department; //所属学院
    private String courseId; //课程编号
    private String courseName; //课程名称
    private List<String> classId; //教学班号
    private List<Double> weight; //权重 依次为平时成绩、期中成绩、期末成绩、实验成绩
//    private Map<String,Scores> scoreMap;

//    private String department; //所属学院
//    private String major; //所属专业
//    private String teacherId; //教师编号
//    private Integer eduNumber;  //教学人数
//    private Integer credit; //学分
//    private String courseTerm; //开课学期
}
