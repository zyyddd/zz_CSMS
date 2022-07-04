//@Time:2021/10/26 18:43
//@Author:aFun

package indi.service.impl;

import indi.domain.*;
import indi.mapper.OfficerMapper;
import indi.mapper.impl.OfficerMapperImpl;
import indi.service.OfficerService;

import java.util.List;
import java.util.Map;

public class OfficerServiceImpl implements OfficerService {

    private OfficerMapper om = new OfficerMapperImpl();

    @Override
    public boolean examineAndapprove(Course course) {

        return om.examineAndapprove(course);
    }

    @Override
    public PageBean<Course> findCourseJudgeByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<Course> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int totalCount = om.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<Course> list = om.findByPage(start, rows, condition);
        pb.setList(list);

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public PageBean<OfficerCountBean> findSelectionByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<OfficerCountBean> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int totalCount = om.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<OfficerCountBean> list = om.findSelectionByPage(start, rows, condition);

        pb.setList(list);

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public List<Map<String, Object>> selectionResult() {
        return om.selectionResult();
    }


    @Override
    public PageBean<StudentScoreAndName> findMessagerByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<StudentScoreAndName> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int totalCount = om.findTotalCountForSelectionMessage(condition);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<StudentScoreAndName> list = om.findSelectionMessageByPage(start, rows, condition);
        pb.setList(list);

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public List<Map<String, Object>> scoreOrder(Student student) {
        return om.scoreOrder(student);
    }

    @Override
    public Map<Object, List<Object>> countResult(int start, int rows, Map<String, String[]> condition) {
        return om.countResult(start, rows, condition);
    }

    @Override
    public boolean delSelectionResult(Integer stuId, Integer couId) {
        return om.delSelectionResult(stuId,couId);
    }
}
