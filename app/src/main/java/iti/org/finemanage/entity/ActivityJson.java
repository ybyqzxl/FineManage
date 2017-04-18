package iti.org.finemanage.entity;

/**
 * Created by Aleck_ on 2017/1/12.
 */

public class ActivityJson {
    //caName\":\"文化艺术\",\"raName\":\"校级\",\"acName\":
    // \"河北工业大学_河工春晚\",\"acRole\":\"观众\",\"acScore\":0.1,\"acRemark\":\"undefined\"
    private String caName;
    private String raName;
    private String acName;
    private String acRole;
    private String acScore;
    private String acRemark;

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcRemark() {
        return acRemark;
    }

    public void setAcRemark(String acRemark) {
        this.acRemark = acRemark;
    }

    public String getAcRole() {
        return acRole;
    }

    public void setAcRole(String acRole) {
        this.acRole = acRole;
    }

    public String getAcScore() {
        return acScore;
    }

    public void setAcScore(String acScore) {
        this.acScore = acScore;
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
