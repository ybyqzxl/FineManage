package iti.org.finemanage.callback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import iti.org.finemanage.entity.RadarScore;
import iti.org.finemanage.utils.LogUtil;
import okhttp3.Response;

/**
 * Created by xueli on 2017/1/12.
 */

public abstract class RadarScoreCallback extends Callback<RadarScore> {

    @Override
    public RadarScore parseNetworkResponse(Response response, int id) throws Exception {
        String json = response.body().string();
        LogUtil.i("MainActivity", json);
        RadarScore radarScore = new Gson().fromJson(json, RadarScore.class);
        return radarScore;
    }
}
