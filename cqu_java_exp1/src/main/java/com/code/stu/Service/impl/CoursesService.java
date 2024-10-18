package com.code.stu.Service.impl;

import com.code.stu.Service.CoursesInterface;
import com.code.stu.entity.Courses;
import com.code.stu.entity.Student;
import com.code.stu.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoursesService
//        extends ServiceImpl<CoursesMapper, Courses>
        implements CoursesInterface {
    public static final int COURSE_NUM=10;
    public static final int CLASS_NUM=20;
    public static final  int CLASS_ID_LIST_SIZE=CLASS_NUM/COURSE_NUM;

    private static String[] DEPARTMENTS = {
            "计算机学院", "软件工程学院", "电气信息学院"
    };
    private static HashMap<String, ArrayList<String>> majorsMap = new HashMap<String, ArrayList<String>>() {{
        put(DEPARTMENTS[0], new ArrayList<>(Arrays.asList("计算机科学与技术", "物联网", "网络安全")));
        put(DEPARTMENTS[1], new ArrayList<>(Arrays.asList("软件工程", "系统开发集成", "人工智能")));
        put(DEPARTMENTS[2], new ArrayList<>(Arrays.asList("电气工程", "通信工程", "微电子")));
    }};
    private static ArrayList<String> CoursesArray = new ArrayList<>(Arrays.asList(
            "线性代数", "概率与统计", "高等数学",
            "数学实验", "工程师职业素养", "电路原理",
            "数字逻辑", "数据库设计", "离散数学",
            "大学物理", "大学英语", "大学体育",
            "中国近代史纲要", "马克思主义基本原理", "文明经典"
    ));

    public ArrayList<String> classIdArray = GenerateClassId();

    Random r = new Random();

//    产生ClassId -> "CLASS"+[0,CLASS_NUM-1]
    @Override
    public ArrayList<String> GenerateClassId() {
        ArrayList<String> classIdList = new ArrayList<>();
        for (int i = 0; i < CLASS_NUM; i++) {
            String classId="CLASS"+i;
            classIdList.add(classId);
        }
        return classIdList;
    }
//    Entry对象<学院，专业> ,供StudentService中Student对象的初始化服务
    @Override
    public Map.Entry<String, String> GenerateEntry(){
        String department=DEPARTMENTS[r.nextInt(DEPARTMENTS.length)];
        String major=majorsMap.get(department).get(r.nextInt(majorsMap.get(department).size()));
        Map.Entry<String,String> majorEntry=new AbstractMap.SimpleEntry<>(department,major);
        return majorEntry;
    }
    //随机为所有的课程产生课程编号,并添加到映射关系中
    @Override
    public HashMap<String, String> GenerateCourseIds() {
        HashMap<String,String> CourseIdMap=new HashMap<>();
        for(int i=0;i<CoursesArray.size();i++){
            String courseId="COURSE"+r.nextInt(1000);
            CourseIdMap.put(CoursesArray.get(i),courseId);
        }
        return CourseIdMap;
    }

    @Override
    public ArrayList<Courses> randomGenerateInfo(){
        ArrayList<Courses> courses=new ArrayList<>();
        HashSet<String> classIdSet=new HashSet<>();
        HashSet<String> courseNameSet=new HashSet<>();
        HashMap<String,String> idMap=GenerateCourseIds();
        for(int i=0;i<COURSE_NUM;i++){
            ArrayList<String> classIdList=new ArrayList<>();
            //从中随机取该CLASS_ID_LIST_SIZE个数classId分配给
            for(int j=0;j<CLASS_ID_LIST_SIZE;j++){
                int randomId=r.nextInt(classIdArray.size());
                //在Set里查找是否存在，若存在则使randomId++直到找不到
                while(classIdSet.contains(classIdArray.get(randomId))){
                    randomId=(randomId+1)%CLASS_NUM;
                }
                //将对应的教学班号添加到Set中
                classIdSet.add(classIdArray.get(randomId));
                classIdList.add(classIdArray.get(randomId));
            }
            //防止选的课程重复
            int randomCourseId=r.nextInt(CoursesArray.size());
            while(courseNameSet.contains(CoursesArray.get(randomCourseId))){
                randomCourseId=(randomCourseId+1)%CoursesArray.size();
            }
            String Name=CoursesArray.get(randomCourseId);
            courseNameSet.add(Name);
            Courses course = Courses.builder().department(DEPARTMENTS[r.nextInt(DEPARTMENTS.length)])
                    .courseId(idMap.get(Name)).courseName(Name).classId(classIdList)
                    .weight(Arrays.asList(0.3,0.1,0.4,0.2))
                    .isAssigned(true)
                    .build();
            courses.add(course);
        }
        return courses;
    }

    @Override
    public String appendCourses(Courses course,ArrayList<Courses> courses) {
        StringBuilder sb=new StringBuilder();
        Optional<Courses> optName=courses.stream().filter((Courses c)->c.getCourseName().equals(course.getCourseName())).findFirst();
        Optional<Courses> opId = courses.stream().filter((Courses c) -> c.getCourseId().equals(course.getCourseId())).findFirst();
        if(optName.isPresent()){
            sb.append("该课程名称已存在，添加失败，请确保要添加的课程为新课程：").append(course.getCourseName());
            return sb.toString();
        }
        else if(opId.isPresent()){
            sb.append("该课程编号已存在，添加失败，请确保要添加的课程为新课程：").append(course.getCourseId());
            return sb.toString();
        }
        else {
            courses.add(course);
            sb.append("添加课程信息成功：");
            return sb.toString();
        }
    }

    @Override
    public void assignCourse(ArrayList<Student> students, ArrayList<Teacher> teachers,ArrayList<Courses> courses) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你要进行分配选课的操作：" +
                "\n1.为已分配的课程再次分配" +
                "\n2.为还未分配的课程分配");
        switch(sc.nextInt()){
            case 1:
                System.out.println("-------------------------------");
                List<Courses> assignedCourses = courses.stream().filter(Courses::isAssigned)
                                                       .collect(Collectors.toList());
                break;
            case 2:
                List<Courses> unassignedCourses = courses.stream().filter(course -> !course.isAssigned())
                        .collect(Collectors.toList());
                /*为每个未分配课程进行分配操作
                * 每个课程给它分配两个老师(根据教师编号)
                * 每个课程的每个教学班给它分配至少20个学生
                * */
                for (Courses course : unassignedCourses) {
                    System.out.println("请为课程分配至少两个老师");
                    int count=0;
                    System.out.println("分配教师选项展示：" +
                            "\n1.教师信息展示" +
                            "\n2.开始分配");
                    boolean assignTeacherFlag = true;
                    while (assignTeacherFlag) {
                        switch (sc.nextInt()){
                            case 1:
                                System.out.println("-------------------------------");
                                System.out.println("教师信息展示");
                                teachers.forEach(new TeacherService()::viewTeacherAllInfo);
                                System.out.println("-------------------------------");
                                break;
                            case 2:
                                while (count<CLASS_ID_LIST_SIZE) {
                                    System.out.println("请输入教师编号");
                                    String teacherId = sc.next();
                                    Optional<Teacher> teacherOpt = teachers.stream().filter(t -> t.getTeacherId().equals(teacherId)).findFirst();
                                    //存在则分配，并修改Teacher中的 List<String> classId
                                    if(teacherOpt.isPresent()){
                                        System.out.println("分配成功!");
                                        teacherOpt.get().getClassId().add(course.getClassId().get(count));
                                        count++;
                                    }
                                    else{
                                        System.out.println("教师编号不存在，分配失败，请重新分配!");
                                    }
                                }
                                assignTeacherFlag = false;
                                break;
                        }
                    }
                    System.out.println("请为课程的每个教学班分配至少二十个学生");
                    for(int i=0;i<CLASS_ID_LIST_SIZE;i++){
                        int countStudent=0;
                        boolean assignStudentFlag=true;
                        while (assignStudentFlag) {

                        }
                    }

                }
                break;
        }
    }

    @Override
    public Optional<Courses> findCourse(ArrayList<Courses> courses) {
        Scanner sc=new Scanner(System.in);
        System.out.println("1.课程编号查询");
        System.out.println("2.课程名称查询");
        Optional<Courses> opt=Optional.empty();
        switch (sc.nextInt()){
            case 1:
                System.out.println("请输入查询的课程编号......");
                String id = sc.next();
                Optional<Courses> idOpt = courses.stream().filter(c -> c.getCourseId().equals(id)).findFirst();
                opt=idOpt;
                break;
            case 2:
                System.out.println("请输入查询的课程名称......");
                String name = sc.next();
                Optional<Courses> nameOpt = courses.stream().filter(c -> c.getCourseName().equals(name)).findFirst();
                opt=nameOpt;
        }
        return opt;
    }

    @Override
    public void alterCourseShow(ArrayList<Courses> courses) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请选择您要进行的操作：" +
                "\n1.查看所有课程的信息" +
                "\n2.通过课程编号 或 课程名称查看对应课程信息");
        switch (sc.nextInt()){
            case 1:
                System.out.println("-------------------------------");
                System.out.println("以下是所有课程的信息：\n");
                courses.forEach(this::showCourseAllInfo);
                break;
            case 2:
                System.out.println("-------------------------------");
                Optional<Courses> opt = findCourse(courses);
                if(opt.isPresent()){
                    System.out.println("查询成功，该课程的信息是：");
                    showCourseAllInfo(opt.get());

                }
                else{
                    System.out.println("查询失败，不存在该课程编号 或 课程名称，请核实无误后再次尝试。");

                }
                break;
        }
    }

    @Override
    public  void showCourseAllInfo(Courses c) {
        StringBuilder sb=new StringBuilder();
        sb.append("所属学院：").append(c.getDepartment())
                .append("\t课程编号：").append(c.getCourseId())
                .append("\t课程名称：").append(c.getCourseName())
                .append("\n教学班号：").append(c.getClassId())
                .append("\n成绩权重(依次为平时成绩、期中成绩、期末成绩、实验成绩):").append(c.getWeight());
        System.out.println(sb);
        System.out.println();
    }
}