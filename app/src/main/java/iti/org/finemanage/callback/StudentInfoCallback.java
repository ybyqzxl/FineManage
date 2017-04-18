package iti.org.finemanage.callback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import iti.org.finemanage.entity.StudentInfo;
import iti.org.finemanage.utils.LogUtil;
import okhttp3.Response;

/**
 * Created by xueli on 2017/1/11.
 */

public abstract class StudentInfoCallback extends Callback<StudentInfo> {
    @Override
    public StudentInfo parseNetworkResponse(Response response, int id) throws Exception {
        String json = response.body().string();
        LogUtil.i("MainActivity", json);
        StudentInfo studentInfo = new Gson().fromJson(json, StudentInfo.class);
        return studentInfo;
    }

}
