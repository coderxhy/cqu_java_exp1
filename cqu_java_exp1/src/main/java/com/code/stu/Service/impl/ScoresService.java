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
import java.util.List;

@Service
public class ScoresService
//        extends ServiceImpl<ScoresMapper,Scores>
        implements ScoresInterface {

    private static ScoresService INSTANCE;
    private ScoresService(){}

    public static ScoresService getInstance(){
        if(INSTANCE == null){
            synchronized (ScoresService.class){
                if(INSTANCE == null){
                    INSTANCE = new ScoresService();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Scores RandomGenerateInfo(Courses course){
        Random r = new Random();
        int finalTerm=r.nextInt(30)+70;
        int norm=r.nextInt(25)+75;
        int midTerm=r.nextInt(20)+80;
        int lab=r.nextInt(15)+85;
        List<Double> weight=course.getWeight();
        Double totalScores=norm*weight.get(0)+midTerm*weight.get(1)+finalTerm*weight.get(2)+lab*weight.get(3);
        Integer total=totalScores.intValue();
        Scores score=new Scores(norm,midTerm,finalTerm,lab,total,new Date());
        return score;
    }

    @Override
    public String updateScoresInfo(ArrayList<Student> students,ArrayList<Courses> courses) {
        StringBuilder sb=new StringBuilder();
        Scanner sc=new Scanner(System.in);

        while (true) {
            System.out.println("请输入你要更新成绩的学生学号：");
            String stuId = sc.next();
            Optional<Student> studentOpt = students.stream().filter(s -> s.getStuId().equals(stuId)).findFirst();
            if(studentOpt.isPresent()){
                Student stu=studentOpt.get();
                int id = students.indexOf(stu);
                if (stu.getSelectedCourses() == null) {
                    sb.append("该学生还未选课，快去为他选课吧！");
                    break;
                }
                else {
                    System.out.printf("以下是 %s 学生的所有课程\n",stu.getStuName());
                    stu.getSelectedCourses().keySet().stream().forEach(System.out::println);
                    while (true) {
                        System.out.println("请输入要更新成绩的课程名称:");
                        String name = sc.next();
                        if(stu.getSelectedCourses().containsKey(name)){
                            Optional<Courses> coursesOpt = courses.stream().filter(c -> c.getCourseName().equals(name)).findFirst();
                            System.out.println("请按照 平时成绩，中期成绩，期末成绩，实验成绩的顺序依次键入要更新的成绩值");
                            int norm = sc.nextInt();
                            int midTerm = sc.nextInt();
                            int finalTerm = sc.nextInt();
                            int lab = sc.nextInt();
                            List<Double> weight = coursesOpt.get().getWeight();
                            int total=(int)(norm*weight.get(0)+midTerm*weight.get(1)+finalTerm*weight.get(2)+lab*weight.get(3));
                            Scores score=new Scores(norm,midTerm,finalTerm,lab,total,new Date());
                            stu.getStuCourses().put(name,score);
                            students.set(id, stu);
                            sb.append("更新成绩成功!")
                                    .append("已为学生:").append(stu.getStuName())
                                    .append("的课程：").append(name)
                                    .append("更新了以下成绩：\n平时成绩：")
                                    .append(norm)
                                    .append("\t中期成绩：").append(midTerm)
                                    .append("\t期末成绩：").append(finalTerm)
                                    .append("\t实验成绩：").append(lab)
                                    .append("\t总成绩：").append(total)
                                    .append("\n");
                            break;
                        }
                        else{
                            System.out.println("输入课程名称错误，请重新输入!");
                        }
                    }
                }
                break;
            }
            else{
                System.out.println("输入的学生学号有误，请重新输入!");
            }
        }
        return sb.toString();
    }

    @Override
    public String queryScoresByClassId(String classId, ArrayList<Student> students, ArrayList<Courses>courses){
        //根据classId得到该教学班的课程名称
        //先查找每个student中是否存在该教学班号 放入一个新的集合内 再根据这些学生的课程名称对应的成绩对象中的总成绩进行排序
        //最后将排序后的学生信息输出
        StringBuilder sb=new StringBuilder();
        ArrayList<Student> classStudents=new ArrayList<>();
        String courseName="";
        Optional<Courses> optionalCourse=courses.stream().filter(c->c.getClassId().contains(classId)).findFirst();
        if(optionalCourse.isPresent()){
            courseName=optionalCourse.get().getCourseName();
        }else {
            sb.append("未找到该班级的信息！");
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
                if (student.getStuCourses()!=null) {
                    for(String courseName:student.getStuCourses().keySet()){
                        sb.append("课程名称: ").append(courseName).append(" 平时成绩: ").append(student.getStuCourses().get(courseName).getNormScore()).append(" 期中成绩: ").append(student.getStuCourses().get(courseName).getMidTermScore()).append(" 期末成绩: ").append(student.getStuCourses().get(courseName).getFinalScore()).append(" 实验成绩: ").append(student.getStuCourses().get(courseName).getLabScore()).append(" 总成绩: ").append(student.getStuCourses().get(courseName).getTotalScore()).append("\n");
                    }
                }
                else {
                    sb.append("该学生的暂无成绩单，快去更新吧！\n");
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
                if (student.getStuCourses()!=null) {
                    for(String courseName:student.getStuCourses().keySet()){
                        sb.append("课程名称: ").append(courseName).append(" 平时成绩: ").append(student.getStuCourses().get(courseName).getNormScore()).append(" 期中成绩: ").append(student.getStuCourses().get(courseName).getMidTermScore()).append(" 期末成绩: ").append(student.getStuCourses().get(courseName).getFinalScore()).append(" 实验成绩: ").append(student.getStuCourses().get(courseName).getLabScore()).append(" 总成绩: ").append(student.getStuCourses().get(courseName).getTotalScore()).append("\n");
                    }
                }
                else {
                    sb.append("该学生的暂无成绩单，快去更新吧！\n");
                }
                sb.append("______________________________________________________\n");
                return sb.toString();
            }
        }
        sb.append("未找到该姓名对应的学生信息");
        return sb.toString();
    }
    @Override
    public void showDistributionOfScores(ArrayList<Student> students){
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
                Optional<Student> opt = students.stream().filter(student -> student.getStuId().equals(stuId)).findFirst();
                if(opt.isPresent()){
                    Student student=opt.get();
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
                }else {
                    sb.append("未找到该学号对应的学生信息");
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
        System.out.println(sb);
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
