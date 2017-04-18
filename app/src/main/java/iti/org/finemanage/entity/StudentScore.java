package iti.org.finemanage.entity;

/**
 * Created by xueli on 2017/1/11.
 */

public class StudentScore {


    /**
     * iTotalDisplayRecords : null
     * iTotalRecords : null
     * responResult : [{"catename":"理想信念","deporder":1,"claorder":1,"score":0.0},{"catename":"社会工作","deporder":1,
     * "claorder":1,"score":0.4},{"catename":"学术科技","deporder":122,"claorder":1,"score":0.3},{"catename":"实践服务",
     * "deporder":29,"claorder":1,"score":0.0},{"catename":"体育素质","deporder":1,"claorder":1,"score":0.0},
     * {"catename":"文化艺术","deporder":64,"claorder":13,"score":0.3}]
     * responState : REQ_SUCCESS
     */

    private String iTotalDisplayRecords;
    private String iTotalRecords;
    private String responResult;
    private String responState;


    public String getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(String iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
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
}
