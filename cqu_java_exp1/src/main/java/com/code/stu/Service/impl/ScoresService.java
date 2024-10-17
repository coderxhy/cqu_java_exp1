package com.code.stu.Service.impl;

import com.code.stu.Service.ScoresInterface;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Scores;
import com.code.stu.entity.Student;
import org.jfree.chart.ChartPanel;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.*;

@Service
public class ScoresService
//        extends ServiceImpl<ScoresMapper,Scores>
        implements ScoresInterface {
    @Override
    public Scores RandomGenerrateInfo(){
        Random r = new Random();
        int finalTerm=r.nextInt(30)+70;
        int norm=r.nextInt(25)+75;
        int midTerm=r.nextInt(20)+80;
        int lab=r.nextInt(15)+85;
        int total=finalTerm+norm+midTerm+lab;
        Scores score=new Scores(norm,midTerm,finalTerm,lab,total,new Date());
        return score;
    }

    @Override
    public String queryScoresByClassId(String classId, ArrayList<Student> students, ArrayList<Courses>courses){
        //根据classId得到该教学班的课程名称
        //先查找每个student中是否存在该教学班号 放入一个新的集合内 再根据这些学生的课程名称对应的成绩对象中的总成绩进行排序
        //最后将排序后的学生信息输出
        StringBuilder sb=new StringBuilder();
        ArrayList<Student> classStudents=new ArrayList<>();
        String courseName="";
        for(Courses course:courses){
            if(course.getClassId().contains(classId)){
                courseName=course.getCourseName();
            }else {
                sb.append("未找到该教学班号对应的课程信息");
                return sb.toString();
            }
        }
        for(Student student:students){
            if(student.getSelectedCourses().containsValue(classId)){
                classStudents.add(student);
            }
        }
        String finalCourseName = courseName;
        System.out.println("请选择排序方式:");
        System.out.println("1. 降序排序");
        System.out.println("2. 升序排序");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        switch(choice){
            case 1:
                classStudents.sort((o1, o2)->o2.getStuCourses().get(finalCourseName).getTotalScore()-o1.getStuCourses().get(finalCourseName).getTotalScore());
                break;
            case 2:
                classStudents.sort((o1, o2)->o1.getStuCourses().get(finalCourseName).getTotalScore()-o2.getStuCourses().get(finalCourseName).getTotalScore());
                break;
            default:
                sb.append("输入错误");
                return sb.toString();
        }
        for(Student student:classStudents){
            sb.append("学号: ").append(student.getStuId()).append(" 姓名: ").append(student.getStuName()).append(" 性别: ").append(student.getSex()).append(" 平时成绩: ").append(student.getStuCourses().get(finalCourseName).getNormScore()).append(" 期中成绩: ").append(student.getStuCourses().get(finalCourseName).getMidTermScore()).append(" 期末成绩: ").append(student.getStuCourses().get(finalCourseName).getFinalScore()).append(" 实验成绩: ").append(student.getStuCourses().get(finalCourseName).getLabScore()).append(" 总成绩: ").append(student.getStuCourses().get(finalCourseName).getTotalScore()).append("\n");
        }
        return sb.toString();
    }
    @Override
    public String queryScoresByStuId(String stuId, ArrayList<Student> students){
        StringBuilder sb=new StringBuilder();
        for(Student student:students){
            if(student.getStuId().equals(stuId)){
                sb.append("学号: ").append(student.getStuId()).append(" 姓名: ").append(student.getStuName()).append(" 性别: ").append(student.getSex()).append("\n");
                for(String courseName:student.getStuCourses().keySet()){
                    sb.append("课程名称: ").append(courseName).append(" 平时成绩: ").append(student.getStuCourses().get(courseName).getNormScore()).append(" 期中成绩: ").append(student.getStuCourses().get(courseName).getMidTermScore()).append(" 期末成绩: ").append(student.getStuCourses().get(courseName).getFinalScore()).append(" 实验成绩: ").append(student.getStuCourses().get(courseName).getLabScore()).append(" 总成绩: ").append(student.getStuCourses().get(courseName).getTotalScore()).append("\n");
                }
                sb.append("______________________________________________________\n");
                return sb.toString();
            }
        }
        sb.append("未找到该学号对应的学生信息");
        return sb.toString();
    }
    @Override
    public String queryScoresByStuName(String stuName, ArrayList<Student> students){
        StringBuilder sb=new StringBuilder();
        for(Student student:students){
            if(student.getStuName().equals(stuName)){
                sb.append("学号: ").append(student.getStuId()).append(" 姓名: ").append(student.getStuName()).append(" 性别: ").append(student.getSex()).append("\n");
                for(String courseName:student.getStuCourses().keySet()){
                    sb.append("课程名称: ").append(courseName).append(" 平时成绩: ").append(student.getStuCourses().get(courseName).getNormScore()).append(" 期中成绩: ").append(student.getStuCourses().get(courseName).getMidTermScore()).append(" 期末成绩: ").append(student.getStuCourses().get(courseName).getFinalScore()).append(" 实验成绩: ").append(student.getStuCourses().get(courseName).getLabScore()).append(" 总成绩: ").append(student.getStuCourses().get(courseName).getTotalScore()).append("\n");
                }
                sb.append("______________________________________________________\n");
                return sb.toString();
            }
        }
        sb.append("未找到该姓名对应的学生信息");
        return sb.toString();
    }
    @Override
    public String showDistributionOfScores(ArrayList<Student> students){
        StringBuilder sb=new StringBuilder();
        //统计某位学生各科成绩的分数段分布
        //统计所有学生某科成绩的分数段分布
        Map<String, Integer> scoreDistribution = new HashMap<>();
        System.out.println("请选择统计方式:");
        System.out.println("1. 某位学生各科成绩的分数段分布");
        System.out.println("2. 所有学生某科成绩的分数段分布");
        Scanner scanner=new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("请输入学号:");
                String stuId = scanner.next();
                for (Student student : students) {
                    if (student.getStuId().equals(stuId)) {
                        //<分数段,课程数> 先初始化一个哈希，数量为10 key为"0-9","10-19"... value为0
                        //根据该学生每一门的课程最终成绩进行统计
                        for (String courseName : student.getStuCourses().keySet()) {
                            int totalScore = student.getStuCourses().get(courseName).getTotalScore();
                            String key = "";
                            if (totalScore >= 0 && totalScore <= 9) {
                                key = "0-9";
                            } else if (totalScore >= 10 && totalScore <= 19) {
                                key = "10-19";
                            } else if (totalScore >= 20 && totalScore <= 29) {
                                key = "20-29";
                            } else if (totalScore >= 30 && totalScore <= 39) {
                                key = "30-39";
                            } else if (totalScore >= 40 && totalScore <= 49) {
                                key = "40-49";
                            } else if (totalScore >= 50 && totalScore <= 59) {
                                key = "50-59";
                            } else if (totalScore >= 60 && totalScore <= 69) {
                                key = "60-69";
                            } else if (totalScore >= 70 && totalScore <= 79) {
                                key = "70-79";
                            } else if (totalScore >= 80 && totalScore <= 89) {
                                key = "80-89";
                            } else if (totalScore >= 90 && totalScore <= 100) {
                                key = "90-100";
                            }
                            scoreDistribution.put(key, scoreDistribution.getOrDefault(key, 0) + 1);
                        }
                    } else {
                        sb.append("未找到该学号对应的学生信息");
                    }
                }
                break;
            case 2:
                System.out.println("请输入课程名称:");
                String courseName = scanner.next();
                //<分数段,学生数> 先初始化一个哈希，数量为10 key为"0-9","10-19"... value为0
                //根据所有学生的该门课程最终成绩进行统计
                for (Student student : students) {
                    if (student.getStuCourses().containsKey(courseName)) {
                        int totalScore = student.getStuCourses().get(courseName).getTotalScore();
                        String key = "";
                        if (totalScore >= 0 && totalScore <= 9) {
                            key = "0-9";
                        } else if (totalScore >= 10 && totalScore <= 19) {
                            key = "10-19";
                        } else if (totalScore >= 20 && totalScore <= 29) {
                            key = "20-29";
                        } else if (totalScore >= 30 && totalScore <= 39) {
                            key = "30-39";
                        } else if (totalScore >= 40 && totalScore <= 49) {
                            key = "40-49";
                        } else if (totalScore >= 50 && totalScore <= 59) {
                            key = "50-59";
                        } else if (totalScore >= 60 && totalScore <= 69) {
                            key = "60-69";
                        } else if (totalScore >= 70 && totalScore <= 79) {
                            key = "70-79";
                        } else if (totalScore >= 80 && totalScore <= 89) {
                            key = "80-89";
                        } else if (totalScore >= 90 && totalScore <= 100) {
                            key = "90-100";
                        }
                        scoreDistribution.put(key, scoreDistribution.getOrDefault(key, 0) + 1);
                    }
                }
                break;
            default:
                sb.append("输入错误");
                break;
        }
        //画图
        JFrame j = new JFrame();
        JDialog jd = new JDialog(j, "成绩分布图", true);
        jd.setBounds(50, 50, 800, 600);
        jd.setLayout(new BorderLayout());

        // 创建柱状图
        BarChart barChart = new BarChart(scoreDistribution);
        ChartPanel chartPanel = barChart.getPanel();

        // 将图表面板添加到对话框
        jd.add(chartPanel, BorderLayout.CENTER);

        // 显示对话框
        jd.setVisible(true);
        return sb.toString();
    }
    @Override
    public String showScoresOfAllStudents(ArrayList<Student> students){
        StringBuilder sb=new StringBuilder();
        System.out.println("请选择排序方式:");
        System.out.println("1. 按照学号排名");
        System.out.println("2. 按照各科成绩排名");
        System.out.println("3. 按照总成绩排名");
        Scanner scanner=new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1:
                students.sort((o1, o2)->o1.getStuId().compareTo(o2.getStuId()));
                for(Student student:students){
                    sb.append("学号: ").append(student.getStuId()).append(" 姓名: ").append(student.getStuName()).append(" 性别: ").append(student.getSex()).append("\n");
                    for(String courseName:student.getStuCourses().keySet()){
                        sb.append("课程名称: ").append(courseName).append(" 平时成绩: ").append(student.getStuCourses().get(courseName).getNormScore()).append(" 期中成绩: ").append(student.getStuCourses().get(courseName).getMidTermScore()).append(" 期末成绩: ").append(student.getStuCourses().get(courseName).getFinalScore()).append(" 实验成绩: ").append(student.getStuCourses().get(courseName).getLabScore()).append(" 总成绩: ").append(student.getStuCourses().get(courseName).getTotalScore()).append("\n");
                    }
                    sb.append("______________________________________________________\n");
                }
                break;
            case 2:
                // 先按照课程名称对集合进行划分，再对每个集合内的学生按照该门课程成绩中的总成绩进行排序
                Map<String, ArrayList<Student>> classStudents = new HashMap<>();  //<课程名称，学生集合>
                //填充集合
                for (Student student : students) {
                    for (String courseName : student.getSelectedCourses().keySet()) {
                        classStudents.computeIfAbsent(courseName, k -> new ArrayList<>()).add(student);
                    }
                }
                // 返回String类型的排序结果
                for (String courseName : classStudents.keySet()) {
                    //进行排序
                    classStudents.get(courseName).sort((o1, o2) -> o2.getStuCourses().get(courseName).getTotalScore() - o1.getStuCourses().get(courseName).getTotalScore());
                    //排序结果输出
                    sb.append("课程名称: ").append(courseName).append("\n");
                    for (Student student : classStudents.get(courseName)) {
                        Scores scores = student.getStuCourses().get(courseName);
                        sb.append("学号: ").append(student.getStuId())
                                .append(" 姓名: ").append(student.getStuName())
                                .append(" 性别: ").append(student.getSex())
                                .append(" 平时成绩: ").append(scores.getNormScore())
                                .append(" 期中成绩: ").append(scores.getMidTermScore())
                                .append(" 期末成绩: ").append(scores.getFinalScore())
                                .append(" 实验成绩: ").append(scores.getLabScore())
                                .append(" 总成绩: ").append(scores.getTotalScore())
                                .append("\n");
                    }
                    sb.append("______________________________________________________\n");
                }
                break;
            case 3:
                students.sort((o1, o2)->o2.getStuCourses().values().stream().mapToInt(Scores::getTotalScore).sum()-o1.getStuCourses().values().stream().mapToInt(Scores::getTotalScore).sum());
                for (Student student : students) {
                    sb.append("学号: ").append(student.getStuId()).append(" 姓名: ").append(student.getStuName()).append(" 性别: ").append(student.getSex()).append("\n");
                    for (String courseName : student.getStuCourses().keySet()) {
                        Scores scores = student.getStuCourses().get(courseName);
                        sb.append("课程名称: ").append(courseName)
                                .append(" 平时成绩: ").append(scores.getNormScore())
                                .append(" 期中成绩: ").append(scores.getMidTermScore())
                                .append(" 期末成绩: ").append(scores.getFinalScore())
                                .append(" 实验成绩: ").append(scores.getLabScore())
                                .append(" 总成绩: ").append(scores.getTotalScore())
                                .append("\n");
                    }
                    sb.append("______________________________________________________\n");
                }
                break;
            default:
                sb.append("输入错误");
                break;
        }return sb.toString();
    }
}
