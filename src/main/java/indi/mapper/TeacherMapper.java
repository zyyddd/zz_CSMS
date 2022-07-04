//@Time:2021/10/25 18:45
//@Author:aFun

package indi.mapper;

import indi.domain.*;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    // 期末成绩的录入：获取当前老师的所有课程 -> 根据teaId和couId记录相应学生的成绩
    public boolean recordScore(StudentScore ss);

    // 学生名单的打印
    public List<Map<String, Object>> printStuOrder(Teacher teacher);

    // 增加新的课程信息
    public boolean addCourse(Course course);

    // 删除旧的课程信息
    public boolean deleteCourse(Integer couId);

    // 修改已有的课程信息
    public boolean updateCourse(Course course);

    public Course findCourseById(Integer couId);

    // 分页相关的查询 查询所有的课程信息
    public int findTotalCount(Map<String, String[]> condition, Integer teaId);

    public List<Course> findByPage(int start, int rows, Map<String, String[]> condition, Integer teaId);

    public int findTotalStuCount(Map<String, String[]> condition, Integer teaId);

    public List<StudentOrder> findByPageStu(int start, int rows, Map<String, String[]> condition, Integer teaId);

    // 通过账号查找对应的教师id
    public Integer findByAccount(String account);

    // 查找审核通过judge=1以及未开课的课程isopen=2
    public List<Course> findCourseForOpen(int start, int rows, Map<String, String[]> condition, Integer teaId);

    public int findTotalimOpenCourse(int start, int rows, Map<String, String[]> condition, Integer teaId);

    // 教师选课
    public boolean selectCourse(Course course, Teacher teacher);
}
