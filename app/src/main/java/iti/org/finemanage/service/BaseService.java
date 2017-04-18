package iti.org.finemanage.service;

/**
 * Created by xueli on 2017/1/11.
 */

public class BaseService {

    private static String HEAD = "http://sc.hebut.edu.cn/FineManagement/";


    //Student action
    //学生信息
    public static String SECOND_STUDENT_INFO = HEAD + "ajax/LoadSecondStudentAction.action";
    //学生组织
    public static String STUDENT_ORGANIZATION = HEAD + "ajax/LoadStudentOrganizationAction.action";
    //校园文化
    public static String STUDENT_ACTIVITY = HEAD + "ajax/LoadStudentActivityAction.action";
    //获奖情况
    public static String STUDENT_AWARD = HEAD + "ajax/LoadStudentAwardAction.action";
    //统计六项分数
    public static String STUDENT_EVERY_SCORE = HEAD + "ajax/InitStudentEveryScore.action";
    //带获取年份
    public static String INIT_YEAR = HEAD + "ajax/InitYear.action";
    //雷达图分数
    public static String STUDNET_RADAR_SCORE = HEAD + "ajax/GetScoreAndDept!gainStuRadarScore.action";

}
