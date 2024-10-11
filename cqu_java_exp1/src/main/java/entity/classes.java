package entity;

import lombok.Data;

@Data
//教学班
public class classes {
    private String classId; //
    private String department; //所属学院
    private String major; //所属专业
    private String grade; //年级
    private String classNum; //班级号
    private String teacherId; //辅导员编号

}
