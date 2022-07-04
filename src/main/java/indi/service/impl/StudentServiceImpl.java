//@Time:2021/10/26 21:56
//@Author:aFun

package indi.service.impl;

import indi.domain.Course;
import indi.domain.Student;
import indi.mapper.StudentMapper;
import indi.mapper.impl.StudentMapperImpl;
import indi.service.StudentService;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    StudentMapper sm = new StudentMapperImpl();

    @Override
    public boolean selection(String account, String couName, String teaName) {
        return sm.selection(account, couName,teaName);
    }

    @Override
    public List<Map<String, Object>> showCourse(String account) {
        return sm.showCourse(account);
    }

    @Override
    public List<Object> scoreInquiry(String account) {
        return sm.scoreInquiry(account);
    }

    @Override
    public boolean delCourse(String account, String couName) { return sm.delCourse(account,couName); }

    @Override
    public List<Map<String, Object>> enselectedCourse(String account) { return sm.enselectedCourse(account); }
}
