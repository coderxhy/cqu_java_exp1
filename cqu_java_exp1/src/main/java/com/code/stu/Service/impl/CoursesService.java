package com.code.stu.Service.impl;

import com.code.stu.Service.CoursesInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.stu.entity.Courses;
import com.code.stu.mapper.CoursesMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class CoursesService
//        extends ServiceImpl<CoursesMapper, Courses>
        implements CoursesInterface {
    private static String[] DEPARTMENTS = {
            "计算机学院", "机械工程学院", "电子信息学院", "生物科学学院", "经济管理学院"
    };
    private static HashMap<String, ArrayList<String>> majorsMap = new HashMap<String, ArrayList<String>>() {{
        put(DEPARTMENTS[0], new ArrayList<>(Arrays.asList("软件工程", "网络工程", "人工智能")));
        put(DEPARTMENTS[1], new ArrayList<>(Arrays.asList("机械设计", "自动化", "材料科学")));
        put(DEPARTMENTS[2], new ArrayList<>(Arrays.asList("电气工程", "通信工程", "微电子")));
        put(DEPARTMENTS[3], new ArrayList<>(Arrays.asList("生物技术", "生态学", "遗传学")));
        put(DEPARTMENTS[4], new ArrayList<>(Arrays.asList("市场营销", "财务管理", "人力资源")));
    }};
    @Override
    public ArrayList<Courses> randomGenerateInfo(){
        return null;
    }
}