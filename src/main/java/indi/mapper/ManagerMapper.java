//@Time:2021/10/25 18:44
//@Author:aFun

package indi.mapper;

import indi.domain.Manager;
import indi.domain.User;

import java.util.List;
import java.util.Map;

public interface ManagerMapper {

    // 进行CRUD操作，通过控制roleID可控制对哪个实体进行操作
    public boolean add(User user);

    public List<Object> findAll();

    public boolean update(User user);

    public boolean delete(Integer uid);

    public User findUserById(Integer uid);

    // 分页相关的查询
    public int findTotalCount(Map<String, String[]> condition);

    public List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
