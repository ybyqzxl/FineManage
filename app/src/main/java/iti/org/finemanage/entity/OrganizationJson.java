package iti.org.finemanage.entity;

/**
 * Created by Aleck_ on 2017/1/11.
 */

public class OrganizationJson {
    /**
     * caName : 社会工作        //组织类别
     * raName : 院级            //组织级别
     * orName : 理学院_理学院舞蹈队      //组织名称
     * orPost : 队员              //职务
     * orScore : 0.4                //分数
     *
     * {\"caName\":\"体育素质\",\"raName\":\"校级\",\"orName\":\"河北工业大学_瑜伽协会（北辰）\",
     * "orPost\":\"部员\",\"orScore\":0.5},
     */

    private String caName;
    private String raName;
    private String orName;
    private String orPost;
    private double orScore;

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

    public String getOrName() {
        return orName;
    }

    public void setOrName(String orName) {
        this.orName = orName;
    }

    public String getOrPost() {
        return orPost;
    }

    public void setOrPost(String orPost) {
        this.orPost = orPost;
    }

    public double getOrScore() {
        return orScore;
    }

    public void setOrScore(double orScore) {
        this.orScore = orScore;
    }
}
