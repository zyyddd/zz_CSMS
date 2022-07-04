//@Time:2021/10/25 18:51
//@Author:aFun

package indi.mapper;

import indi.domain.User;

// 登录接口
public interface LoginMapper {
    public User login(String account, String password);

    public String findPasswordByAccount(User user);         // 通过账号查找密码

    public boolean alterPassword(User user);
}
