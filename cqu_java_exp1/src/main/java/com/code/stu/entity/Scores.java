package com.code.stu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scores {
    private int normScore; //平时成绩
    private int midTermScore; //中期成绩
    private int finalScore; //期末成绩
    private int labScore;//实验成绩
    private int totalScore; //总成绩
    private Date date; //成绩取得时间
}
