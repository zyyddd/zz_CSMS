//@Time:2021/10/26 14:42
//@Author:aFun

package indi.domain;

public class StudentScore {
    private Integer ssId;
    private Integer stuId;
    private Integer couId;
    private Integer teaId;
    private int score;

    public StudentScore(Integer ssId, Integer stuId, Integer couId, Integer teaId, int score) {
        this.ssId = ssId;
        this.stuId = stuId;
        this.couId = couId;
        this.teaId = teaId;
        this.score = score;
    }

    public StudentScore() {
    }

    public Integer getSsId() {
        return ssId;
    }

    public void setSsId(Integer ssId) {
        this.ssId = ssId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "ssId=" + ssId +
                ", stuId=" + stuId +
                ", couId=" + couId +
                ", teaId=" + teaId +
                ", score=" + score +
                '}';
    }
}
