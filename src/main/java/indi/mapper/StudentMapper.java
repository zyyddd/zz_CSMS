//@Time:2021/10/25 18:45
//@Author:aFun

package indi.mapper;

import indi.domain.Course;
import indi.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    // 选课
    public boolean selection(String account, String couName, String teaName);

    // 展示学生课程表
    public List<Map<String, Object>> showCourse(String account);

    //退选课程
    public boolean delCourse(String account,String couName);

    // 成绩查询
    public List<Object> scoreInquiry(String account);

    //显示未选课程
    public List<Map<String, Object>> enselectedCourse(String account);


    // 通过couName查询couId
    public Integer findcouIdBycouName(String couName);

    // 通过account去找到stuId
    public Integer findstuIdByAccount(String account);

    //通过couId+stuId唯一标识ssid
    public Integer findssidByNameandstuId(Integer couId,Integer stuId);

    // 通过ssId执行删除操作
    public boolean deleteByssid(Integer ssId);

}
