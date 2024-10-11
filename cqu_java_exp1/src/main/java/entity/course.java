package entity;

import lombok.Data;

@Data
public class course {
    private String courseId; //课程编号
    private String courseName; //课程名称
    private String department; //所属学院
    private String major; //所属专业
    private String teacherId; //教师编号
    private Integer eduNumber;  //教学人数
    private Integer credit; //学分
    private String courseTerm; //开课学期
}
