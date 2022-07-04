//@Time:2021/10/26 17:13
//@Author:aFun

import indi.domain.Course;
import indi.domain.StudentScore;
import indi.domain.Teacher;
import indi.mapper.TeacherMapper;
import indi.mapper.impl.TeacherMapperImpl;
import indi.service.TeacherService;
import indi.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class TestTeacher {
    TeacherService ts = new TeacherServiceImpl();

    @Test
    public void testRecord() {
        StudentScore ss = new StudentScore(2, 30001, 1, 60, 40001);
        boolean b = ts.recordScore(ss);
        System.out.println(b);
    }

    @Test
    public void testPrint() {
        Teacher t = new Teacher(30002, "tea2", "123456", 3, "tea2", "女", 30002);
        List<Map<String, Object>> maps = ts.printStuOrder(t);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    @Test
    public void testInsert() {
        Course course = new Course(3, "c", 16, null, 0, 0, 0);
        boolean b = ts.addCourse(course);
        System.out.println(b);
    }

    @Test
    public void testDelete() {
        Course course = new Course(3, "c#", 32, null, 1, 0, 0);
        boolean b = ts.deleteCourse(course.getCouId());
        System.out.println(b);
    }

    @Test
    public void testUpdate() {
        Course course = new Course(3, "c#", 32, null, 1, 0, 0);
        boolean b = ts.updateCourse(course);
        System.out.println(b);
    }

    @Test
    public void testFindByAccount() {
        System.out.println(ts.findByAccount("tea1"));
    }

    TeacherMapper tm = new TeacherMapperImpl();


}
