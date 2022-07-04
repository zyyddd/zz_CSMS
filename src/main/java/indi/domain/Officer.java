//@Time:2021/10/25 17:19
//@Author:aFun

package indi.domain;

// 教务处管理员
public class Officer extends User {
    // 教务处管理员工号
    private Integer offId;

    public Officer(Integer uid, String account, String password, int roleId, String name, String gender, Integer offId) {
        super(uid, account, password, roleId, name, gender);
        this.offId = offId;
    }

    public Officer(Integer offId) {
        this.offId = offId;
    }

    public Integer getOffId() {
        return offId;
    }

    public void setOffId(Integer offId) {
        this.offId = offId;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "offId=" + offId +
                '}';
    }
}
