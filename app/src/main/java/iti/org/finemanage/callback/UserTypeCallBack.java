package iti.org.finemanage.callback;

import android.text.TextUtils;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by xueli on 2017/1/10.
 */

public abstract class UserTypeCallBack extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        //url为登录成功跳转之后的网页链接，用来判断角色：例如：http://sc.hebut.edu.cn/FineManagement/student/index.html
        String url = response.request().url().toString();
        if (!TextUtils.isEmpty(url)) {
            int index = url.lastIndexOf("/", url.length());
            String type_url = url.substring(0, index);
            int index_two = type_url.lastIndexOf("/", type_url.length());
            String type = url.substring(index_two + 1, index);
            return type;        //type为角色对象里面的student
        }
        return "-1";
    }
}
