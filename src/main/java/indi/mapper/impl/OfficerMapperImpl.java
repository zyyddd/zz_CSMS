//@Time:2021/10/26 18:44
//@Author:aFun

package indi.mapper.impl;

import indi.domain.*;
import indi.mapper.OfficerMapper;
import indi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class OfficerMapperImpl implements OfficerMapper {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public boolean examineAndapprove(Course course) {          // 1表示审核通过，2表示审核中，3表示审核不通过
        String sql = "update course set judge=?,isopen=? where couId=?";
        int update = template.update(sql, course.getJudge(), course.getIsopen(), course.getCouId());
        return update > 0 ? true : false;
    }

    @Override
    public List<Map<String, Object>> selectionResult() {
        String sql = "select name,couName,c.couId from user u,student s, stu2score ss,course c where u.account=s.account and" +
                " s.stuId = ss.stuId and ss.couId = c.couId ORDER BY name";
        List<Map<String, Object>> maps = template.queryForList(sql);

        return maps;
    }

    @Override
    public List<Map<String, Object>> scoreOrder(Student student) {
        String sql = "select ss.stuId,u.name,c.couName,ss.score from stu2score ss,student s,course c,user u " +
                "where ss.stuId=s.stuId and s.account=u.account and ss.couId=c.couId and ss.stuId=?";
        List<Map<String, Object>> maps = template.queryForList(sql, student.getStuId());
//        for (Map<String, Object> map : maps) {
//            System.out.println(map);
//        }
        return maps;
    }

    @Override
    public Map<Object, List<Object>> countResult(int start, int rows, Map<String, String[]> condition) {
        String sql1 = "select couId from course where 1=1";             // 查出全部课程
        StringBuilder sb = new StringBuilder(sql1);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }

        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        List<Course> query = template.query(sb.toString(), new BeanPropertyRowMapper<Course>(Course.class), params.toArray());
//        List<Map<String, Object>> maps = template.queryForList(sql1);

        // 统计选课人数 先查出全部课程 再根据stu_score表查出相应的课程共有多少学生选择
        Map<Object, List<Object>> result = new HashMap<>();
        for (Course course : query) {
            String sql2 = "select count(stuId),max(score),min(score),avg(score) from stu2score where couId = ? ";
            String sql3 = "select couName from course where couId = ?";               // 通过课程号查找出相应的课程名
            String sql4 = "select count(stuId) from stu2score where couId=? and score>=60";       // 统计课程及格人数
            List<Map<String, Object>> courseCount = template.queryForList(sql2, course.getCouId());
            List<Map<String, Object>> courseName = template.queryForList(sql3, course.getCouId());
            List<Map<String, Object>> countPass = template.queryForList(sql4, course.getCouId());
//            System.out.println(courseCount);
//            System.out.println(courseName);
            result.put(courseName.get(0).get("couName"), Arrays.asList(courseCount.get(0).get("count(stuId)"),
                    courseCount.get(0).get("max(score)"), courseCount.get(0).get("min(score)"),
                    courseCount.get(0).get("avg(score)"), countPass.get(0).get("count(stuId)")));
            // 课程名 选课人数 最高成绩 最低成绩 平均成绩 及格人数
        }
//        for (Map<String, Object> map : maps) {
//            String sql2 = "select count(stuId),max(score),min(score),avg(score) from stu2score where couId = ? ";
//            String sql3 = "select couName from course where couId = ?";               // 通过课程号查找出相应的课程名
//            String sql4 = "select count(stuId) from stu2score where couId=? and score>=60";       // 统计课程及格人数
//            List<Map<String, Object>> courseCount = template.queryForList(sql2, map.get("couId"));
//            List<Map<String, Object>> courseName = template.queryForList(sql3, map.get("couId"));
//            List<Map<String, Object>> countPass = template.queryForList(sql4, map.get("couId"));
////            System.out.println(courseCount);
////            System.out.println(courseName);
//            result.put(courseName.get(0).get("couName"), Arrays.asList(courseCount.get(0).get("count(stuId)"),
//                    courseCount.get(0).get("max(score)"), courseCount.get(0).get("min(score)"),
//                    courseCount.get(0).get("avg(score)"), countPass.get(0).get("count(stuId)")));
//            // 课程名 选课人数 最高成绩 最低成绩 平均成绩 及格人数
//        }
        return result;
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from course where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<Course> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from course where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }

        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<Course>(Course.class), params.toArray());
    }

    @Override
    public int findTotalCountForSelection(int start, int rows, Map<String, String[]> condition) {
        Map<Object, List<Object>> objectListMap = this.countResult(start, rows, condition);
        return objectListMap.size();
    }

    @Override
    public List<OfficerCountBean> findSelectionByPage(int start, int rows, Map<String, String[]> condition) {
        List<OfficerCountBean> ocbs = new ArrayList<>();
        Map<Object, List<Object>> objectListMap = this.countResult(start, rows, condition);

        for (Map.Entry<Object, List<Object>> entry : objectListMap.entrySet()) {
            List<Object> value = entry.getValue();
            String couName = entry.getKey().toString();
            Integer countNum = value.get(0) == null ? 0 : Integer.parseInt(value.get(0).toString());
            Double maxScore = value.get(1) == null ? 0 : Double.parseDouble(value.get(1).toString());
            Double minScore = value.get(2) == null ? 0 : Double.parseDouble(value.get(2).toString());
            Double avgScore = value.get(3) == null ? 0 : Double.parseDouble(value.get(3).toString());
            Integer passNum = value.get(4) == null ? 0 : Integer.parseInt(value.get(0).toString());
            OfficerCountBean ocb = new OfficerCountBean(couName, countNum, maxScore, minScore, avgScore, passNum);
//            System.out.println(ocb);
            ocbs.add(ocb);
        }
        return ocbs;
    }

    @Override
    public int findTotalCountForSelectionMessage(Map<String, String[]> condition) {
        String sql = "select count(*) from stu2score,user,student,course where 1= 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {

                if (key.equals("stuId")) {
                    sb.append(" and student." + key + " like ? ");
                } else {
                    sb.append(" and " + key + " like ? ");
                }

                params.add("%" + value + "%");

            }
        }
        sb.append(" and user.account = student.account and student.stuId = stu2score.stuId and course.couId = stu2score.couId");
//        System.out.println("1" + sb);
//        System.out.println(params);
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());

    }

    @Override
    public List<StudentScoreAndName> findSelectionMessageByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from stu2score,user,student,course where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                if (key.equals("stuId")) {
                    sb.append(" and student." + key + " like ? ");
                } else {
                    sb.append(" and " + key + " like ? ");

                }
                params.add("%" + value + "%");
            }
        }
        sb.append(" and user.account = student.account and student.stuId = stu2score.stuId and course.couId = stu2score.couId");
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
//        System.out.println("2" + sql);
//        System.out.println(params);
        List<StudentScoreAndName> query = template.query(sql, new BeanPropertyRowMapper<StudentScoreAndName>(StudentScoreAndName.class), params.toArray());
//        System.out.println(query);
        return query;
    }

    @Override
    public boolean delSelectionResult(Integer stuId, Integer couId) {
            String sql = "delete from stu2score where stuId = ? and couId = ?";
        int update = template.update(sql, stuId, couId);
        return update > 0 ? true : false;
    }
}














