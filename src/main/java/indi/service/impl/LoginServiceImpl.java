//@Time:2021/10/25 19:12
//@Author:aFun

package indi.service.impl;

import indi.domain.User;
import indi.mapper.LoginMapper;
import indi.mapper.loginMethod.*;
import indi.mapper.impl.LoginMapperImpl;
import indi.service.LoginService;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServiceImpl implements LoginService {
    LoginMapper lm = new LoginMapperImpl();

    @Override
    public User login(User user) {
        return lm.login(user.getAccount(), user.getPassword());
    }

    @Override
    public String stragyMethodForRole(User user) {
        MethodForLogin mfl = null;
        switch (user.getRoleId()) {
            case 1:
                mfl = new ManagerLogin();
                break;
            case 2:
                mfl = new OfficerLogin();
                break;
            case 3:
                mfl = new TeacherLogin();
                break;
            case 4:
                mfl = new StudentLogin();
                break;
            default:
                break;
        }
        if (mfl == null) {
            return null;
        } else {
            return mfl.roleLogin(user);
        }
    }

    @Override
    public boolean alterPassword(User user, String oldPassword) {
        // 使用MD5进行加密
        MessageDigest md5 = null;
        // 先验证旧密码是否正确
        // 先通过账号找到旧密码
        String databaseOldPassword = lm.findPasswordByAccount(user);
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            // 将传进来的旧密码跟数据库密码对比
            String finalOldPsd = base64Encoder.encode(md5.digest(oldPassword.getBytes("utf-8")));
            if (finalOldPsd.equals(databaseOldPassword)) {
                // 旧密码正确
                String psd = user.getPassword();
                String finalPsd = base64Encoder.encode(md5.digest(psd.getBytes("utf-8")));
                user.setPassword(finalPsd);
                return lm.alterPassword(user);
            } else {
                // 旧密码错误
                return false;
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}

