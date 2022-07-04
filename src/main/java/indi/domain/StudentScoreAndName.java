//-*- coding = utf-8 -*-
//@Time:2021/11/2 17:10
//@Author:ZYD
//@File:StudentScoreAndName.py
//@Software: IntelliJ IDEA

package indi.domain;

public class StudentScoreAndName {
    private Integer ssId;
    private Integer stuId;
    private Integer couId;
    private Integer teaId;
    private int score;
    private String name;
    private String couName;

    public StudentScoreAndName(Integer ssId, Integer stuId, Integer couId, Integer teaId, int score, String name, String couName) {
        this.ssId = ssId;
        this.stuId = stuId;
        this.couId = couId;
        this.teaId = teaId;
        this.score = score;
        this.name = name;
        this.couName = couName;
    }

    public StudentScoreAndName() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    @Override
    public String toString() {
        return "StudentScoreAndName{" +
                "ssId=" + ssId +
                ", stuId=" + stuId +
                ", couId=" + couId +
                ", teaId=" + teaId +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", couName='" + couName + '\'' +
                '}';
    }
}
