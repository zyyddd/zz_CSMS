//@Time:2021/10/26 22:26
//@Author:aFun

import indi.domain.Course;
import indi.domain.Student;
import indi.mapper.StudentMapper;
import indi.mapper.impl.StudentMapperImpl;
import indi.service.StudentService;
import indi.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class TestStudent {

    StudentService ss = new StudentServiceImpl();


    @Test
    public void TestSelection(){
        Student student = new Student(40003,"zyd","123",4,"stu3","男",30001);
        Course course = new Course(2,"c++",32, null,1,1,50);
        System.out.println(ss.selection("stu1","java","tea1"));
    }

    @Test
    public void TestShowCourse(){
        Student student = new Student(40003,"stu1","123",4,"stu3","男",30001);
        String account = student.getAccount();
        List<Map<String, Object>> maps = ss.showCourse(account);
        for (Map<String,Object> map: maps){
            System.out.println(map.get("couId"));
        }
        System.out.println(maps);

    }

    @Test
    public void TestDelCourse(){
        System.out.println(ss.delCourse("stu1","c语言"));

    }

    @Test
    public void TestEnselectCourse(){
        System.out.println(ss.enselectedCourse("stu1"));
    }

    @Test
    public void myTest(){
        StudentMapper sm=new StudentMapperImpl();
        Integer result1 = sm.findcouIdBycouName("c语言");
        System.out.println(result1);
        Integer result2 = sm.findstuIdByAccount("stu1");
        System.out.println(result2);
        Integer result3 = sm.findssidByNameandstuId(result1, result2);
        System.out.println(result3);

    }


}
