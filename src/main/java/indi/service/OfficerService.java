//@Time:2021/10/26 14:36
//@Author:aFun

package indi.service;

import indi.domain.*;

import java.util.List;
import java.util.Map;

public interface OfficerService {

    // 课程设置审批
    public boolean examineAndapprove(Course course);

    // 分页查询课程
    public PageBean<Course> findCourseJudgeByPage(String _currentPage, String _rows, Map<String, String[]> condition);

    // 分页查询选课以及成绩的情况
    public PageBean<OfficerCountBean> findSelectionByPage(String _currentPage, String _rows, Map<String, String[]> condition);

    // 选课结果管理
    public List<Map<String, Object>> selectionResult();

    //选课结果退选
    public boolean delSelectionResult(Integer stuId, Integer couId);

    // 成绩单生成
    public List<Map<String, Object>> scoreOrder(Student student);

    // 统计
    public Map<Object, List<Object>> countResult(int start, int rows, Map<String, String[]> condition);

    //分页查询选课结果管理
    public PageBean<StudentScoreAndName> findMessagerByPage(String _currentPage, String _rows, Map<String, String[]> condition);
}
