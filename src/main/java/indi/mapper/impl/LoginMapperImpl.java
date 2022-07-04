//@Time:2021/10/25 18:52
//@Author:aFun

package indi.mapper.impl;

import indi.domain.User;
import indi.mapper.LoginMapper;
import indi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

public class LoginMapperImpl implements LoginMapper {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User login(String account, String password) {
        try {
            // 使用MD5进行解密
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String finalPassword = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));

            String sql = "select * from user where account=? and password=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), account, finalPassword);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String findPasswordByAccount(User user) {
        String sql = "SELECT PASSWORD FROM USER WHERE ACCOUNT=?";
        String s = template.queryForObject(sql, String.class, user.getAccount());
        return s;
    }

    @Override
    public boolean alterPassword(User user) {
        String sql = "UPDATE USER SET PASSWORD=? WHERE account=?;";
        int update = template.update(sql, user.getPassword(), user.getAccount());
        return update > 0 ? true : false;
    }

}
