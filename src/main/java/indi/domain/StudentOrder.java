//@Time:2021/10/29 12:33
//@Author:aFun

package indi.domain;

public class StudentOrder {
    private Integer ssId;
    private Integer couId;
    private String couName;
    private Integer teaId;
    private Integer stuId;
    private String stuName;
    private String gender;
    private String score;

    public StudentOrder(Integer ssId, Integer couId, String couName, Integer teaId, Integer stuId, String stuName, String gender, String score) {
        this.ssId = ssId;
        this.couId = couId;
        this.couName = couName;
        this.teaId = teaId;
        this.stuId = stuId;
        this.stuName = stuName;
        this.gender = gender;
        this.score = score;
    }

    public StudentOrder() {
    }

    public Integer getSsId() {
        return ssId;
    }

    public void setSsId(Integer ssId) {
        this.ssId = ssId;
    }

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentOrder{" +
                "ssId=" + ssId +
                ", couId=" + couId +
                ", couName='" + couName + '\'' +
                ", teaId=" + teaId +
                ", stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", gender='" + gender + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
