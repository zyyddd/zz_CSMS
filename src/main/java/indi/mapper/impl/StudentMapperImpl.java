//-*- coding = utf-8 -*-
//@Time:2021/10/26 16:29
//@Author:ZYD
//@File:StudentMapperImpl.py
//@Software: IntelliJ IDEA

package indi.mapper.impl;

import indi.domain.Course;
import indi.domain.Student;
import indi.domain.StudentScore;
import indi.domain.User;
import indi.mapper.StudentMapper;
import indi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.*;

public class StudentMapperImpl implements StudentMapper {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public boolean selection(String account, String couName, String teaName) {
        String sql = "select couId from course where couName = '" + couName + "'";
        System.out.println(sql);
        Integer couId = template.queryForObject(sql, Integer.class);
        String sql1 = "select teaId from teacher,user where user.`NAME` = '" + teaName + "' and user.account = teacher.account";
        System.out.println(sql1);
        Integer teaId = template.queryForObject(sql1, Integer.class);
        String sql2 = "INSERT INTO stu2score VALUES(NULL,?,?,-1," + teaId + ")";

        String sql3 = "select stuId from student,user where student.account = user.account and " +
                "user.account = '" + account + "'";
        System.out.println(sql3);
        Integer stuId = template.queryForObject(sql3, Integer.class);
        System.out.println(sql2);
        int result = template.update(sql2, stuId, couId);
        return result > 0 ? true : false;
    }

    @Override
    public List<Map<String, Object>> showCourse(String account) {
        String sql = "select stuId from student where account = '" + account + "'";

        Integer stuId = template.queryForObject(sql, Integer.class);
        String sql1 = "select  couId,teaId from stu2score where stuId = " + stuId;
        List<Map<String, Object>> maps = template.queryForList(sql1);
        for (Map<String, Object> map : maps) {

            String sql2 = "select couName from course where couId = " + map.get("couId");
            String couIdName = template.queryForObject(sql2, String.class);
            Integer teaId = (Integer) map.get("teaId");
            String sql3 = "select user.NAME from teacher,user where teacher.teaid = " + teaId +
                    " and teacher.account = user.account";
            String name = template.queryForObject(sql3, String.class);
            map.put("couId", couIdName);
            map.put("teaId", name);
        }
        return maps;
    }

    @Override
    public List<Object> scoreInquiry(String account) {
        String sql = "select stuId from student where account = '" + account + "'";
//        System.out.println(1+sql);

        Integer stuId = template.queryForObject(sql, Integer.class);
//        System.out.println(stuId);
        //当前用户各科的成绩 保存在result里头  最大值平均值最小值保存在maps1
        String sql1 = "select couId,score from stu2score where stuId = " + stuId;
//        System.out.println(2+sql1);
        List<Map<String, Object>> maps = template.queryForList(sql1);

        Map<Object, List<Object>> resultmap = new HashMap<>();


        for (Map<String, Object> map : maps) {
            List<Map<String, Object>> result = new ArrayList<>();
            //将couId改成课程相对应的名称
            String sql2 = "select couName from course where couId = " + map.get("couId");
            String couIdName = template.queryForObject(sql2, String.class);
            String sql3 = "select max(score), avg(score), min(score) from stu2score where couId = " + map.get("couId");
            List<Map<String, Object>> maps1 = template.queryForList(sql3);
//            System.out.println("maps1   "+maps1);
            //排名的sql语句
            String sql4 = "SELECT stuId,score, @rank := @rank + 1 AS ranks " +
                    " from stu2score p,(SELECT @rank := 0) q " +
                    " WHERE couId = " + map.get("couId") +
                    " ORDER BY score DESC";
            List<Map<String, Object>> maps2 = template.queryForList(sql4);
//            System.out.println("maps2  " + maps2);
            //排名
            Object temp = 0;
            for (Map<String, Object> map2 : maps2) {
                if (map2.get("stuId").equals(stuId)) {
                    temp = map2.get("ranks");
                }
            }
            map.put("couId", couIdName);
            result.add(map);
            Object countMaxScore = maps1.get(0).get("max(score)");
            Object countAvgScore = maps1.get(0).get("min(score)");
            Object countMinScore = maps1.get(0).get("avg(score)");
            Object CountRank = temp;
            resultmap.put(result, Arrays.asList(countMaxScore, countAvgScore, countMinScore, CountRank));
        }

        Map<Object, List<Object>> objectListMap = resultmap;
        List<Object> studentScoreList = new ArrayList<>();

        for (Map.Entry<Object, List<Object>> entry : objectListMap.entrySet()) {
            List<Object> studentList = new ArrayList<>();
            Object mapKey = entry.getKey();
            //最高分 最低分平均分 排名 mapValue
            List<Object> mapValue = entry.getValue();
            ArrayList tempMapKey = (ArrayList) mapKey;
            Object temp = tempMapKey.get(0);
            LinkedCaseInsensitiveMap map = (LinkedCaseInsensitiveMap) temp;
            //课程名
            Object couId = map.get("couId");
            Object score = map.get("score");
            studentList.add(couId);
            studentList.add(score);
//            System.out.println(couId);
//            System.out.println(score);
            for (Object i : mapValue) {
                studentList.add(i);
            }
            studentScoreList.add(studentList);
//            Map<String,Object> tempKeyMap = Map<String,Object>(temp);
//            System.out.println(temp);

//            System.out.println(mapKey + "：" + mapValue);
        }
        return studentScoreList;


    }


    @Override
    public boolean delCourse(String account, String couName) {
        String sql = "delete stu2score from (stu2score,student,course) where course.couName = '" + couName + "' AND course.couId =" +
                " stu2score.couId AND student.stuid = stu2score.stuId AND student.account = '" + account + "'";
        int update = template.update(sql);
        return update > 0 ? true : false;
    }


    @Override
    public List<Map<String, Object>> enselectedCourse(String account) {
        String sql = "select distinct course.couName from (course,stu2score,student) where course.couId NOT IN(\n" +
                " SELECT course.couId FROM (course,stu2score,student) where course.couId = stu2score.couId AND \n" +
                " stu2score.stuId = student.stuid AND student.account ='" + account +
                "' )" + "AND course.judge = 1 AND course.isopen " +
                " = 1 AND course.limitNum > 0 ";
        List<Map<String, Object>> maps = template.queryForList(sql);
        for (Map<String, Object> map : maps) {
            String couName = (String) map.get("couName");
            String sql1 = "SELECT `NAME` from user,teacher,tea2cou,course where course.couName ='" + couName +
                    "' AND course.couId = tea2cou.couId and tea2cou.teaId = teacher.teaid " +
                    " AND teacher.account = user.account";
            List<Map<String, Object>> maps1 = template.queryForList(sql1);
            map.put("teaName", maps1);
        }

        return maps;
    }

    @Override
    public Integer findcouIdBycouName(String couName) {
        Course c = new Course(null, couName, 0, null, 0, 0, 0);
        String sql = "select couId from course where couName='" + c.getCouName() + "'";
        List<Course> query = template.query(sql, new BeanPropertyRowMapper<Course>(Course.class));

        return query.get(0).getCouId();
    }

    @Override
    public Integer findstuIdByAccount(String account) {
        Student s = new Student(0, account, null, 0, null, null, null);
        String sql = "select stuId from user where account=?";
        List<Student> query = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class), s.getAccount());
        return query.get(0).getStuId();
    }

    @Override
    public Integer findssidByNameandstuId(Integer couId, Integer stuId) {
        StudentScore ss = new StudentScore(null, stuId, couId, null, 0);
        String sql = "select ssId from stu2score where couId=? and stuId=?";
        List<StudentScore> query = template.query(sql, new BeanPropertyRowMapper<StudentScore>(StudentScore.class), ss.getCouId(), ss.getStuId());
        return query.get(0).getSsId();
    }

    @Override
    public boolean deleteByssid(Integer ssId) {
        String sql = "delete from stu2score where ssId=?";
        int update = template.update(sql);
        return update > 0 ? true : false;
    }

}
