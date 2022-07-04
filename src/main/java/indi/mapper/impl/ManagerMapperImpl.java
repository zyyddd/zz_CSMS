//@Time:2021/10/25 18:46
//@Author:aFun

package indi.mapper.impl;

import indi.domain.*;
import indi.mapper.LoginMapper;
import indi.mapper.ManagerMapper;
import indi.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class ManagerMapperImpl implements ManagerMapper {

    // 这个实现类中需要实现对事务的控制！！！
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    // 用这个方法判断要操作哪个表
    public String judgeTable(User user) {
        String tableName = "";
        int roleId = user.getRoleId();
        if (roleId == 2) {
            tableName = "officer";
        } else if (roleId == 3) {
            tableName = "teacher";
        } else if (roleId == 4) {
            tableName = "student";
        }
        return tableName;
    }



    // 原生JDBC操作！
    @Override
    public boolean add(User user) {
        Connection conn = null;
        PreparedStatement pstm = null;
        PreparedStatement pstm2 = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            // 先添加用户
            String sql = "insert into user values(?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setObject(1, user.getUid());
            pstm.setString(2, user.getAccount());

            //===========================================================================
            // 使用MD5进行加密
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            // 密码可以为空，默认为123456
            String psd = user.getPassword().equals("") ? "123456" : user.getPassword();
            String finalPsd = base64Encoder.encode(md5.digest(psd.getBytes("utf-8")));
            pstm.setString(3, finalPsd);
            //===========================================================================

//            // 密码可以为空，默认为123456
//            String psd = user.getPassword().equals("") ? "123456" : user.getPassword();
//            pstm.setString(3, psd);
            pstm.setInt(4, user.getRoleId());
            pstm.setString(5, user.getName());
            pstm.setString(6, user.getGender());
            int i = pstm.executeUpdate();

            // 再在相应角色的表中添加数据
            String sql2 = "";
            String tableName = judgeTable(user);
            sql2 = "insert into " + tableName + " values(null,?)";              // 插入的学号/工号由默认系统给出即可
            pstm2 = conn.prepareStatement(sql2);
            pstm2.setString(1, user.getAccount());
            int j = pstm2.executeUpdate();


            // 其中一个更新失败，事务需要回滚
            if (i <= 0 || j <= 0) {
                System.out.println("更新失败！！！");
                conn.rollback();
                return false;
            } else {
                conn.commit();
                return true;
            }


        } catch (SQLException throwables) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstm, conn);
        }
        return false;
    }

    @Override
    public List<Object> findAll() {


        String sql = "select * from user u,student s where u.account=s.account";
        String sql1 = "select * from user u,teacher t where u.account=t.account";
        String sql2 = "select * from user u,officer o where u.account=o.account";

        List<Map<String, Object>> students = template.queryForList(sql);
        List<Map<String, Object>> teachers = template.queryForList(sql1);
        List<Map<String, Object>> officers = template.queryForList(sql2);

//        System.out.println(students);
//        System.out.println(teachers);
//        System.out.println(officers);

        List<Object> result = new ArrayList<>(Arrays.asList(students, teachers, officers));
        return result;
    }

    @Override
    public boolean update(User user) {
        String sql = "update user set account=?,password=?,roleId=?,name=?,gender=? where uid=?";
        int update = template.update(sql, user.getAccount(), user.getPassword(), user.getRoleId(),
                user.getName(), user.getGender(), user.getUid());
        return update > 0 ? true : false;
    }

    @Override
    public boolean delete(Integer uid) {
        String sql = "delete from user where uid=?";
        int update = template.update(sql, uid);
        return update > 0 ? true : false;
    }

    @Override
    public User findUserById(Integer uid) {
        String sql = "select * from user where uid=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
    }


    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
//        System.out.println(sb.toString());
//        System.out.println(params);
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }

        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }
}
