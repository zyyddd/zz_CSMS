//@Time:2021/10/25 18:26
//@Author:aFun

package indi.domain;

// 系统管理员 这个实体类感觉不用写，没什么多大意义
public class Manager extends User {
    public Manager(Integer uid, String account, String password, int roleId, String name, String gender) {
        super(uid, account, password, roleId, name, gender);
    }

    public Manager() {
    }

    @Override
    public String toString() {
        return "Manager{}";
    }
}
