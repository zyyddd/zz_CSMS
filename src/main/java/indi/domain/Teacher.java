//@Time:2021/10/25 17:20
//@Author:aFun

package indi.domain;

// 教师
public class Teacher extends User {
    // 教师工号
    private Integer teaId;

    public Teacher(Integer uid, String account, String password, int roleId, String name, String gender, Integer teaId) {
        super(uid, account, password, roleId, name, gender);
        this.teaId = teaId;
    }

    public Teacher(Integer teaId) {
        this.teaId = teaId;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaId=" + teaId +
                '}';
    }
}
