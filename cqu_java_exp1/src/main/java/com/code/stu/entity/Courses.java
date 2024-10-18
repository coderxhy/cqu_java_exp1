package com.code.stu.entity;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Builder
@Data
public class Courses {
//    课程至少包含课程编号、课程名字等信息 类似于计算机网络
    private String department; //所属学院
    private String courseId; //课程编号
    private String courseName; //课程名称
    private boolean isAssigned; //是否分配学生和老师
    private List<String> classId; //教学班号
    private List<Double> weight; //权重 依次为平时成绩、期中成绩、期末成绩、实验成绩

}
