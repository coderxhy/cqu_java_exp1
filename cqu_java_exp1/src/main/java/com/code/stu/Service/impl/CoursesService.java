package com.code.stu.Service.impl;

import com.code.stu.Service.CoursesInterface;
import com.code.stu.entity.Courses;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private static String[] CoursesArray = {
            "线性代数", "概率与统计", "高等数学",
            "数学实验", "工程师职业素养", "电路原理",
            "数字逻辑", "数据库设计","离散数学" ,
            "大学物理", "大学英语", "大学体育",
            "中国近代史纲要","马克思主义基本原理","文明经典"
    };
    public   ArrayList<String> classIdArray=GenerateClassId();

//    产生ClassId -> "CLASS"+[0,CLASS_NUM-1]
    @Override
    public ArrayList<String> GenerateClassId() {
        Random r = new Random();
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
        Random r=new Random();
        String department=DEPARTMENTS[r.nextInt(DEPARTMENTS.length)];
        String major=majorsMap.get(department).get(r.nextInt(majorsMap.get(department).size()));
        Map.Entry<String,String> majorEntry=new AbstractMap.SimpleEntry<>(department,major);
        return majorEntry;
    }
    //随机为所有的课程产生课程编号,并添加到映射关系中
    @Override
    public HashMap<String, String> GenerateCourseIds() {
        HashMap<String,String> CourseIdMap=new HashMap<>();
        Random r=new Random();
        for(int i=0;i<CoursesArray.length;i++){
            String courseId="COURSE"+r.nextInt(1000);
            CourseIdMap.put(CoursesArray[i],courseId);
        }
        return CourseIdMap;
    }

    @Override
    public ArrayList<Courses> randomGenerateInfo(){
        ArrayList<Courses> courses=new ArrayList<>();
        HashSet<String> classIdSet=new HashSet<>();
        HashSet<String> courseNameSet=new HashSet<>();
        Random r=new Random();
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
            int randomCourseId=r.nextInt(CoursesArray.length);
            while(courseNameSet.contains(CoursesArray[randomCourseId])){
                randomCourseId=(randomCourseId+1)%CoursesArray.length;
            }
            String Name=CoursesArray[randomCourseId];
            courseNameSet.add(Name);
            Courses course = Courses.builder().department(DEPARTMENTS[r.nextInt(DEPARTMENTS.length)])
                    .courseId(idMap.get(Name)).courseName(Name).classId(classIdList)
                    .weight(Arrays.asList(0.3,0.1,0.4,0.2))
                    .build();
            courses.add(course);
        }
        return courses;
    }
}