package Service;

import entity.Courses;
import entity.Student;

import java.util.ArrayList;

public interface ScoresInterface {
    public String queryScoresByClassId(String classId, ArrayList<Student> students, ArrayList<Courses>courses);
    public String queryScoresByStuId(String stuId, ArrayList<Student> students);
    public String queryScoresByStuName(String stuName, ArrayList<Student> students);
    public String showDistributionOfScores(ArrayList<Student> students);
    public String showScoresOfAllStudents(ArrayList<Student> students);
}
