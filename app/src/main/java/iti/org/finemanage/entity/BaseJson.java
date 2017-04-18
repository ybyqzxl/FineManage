package iti.org.finemanage.entity;

/**
 * Created by Aleck_ on 2017/1/11.
 */

public class BaseJson {
    /**
     * responResult : [{"id":119,"timeStamp":1481522524929,"state":0,"year":2017,"semester":0,"yearState":2},{"id":84,"timeStamp":1474274106518,"historyId":89,"state":0,"year":2016,"semester":0,"yearState":0},{"id":107,"timeStamp":1483442713061,"historyId":123,"state":0,"year":2016,"semester":1,"yearState":1},{"id":77,"timeStamp":1475983680728,"historyId":117,"state":0,"year":2015,"semester":0,"yearState":0},{"id":81,"timeStamp":1456544261349,"historyId":85,"state":0,"year":2015,"semester":1,"yearState":0},{"id":37,"timeStamp":1426582113364,"historyId":78,"state":0,"year":2014,"semester":0,"yearState":0},{"id":76,"timeStamp":1475045489751,"historyId":113,"state":0,"year":2014,"semester":1,"yearState":0},{"id":35,"timeStamp":1416830153671,"historyId":65,"state":0,"year":2013,"semester":1,"yearState":0}]
     * responState : REQ_SUCCESS
     */

    private String responResult;
    private String responState;

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
