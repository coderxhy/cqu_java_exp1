package entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Scores {
    private int normScore; //平时成绩
    private int midTermScore; //中期成绩
    private int finalScore; //期末成绩
    private int labScore;//实验成绩
}
