//@Time:2021/10/26 11:48
//@Author:aFun

package indi.service;

import indi.domain.Course;
import indi.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    // 选课
    public boolean selection(String account, String couName, String teaName);

    // 展示学生课程表
    public List<Map<String, Object>> showCourse(String account);

    // 成绩查询
    public List<Object> scoreInquiry(String account);

    //退选课程
    public boolean delCourse(String account,String couName);

    //显示未选课程
    public List<Map<String, Object>> enselectedCourse(String account);

}
