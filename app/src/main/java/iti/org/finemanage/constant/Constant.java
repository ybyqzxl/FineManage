package iti.org.finemanage.constant;

/**
 * Created by xueli on 2017/1/11.
 */

public class Constant {

    //角色
    public enum Role {
        student, teacher, admin, secondadmin, leader
    }

    //状态,使用枚举类型，变量名和值相同，调用方法为REQ_SUCCESS.name
    public enum HttpResponseState {
        REQ_PARAMS_ERR, REQ_UNKNOW_ERR, REQ_SYSTEM_ERR, REQ_USERPWD_ERR, REGISTER_EXISTENCE, REGISTER_NO_USER,
        REQ_USERNAME_ERR, REQ_TIMEOUT, REQ_VERIFY_ERR, REQ_VERIFY_SUCCESS, REQ_SUCCESS, REGISTER_SUCCESS,
        ALREADY_SGINED, ALREADY_APPLY;
    }

    public static final String ISAUTOLOGIN = "ISAUTOLOGIN"; //是否自动登录标记
    public static final String ISREMEMBERPWD = "ISREMEMBERPWD"; //是否记住密码
}
