package entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Scores {
    private int normScore; //平时成绩
    private int midTermScore; //中期成绩
    private int finalScore; //期末成绩
    private int labScore;//实验成绩
    private int totalScore; //总成绩
    private Date date; //成绩取得时间
}
