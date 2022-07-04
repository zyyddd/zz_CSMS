//@Time:2021/10/25 17:15
//@Author:aFun

package indi.domain;

// 用户
public class User {
    private Integer uid;
    private String account;
    private String password;
    private int roleId;                 // 标志位，不需要设置为包装类
    private String name;
    private String gender;


    public User(Integer uid, String account, String password, int roleId, String name, String gender) {
        this.uid = uid;
        this.account = account;
        this.password = password;
        this.roleId = roleId;
        this.name = name;
        this.gender = gender;
    }

    public User() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

