package iti.org.finemanage.entity;

/**
 * Created by Aleck_ on 2017/1/11.
 */

public class YearJson {
    /**
     * id : 119
     * timeStamp : 1481522524929
     * state : 0
     * year : 2017
     * semester : 0
     * yearState : 2
     */

    private String id;
    private long timeStamp;
    private String state;
    private String year;
    private String semester;       //学期
    private String yearState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYearState() {
        return yearState;
    }

    public void setYearState(String yearState) {
        this.yearState = yearState;
    }
}
