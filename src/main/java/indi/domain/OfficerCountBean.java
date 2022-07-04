//@Time:2021/10/31 14:42
//@Author:aFun

package indi.domain;

public class OfficerCountBean {
    private String couName;
    private Integer countNum;
    private Double maxScore;
    private Double minScore;
    private Double avgScore;
    private Integer passNum;

    public OfficerCountBean(String couName, Integer countNum, Double maxScore, Double minScore, Double avgScore, Integer passNum) {
        this.couName = couName;
        this.countNum = countNum;
        this.maxScore = maxScore;
        this.minScore = minScore;
        this.avgScore = avgScore;
        this.passNum = passNum;
    }

    public OfficerCountBean() {
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    @Override
    public String toString() {
        return "OfficerCountBean{" +
                "couName='" + couName + '\'' +
                ", countNum=" + countNum +
                ", maxScore=" + maxScore +
                ", minScore=" + minScore +
                ", avgScore=" + avgScore +
                ", passNum=" + passNum +
                '}';
    }
}
