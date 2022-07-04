//@Time:2021/11/2 16:55
//@Author:aFun

package indi.mapper.loginMethod;

import indi.domain.User;

public class TeacherLogin extends MethodForLogin {

    @Override
    public String roleLogin(User user) {
        return "/teacherIndex.jsp";
    }
}