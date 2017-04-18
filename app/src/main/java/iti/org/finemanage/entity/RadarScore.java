package iti.org.finemanage.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xueli on 2017/1/12.
 */

public class RadarScore {
    /**
     * depzwsname : null
     * responResult : ["理想信念","社会工作","学术科技","实践服务","体育素质","文化艺术"]
     * responState : REQ_SUCCESS
     * score2 : [0.0,0.0,0.0,0.0,0.0,0.0]
     * scorel : [0.0,0.4,0.3,0.0,0.0,0.3]
     * scorelt : [0.0,0.0,0.0,0.0,0.0,0.0]
     */

    private String depzwsname;

    @SerializedName("responResult")
    private String subjectName;

    private String responState;

    @SerializedName("score2")
    private String collegeRadarScore;

    @SerializedName("scorel")
    private String studentRadarScore;

    @SerializedName("scorelt")
    private String schooRadarScore;

    public Object getDepzwsname() {
        return depzwsname;
    }

    public void setDepzwsname(String depzwsname) {
        this.depzwsname = depzwsname;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getResponState() {
        return responState;
    }

    public void setResponState(String responState) {
        this.responState = responState;
    }

    public String getCollegeRadarScore() {
        return collegeRadarScore;
    }

    public void setCollegeRadarScore(String collegeRadarScore) {
        this.collegeRadarScore = collegeRadarScore;
    }

    public String getStudentRadarScore() {
        return studentRadarScore;
    }

    public void setStudentRadarScore(String studentRadarScore) {
        this.studentRadarScore = studentRadarScore;
    }

    public String getSchooRadarScore() {
        return schooRadarScore;
    }

    public void setSchooRadarScore(String schooRadarScore) {
        this.schooRadarScore = schooRadarScore;
    }
}
