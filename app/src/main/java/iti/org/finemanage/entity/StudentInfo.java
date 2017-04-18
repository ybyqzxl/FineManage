package iti.org.finemanage.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xueli on 2017/1/11.
 */

public class StudentInfo {


    /**
     * XM : 陈南辛
     * name : 理学院
     * responResult :
     * responState : REQ_SUCCESS
     * userName : 150001
     */
    @SerializedName("XM")
    private String userName;

    @SerializedName("name")
    private String userCollege;

    private String responResult;

    private String responState;

    @SerializedName("userName")
    private String userCode;

    public String getUserCollege() {
        return userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getResponResult() {
        return responResult;
    }

    public void setResponResult(String responResult) {
        this.responResult = responResult;
    }

    public String getResponState() {
        return responState;
    }

    public void setResponState(String responState) {
        this.responState = responState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
