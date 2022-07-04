//@Time:2021/10/25 17:17
//@Author:aFun

package indi.domain;

// 学生
public class Student extends User {
    // 学生学号
    private Integer stuId;

    public Student(Integer uid, String account, String password, int roleId, String name, String gender, Integer stuId) {
        super(uid, account, password, roleId, name, gender);
        this.stuId = stuId;
    }

    public Student(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                '}';
    }
}
