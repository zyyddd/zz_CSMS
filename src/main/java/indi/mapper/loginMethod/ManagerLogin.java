//@Time:2021/11/2 16:54
//@Author:aFun

package indi.mapper.loginMethod;

import indi.domain.User;

public class ManagerLogin extends MethodForLogin {
    @Override
    public String roleLogin(User user) {
        return "/managerIndex.jsp";
    }
}
