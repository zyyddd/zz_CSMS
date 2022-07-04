//@Time:2021/10/26 15:28
//@Author:aFun

package indi.service.impl;

import indi.domain.*;
import indi.mapper.TeacherMapper;
import indi.mapper.impl.TeacherMapperImpl;
import indi.service.TeacherService;

import java.util.List;
import java.util.Map;

public class TeacherServiceImpl implements TeacherService {

    private TeacherMapper tm = new TeacherMapperImpl();

    @Override
    public boolean recordScore(StudentScore studentScore) {
        return tm.recordScore(studentScore);
    }

    @Override
    public List<Map<String, Object>> printStuOrder(Teacher teacher) {
        return tm.printStuOrder(teacher);
    }

    @Override
    public boolean addCourse(Course course) {
        return tm.addCourse(course);
    }

    @Override
    public boolean deleteCourse(Integer couId) {
        return tm.deleteCourse(couId);
    }

    @Override
    public boolean updateCourse(Course course) {
        return tm.updateCourse(course);
    }

    @Override
    public Course findCourseById(Integer couId) {
        return tm.findCourseById(couId);
    }

    @Override
    public void delSelectedCourse(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                tm.deleteCourse(Integer.parseInt(id));
            }
        }
    }


    @Override
    public PageBean<Course> findCourseByPage(String _currentPage, String _rows, Map<String, String[]> condition, Integer teaId) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<Course> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int totalCount = tm.findTotalCount(condition, teaId);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<Course> list = tm.findByPage(start, rows, condition, teaId);
        pb.setList(list);

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public PageBean<StudentOrder> findStudentByPage(String _currentPage, String _rows, Map<String, String[]> condition, Integer teaId) {
//        System.out.println("enter findStudentService...");
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<StudentOrder> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int totalCount = tm.findTotalStuCount(condition, teaId);
//        System.out.println(totalCount);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<StudentOrder> list = tm.findByPageStu(start, rows, condition, teaId);
        pb.setList(list);

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Integer findByAccount(String account) {
        return tm.findByAccount(account);
    }

    @Override
    public PageBean<Course> findImOpenCourse(String _currentPage, String _rows, Map<String, String[]> condition, Integer teaId) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<Course> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int start = (currentPage - 1) * rows;
        int totalCount = tm.findTotalimOpenCourse(start, rows, condition, teaId);
        pb.setTotalCount(totalCount);
        List<Course> list = tm.findCourseForOpen(start, rows, condition, teaId);
        pb.setList(list);

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public boolean selectCourse(Course course, Teacher teacher) {
        return tm.selectCourse(course, teacher);
    }
}
