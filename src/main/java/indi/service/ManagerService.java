//@Time:2021/10/25 19:04
//@Author:aFun

package indi.service;

import indi.domain.Manager;
import indi.domain.PageBean;
import indi.domain.User;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    public boolean add(User user);

    public List<Object> findAll();

    public boolean update(User user);

    public boolean delete(Integer uid);

    public User findUserById(Integer uid);

    void delSelectedUser(String[] ids);

    // 分页查找全部用户
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);
}
