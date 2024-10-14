package com.code.stu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.stu.entity.Courses;

import java.util.ArrayList;

public interface CoursesInterface
//        extends IService<Courses>
{
    ArrayList<Courses> randomGenerateInfo();
}
