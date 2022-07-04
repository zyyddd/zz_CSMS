//@Time:2021/10/25 19:12
//@Author:aFun

package indi.service;

import indi.domain.User;

public interface LoginService {
    public User login(User user);

    // 使用策略模式解决用户数量改变而存在的不确定性
    public String stragyMethodForRole(User user);

    // 修改密码
    public boolean alterPassword(User user, String oldPassword);
}
