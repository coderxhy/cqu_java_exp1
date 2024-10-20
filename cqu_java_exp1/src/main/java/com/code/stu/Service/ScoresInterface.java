package com.code.stu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Scores;
import com.code.stu.entity.Student;

import java.util.ArrayList;

public interface ScoresInterface
//extends IService<Scores>
{   Scores RandomGenerateInfo(Courses course);
    String updateScoresInfo(ArrayList<Student> students,ArrayList<Courses> courses);
    public String queryScoresByClassId(String classId, ArrayList<Student> students, ArrayList<Courses>courses);
    public String queryScoresByStuId(String stuId, ArrayList<Student> students);
    public String queryScoresByStuName(String stuName, ArrayList<Student> students);
    public void showDistributionOfScores(ArrayList<Student> students);
    public String showScoresOfAllStudents(ArrayList<Student> students);
}
