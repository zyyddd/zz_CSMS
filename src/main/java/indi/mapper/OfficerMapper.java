//@Time:2021/10/25 18:44
//@Author:aFun

package indi.mapper;

import indi.domain.*;

import java.util.List;
import java.util.Map;

public interface OfficerMapper {
    // 课程设置审批
    public boolean examineAndapprove(Course course);         // 1表示审核通过，2表示审核中，3表示审核不通过

    // 选课结果管理
    public List<Map<String, Object>> selectionResult();

    //选课结果退选
    public boolean delSelectionResult(Integer stuId, Integer couId);

    // 成绩单生成
    public List<Map<String, Object>> scoreOrder(Student student);

    // 统计各项成绩
    public Map<Object, List<Object>> countResult(int start, int rows, Map<String, String[]> condition);

    // 分页相关的查询
    public int findTotalCount(Map<String, String[]> condition);

    public List<Course> findByPage(int start, int rows, Map<String, String[]> condition);

    public int findTotalCountForSelection(int start, int rows, Map<String, String[]> condition);

    public List<OfficerCountBean> findSelectionByPage(int start, int rows, Map<String, String[]> condition);

    public int findTotalCountForSelectionMessage(Map<String, String[]> condition);

    public List<StudentScoreAndName> findSelectionMessageByPage(int start, int rows, Map<String, String[]> condition);

}
