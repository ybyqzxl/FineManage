package iti.org.finemanage.callback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import iti.org.finemanage.entity.BaseJson;
import okhttp3.Response;

/**
 * Created by Aleck_ on 2017/1/11.
 */

public abstract class YearCallBack extends Callback<BaseJson> {
    @Override
    public BaseJson parseNetworkResponse(Response response, int id) throws Exception {
        String str = response.body().string();
        BaseJson baseJson = new Gson().fromJson(str, BaseJson.class);
        return baseJson;
    }
}
