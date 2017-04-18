package iti.org.finemanage.callback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import iti.org.finemanage.entity.StudentScore;
import okhttp3.Response;

/**
 * Created by xueli on 2017/1/11.
 */

public abstract class StudentScoreCallback extends Callback<StudentScore> {

    @Override
    public StudentScore parseNetworkResponse(Response response, int id) throws Exception {
        String json = response.body().string();
        StudentScore score = new Gson().fromJson(json, StudentScore.class);
        return score;
    }
}
