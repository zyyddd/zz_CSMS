//@Time:2021/10/26 11:45
//@Author:aFun

package indi.service;

import indi.domain.*;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    // 期末成绩的录入
    public boolean recordScore(StudentScore studentScore);

    // 学生名单的打印
    public List<Map<String, Object>> printStuOrder(Teacher teacher);

    // 增加新的课程信息
    public boolean addCourse(Course course);

    // 删除旧的课程信息
    public boolean deleteCourse(Integer couId);

    // 修改已有的课程信息
    public boolean updateCourse(Course course);

    // 根据课程id查找课程
    public Course findCourseById(Integer couId);

    void delSelectedCourse(String[] ids);

    // 查询所有的课程信息
    public PageBean<Course> findCourseByPage(String _currentPage, String _rows, Map<String, String[]> condition, Integer teaId);

    // 查询本教师拥有的学生信息
    public PageBean<StudentOrder> findStudentByPage(String _currentPage, String _rows, Map<String, String[]> condition, Integer teaId);

    // 通过账号查对应的教师id
    public Integer findByAccount(String account);

    // 查询可开课的课程
    public PageBean<Course> findImOpenCourse(String _currentPage, String _rows, Map<String, String[]> condition, Integer teaId);

    // 选择课程
    public boolean selectCourse(Course course, Teacher teacher);
}
