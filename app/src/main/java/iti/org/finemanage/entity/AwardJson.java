package iti.org.finemanage.entity;

/**
 * Created by Aleck_ on 2017/1/12.
 */

public class AwardJson {

    //{\"caName\":\"文化艺术\",\"raName\":\"院级\",\"
    // awName\":\"材料科学与工程学院_团体-材料学院16级新生辩论赛\",\"awRank\":\"二等奖\",\"awScore\":0.3}
    private String caName;
    private String raName;
    private String awName;
    private String awRank;
    private String awScore;

    public String getAwName() {
        return awName;
    }

    public void setAwName(String awName) {
        this.awName = awName;
    }

    public String getAwRank() {
        return awRank;
    }

    public void setAwRank(String awRank) {
        this.awRank = awRank;
    }

    public String getAwScore() {
        return awScore;
    }

    public void setAwScore(String awScore) {
        this.awScore = awScore;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public String getRaName() {
        return raName;
    }

    public void setRaName(String raName) {
        this.raName = raName;
    }
}
