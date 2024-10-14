package com.code.stu.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Teacher {
//    教师至少包含教师编号、姓名等信息
    private String teacherId;
    private String teacherName;
    private List<String> classId;
}
