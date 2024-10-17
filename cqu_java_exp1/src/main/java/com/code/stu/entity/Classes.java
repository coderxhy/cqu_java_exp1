package com.code.stu.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
//教学班
public class Classes {
    //教学班至少包含教师、课程名字、总人数、教学班号、开课学期等信息
    //类似于计算机网络下的教学班级 一门课程可以有多个教学班级
    private String teacherId; //教师编号
    private String courseName; //课程名字
    private int totalStudentNum;//总人数
    private String classId; //教学班号
    private String BeginTerm;//开课学期

}
