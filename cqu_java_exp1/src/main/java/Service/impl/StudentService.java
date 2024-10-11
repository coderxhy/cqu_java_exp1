package Service.impl;

import Service.StudentInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Student;
import mapper.StudentMapper;

import java.util.ArrayList;
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
