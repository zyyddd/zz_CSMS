//@Time:2021/10/26 19:03
//@Author:aFun

import indi.domain.Course;
import indi.domain.OfficerCountBean;
import indi.domain.Student;
import indi.service.OfficerService;
import indi.service.impl.OfficerServiceImpl;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestOfficer {
    OfficerService os = new OfficerServiceImpl();

//    @Test
//    public void testCount() {
//        Map<Object, List<Object>> objectListMap = os.countResult();
//        for (Map.Entry<Object, List<Object>> entry : objectListMap.entrySet()) {
//            System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue().toArray(new Object[0])));
//        }
//        System.out.println(objectListMap.size());
//    }

    @Test
    public void testScoreOrder() {
        Student student = new Student(40001, "zs", "123", 4, "stu1", "男", 30000);
        List<Map<String, Object>> maps = os.scoreOrder(student);
        System.out.println(maps);
    }

    @Test
    public void testJudge() {
        Course course = new Course(4, "高等数学", 4, null, 2, 2, 150);
        os.examineAndapprove(course);
    }
    @Test
    public void testSelectTionResult() {

        System.out.println(os.selectionResult());
    }
}
