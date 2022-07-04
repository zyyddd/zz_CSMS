//@Time:2021/10/26 15:27
//@Author:aFun

package indi.mapper.impl;

import indi.domain.*;
import indi.mapper.TeacherMapper;
import indi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeacherMapperImpl implements TeacherMapper {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public boolean recordScore(StudentScore ss) {
        // 期末成绩的录入
        String sql = "UPDATE stu2score SET score=? WHERE ssid=?";
        int update = template.update(sql, ss.getScore(), ss.getSsId());
//        System.out.println("成绩录入。。。"+ss.getSsId()+" "+ss.getScore());
        return update > 0 ? true : false;
    }

    @Override
    public List<Map<String, Object>> printStuOrder(Teacher teacher) {
        // 学生名单的打印
        // 根据教师id获取对应的课程
        // 根据教师id获取学生以及学生的成绩

        String sql = "SELECT t.`teaid`,u.`account`,c.`couId`," +
                "couName,couHour,stuId,score " +
                "FROM teacher t,tea2cou tc," +
                "course c,stu2score ss,USER u WHERE t.`teaid`=tc.`teaId` " +
                "AND tc.`couId`=c.`couId` AND ss.`teaId`=t.`teaid` AND t.`account`=u.`account` and t.`teaId`=" + teacher.getTeaId();

        return template.queryForList(sql);
    }

    @Override
    public boolean addCourse(Course course) {
        String sql = "insert into course values(null,?,?,?,2,2,?)";
        int i = template.update(sql, course.getCouName(),
                course.getCouHour(), course.getCouDes(), course.getLimitNum());
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteCourse(Integer couId) {
        String sql = "delete from course where couid=?";
        int update = template.update(sql, couId);
        return update > 0 ? true : false;
    }

    @Override
    public boolean updateCourse(Course course) {
        String sql = "update course set couName=?,couHour=?,couDes=?,judge=?,limitNum=? where couId=?";
        int update = template.update(sql, course.getCouName(), course.getCouHour(), course.getCouDes(),
                course.getJudge(), course.getLimitNum(), course.getCouId());
        return update > 0 ? true : false;
    }

    @Override
    public Course findCourseById(Integer couId) {
        String sql = "select * from course where couId=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Course>(Course.class), couId);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition, Integer teaId) {
        String sql = "select count(*) from course c,tea2cou tc where 1 = 1 and c.couId=tc.couId and tc.teaId=" + teaId;
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
    public List<Course> findByPage(int start, int rows, Map<String, String[]> condition, Integer teaId) {
        String sql = "select c.couId,c.couName,c.couHour,c.couDes,c.judge,c.isopen,c.limitNum" +
                " from course c,tea2cou tc where 1 = 1 and c.couId=tc.couId and tc.teaId=" + teaId;
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
    public int findTotalStuCount(Map<String, String[]> condition, Integer teaId) {
        String sql = "SELECT COUNT(DISTINCT ss.ssId) " +
                "FROM course c,tea2cou tc,stu2score ss,student s,USER u " +
                "WHERE ss.couId=c.couId AND ss.stuId=s.stuId AND ss.teaId=tc.teaId " +
                "AND s.account=u.account AND ss.teaId=" + teaId;
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (key.equals("stuId") && value != null && !"".equals(value)) {
                sb.append(" and ss.stuId=" + value);
                continue;
            }
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
//        System.out.println(sb.toString());
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<StudentOrder> findByPageStu(int start, int rows, Map<String, String[]> condition, Integer teaId) {
//        System.out.println("enter findStuMapper...");

        String sql = "SELECT DISTINCT ss.ssId,ss.couId,c.couName,ss.teaId,ss.stuId,u.name as stuName,u.gender,ss.score " +
                "FROM course c,tea2cou tc,stu2score ss,student s,USER u " +
                "WHERE ss.couId=c.couId AND ss.stuId=s.stuId AND ss.teaId=tc.teaId " +
                "AND s.account=u.account AND ss.teaId=" + teaId;
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (key.equals("stuId") && value != null && !"".equals(value)) {
                sb.append(" and ss.stuId=" + value);
                continue;
            }
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<StudentOrder>(StudentOrder.class), params.toArray());
    }

    @Override
    public Integer findByAccount(String account) {
        String sql = "select teaId from teacher where account=?";
        return template.queryForObject(sql, Integer.class, account);
    }

    @Override
    public List<Course> findCourseForOpen(int start, int rows, Map<String, String[]> condition, Integer teaId) {
        // 需要排除教师已有的课程
//        String sql="SELECT couId,couName FROM course WHERE isopen=2 AND judge=1";
        String sql = "SELECT DISTINCT course.couId,couName " +
                "FROM course,tea2cou " +
                "WHERE isopen=2 AND judge=1 AND course.couId NOT IN" +
                "(SELECT couId FROM tea2cou WHERE teaId=" + teaId + ")";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (key.equals("couId") && value != null && !"".equals(value)) {
                sb.append(" and course.couId=" + value);
                continue;
            }
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ?");
                params.add("%" + value + "%");
            }
        }
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        List<Course> result = template.query(sb.toString(), new BeanPropertyRowMapper<Course>(Course.class), params.toArray());
        return result;
    }

    @Override
    public int findTotalimOpenCourse(int start, int rows, Map<String, String[]> condition, Integer teaId) {
//        String sql = "SELECT count(couId) FROM course WHERE isopen=2 AND judge=1";
        String sql = "SELECT count(DISTINCT course.couId) " +
                "FROM course,tea2cou " +
                "WHERE isopen=2 AND judge=1 AND course.couId NOT IN" +
                "(SELECT couId FROM tea2cou WHERE teaId=" + teaId + ")";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (key.equals("couId") && value != null && !"".equals(value)) {
                sb.append(" and course.couId=" + value);
                continue;
            }
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public boolean selectCourse(Course course, Teacher teacher) {
        String sql = "INSERT INTO tea2cou VALUES(NULL,?,?)";
        int update = template.update(sql, teacher.getTeaId(), course.getCouId());
        return update > 0 ? true : false;
    }

}
