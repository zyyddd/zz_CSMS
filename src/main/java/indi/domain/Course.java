//@Time:2021/10/26 14:38
//@Author:aFun

package indi.domain;

public class Course {
    private Integer couId;
    private String couName;
    private int couHour;            // 这里不用转
    private String couDes;
    private int judge;              // 这里只是个标志位，不用转成Integer,judge=1表示审核通过,judge=2表示审核中,judge=3表示审核不通过
    private int isopen;             // 开课标志，1为已开课，2为未开课
    private int limitNum;           // 限选人数

    public Course(Integer couId, String couName, int couHour, String couDes, int judge, int isopen, int limitNum) {
        this.couId = couId;
        this.couName = couName;
        this.couHour = couHour;
        this.couDes = couDes;
        this.judge = judge;
        this.isopen = isopen;
        this.limitNum = limitNum;
    }

    public Course() {
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

    public int getCouHour() {
        return couHour;
    }

    public void setCouHour(int couHour) {
        this.couHour = couHour;
    }

    public String getCouDes() {
        return couDes;
    }

    public void setCouDes(String couDes) {
        this.couDes = couDes;
    }

    public int getJudge() {
        return judge;
    }

    public void setJudge(int judge) {
        this.judge = judge;
    }

    public int getIsopen() {
        return isopen;
    }

    public void setIsopen(int isopen) {
        this.isopen = isopen;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    @Override
    public String toString() {
        return "Course{" +
                "couId=" + couId +
                ", couName='" + couName + '\'' +
                ", couHour=" + couHour +
                ", couDes='" + couDes + '\'' +
                ", judge=" + judge +
                ", isopen=" + isopen +
                ", limitNum=" + limitNum +
                '}';
    }
}

