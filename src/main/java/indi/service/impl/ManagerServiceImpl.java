//@Time:2021/10/25 19:04
//@Author:aFun

package indi.service.impl;

import indi.domain.Manager;
import indi.domain.PageBean;
import indi.domain.User;
import indi.mapper.ManagerMapper;
import indi.mapper.impl.ManagerMapperImpl;
import indi.service.ManagerService;

import java.util.List;
import java.util.Map;

public class ManagerServiceImpl implements ManagerService {
    private ManagerMapper mm = new ManagerMapperImpl();

    @Override
    public boolean add(User user) {
        return mm.add(user);
    }

    @Override
    public List<Object> findAll() {
        return mm.findAll();
    }

    @Override
    public boolean update(User user) {
        // try to update uid and roleid should be illegle !!!
        return mm.update(user);
    }

    @Override
    public boolean delete(Integer uid) {
        return mm.delete(uid);
    }

    @Override
    public User findUserById(Integer uid) {
        return mm.findUserById(uid);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                mm.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<User> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int totalCount = mm.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<User> list = mm.findByPage(start, rows, condition);
        pb.setList(list);

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }


}
