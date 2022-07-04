//@Time:2021/11/2 17:09
//@Author:aFun

import indi.domain.User;
import indi.mapper.LoginMapper;
import indi.mapper.impl.LoginMapperImpl;
import indi.service.LoginService;
import indi.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Test;

public class TestLogin {
    LoginService ls = new LoginServiceImpl();
    @Test
    public void testManagerLogin() {
        User user = new User(0, "aFun", "123456", 1, null, null);
        String s = ls.stragyMethodForRole(user);
        System.out.println(s);
    }

    @Test
    public void testOfficerLogin() {
        User user = new User(0, "off1", "123456", 2, null, null);
        String s = ls.stragyMethodForRole(user);
        System.out.println(s);
    }

    @Test
    public void testTeacherLogin() {
        User user = new User(0, "tea1", "123456", 3, null, null);
        String s = ls.stragyMethodForRole(user);
        System.out.println(s);
    }

    @Test
    public void testStudentLogin() {
        User user = new User(0, "stu1", "123456", 4, null, null);
        String s = ls.stragyMethodForRole(user);
        System.out.println(s);
    }

    @Test
    public void testAlterPwd() {
        LoginMapper lm = new LoginMapperImpl();
        User user = new User(0, "aFun", "123456", 1, null, null);
        String passwordByAccount = lm.findPasswordByAccount(user);
        System.out.println(passwordByAccount);
    }

    @Test
    public void testAlterPwd2() {
        User user = new User(0, "aFun", "123456", 1, null, null);
        boolean b = ls.alterPassword(user, "123");
        System.out.println(b);
    }
}
