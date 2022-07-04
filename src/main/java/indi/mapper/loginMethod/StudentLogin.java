//@Time:2021/11/2 16:56
//@Author:aFun

package indi.mapper.loginMethod;

import indi.domain.User;

public class StudentLogin extends MethodForLogin {

    @Override
    public String roleLogin(User user) {
        return "/studentIndex.jsp";
    }
}
