//@Time:2021/10/25 19:20
//@Author:aFun

import indi.domain.User;
import indi.service.LoginService;
import indi.service.ManagerService;
import indi.service.impl.LoginServiceImpl;
import indi.service.impl.ManagerServiceImpl;
import org.junit.jupiter.api.Test;

public class TestManager {
    @Test
    public void testLogin() {
        User user = new User(-1, "root", "123", 1, "", "");
        LoginService ls = new LoginServiceImpl();
        User loginUser = ls.login(user);
        System.out.println(loginUser);
    }

    @Test
    public void testAdd() {
        User user = new User(30001, "testTea", "123", 3, "Tea1", "女");
        ManagerService ms = new ManagerServiceImpl();
        System.out.println(ms.add(user));

    }

    @Test
    public void testRead() {
        ManagerService ms = new ManagerServiceImpl();
        System.out.println(ms.findAll());
    }

    @Test
    public void testUpdate() {
        ManagerService ms = new ManagerServiceImpl();
        User user = new User(20001, "off1", "123", 2, "off1", "男");
        System.out.println(ms.update(user));
    }

    @Test
    public void testDelete() {
        ManagerService ms = new ManagerServiceImpl();
        User user = new User(30001, "testTea", "123", 3, "Tea1", "女");
        System.out.println(ms.delete(user.getUid()));
    }

}
