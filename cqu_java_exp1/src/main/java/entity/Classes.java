package entity;

import lombok.Data;

@Data
//教学班
public class Classes {
    //教学班至少包含教师、课程名字、总人数、教学班号、开课学期等信息
    private String teacherId; //教师编号
    private String courseName; //课程名字
    private int totalStudentNum;//总人数
    private String classId; //教学班号
    private String BeginTerm;//开课学期

    private String department; //所属学院

}
