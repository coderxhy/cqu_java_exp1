package Service.impl;

import Service.StudentInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Student;
import mapper.StudentMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class StudentService extends ServiceImpl<StudentMapper, Student> implements StudentInterface {
    private static final String[] FIRST_NAMES = {
            "Liam", "Emma", "Noah", "Olivia", "Oliver",
            "Ava", "Elijah", "Sophia", "James", "Isabella",
            "William", "Mia", "Benjamin", "Charlotte", "Lucas"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson"
    };
    private static final String[] SEX={"男","女"};
//    private static String[] DEPARTMENTS = {
//            "计算机学院", "机械工程学院", "电子信息学院", "生物科学学院", "经济管理学院"
//    };
//    private static HashMap<String, ArrayList<String>> majorsMap = new HashMap<String, ArrayList<String>>() {{
//        put(DEPARTMENTS[0], new ArrayList<>(Arrays.asList("软件工程", "网络工程", "人工智能")));
//        put(DEPARTMENTS[1], new ArrayList<>(Arrays.asList("机械设计", "自动化", "材料科学")));
//        put(DEPARTMENTS[2], new ArrayList<>(Arrays.asList("电气工程", "通信工程", "微电子")));
//        put(DEPARTMENTS[3], new ArrayList<>(Arrays.asList("生物技术", "生态学", "遗传学")));
//        put(DEPARTMENTS[4], new ArrayList<>(Arrays.asList("市场营销", "财务管理", "人力资源")));
//    }};
    @Override
    public ArrayList<Student> randomGenerateInfo(){
        ArrayList<Student> students = new ArrayList<>();
        Random r = new Random();
        //随机出firstName与lastName的个数 进行随机组合 [10-15]*[10-15]=totalStudents
        int firstNameNum = r.nextInt(FIRST_NAMES.length-9)+10;
        int lastNameNum = r.nextInt(LAST_NAMES.length-9)+10;

        for(int i=0;i<firstNameNum;i++){
            for(int j=0;j<lastNameNum;j++){
                Student student = Student.builder()
                        .stuName(FIRST_NAMES[r.nextInt(FIRST_NAMES.length)]+LAST_NAMES[r.nextInt(LAST_NAMES.length)])
                        .sex(SEX[r.nextInt(SEX.length)])
                        .stuId("2022"+String.valueOf(r.nextInt(10000)))
                        .build();
                students.add(student);
            }
        }
        return students;
    }
    @Override
    public void appendStuInfo(Student s){

    }
    @Override
    public void deleteStuInfo(Student s){

    }
    @Override
    public void updateStuInfo(Student s){

    }
    @Override
    public void viewStuBasicInfo(Student s){
        StringBuilder sb=new StringBuilder();
        sb.append("姓名：").append(s.getStuName())
                .append("\t学号：").append(s.getStuId())
                .append("\t性别：").append(s.getSex());
        System.out.println(sb);
    }
    @Override
    public Student viewStuAllInfo(Student s){
        return null;
    }
}
