package cn.icybee.icyjob.model.callback;


import android.util.Log;

import com.google.gson.Gson;
import cn.icybee.icyjob.config.API;
import cn.icybee.icyjob.model.bean.TextJob;
import cn.icybee.icyjob.model.bean.TextJobPage;

import com.jude.utils.JUtils;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.ParameterizedType;
/**
 * Created by huangxuefeng on 16/5/2.
 */
public abstract class ListCallback extends LinkCallback  {
    private Boolean isRefresh;

    public ListCallback(Boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override

    public void onSuccess(String s) {
        Log.d("icyjob","insde callback Success");
        JSONArray responseObject;
        int status = 0;
        String info = "";
        TextJobPage data = new TextJobPage();
        List<TextJob> pagedata = new ArrayList<TextJob>();
        try {
            responseObject = new JSONArray(s);
            for(int i=0;i<responseObject.length();i++){
                JSONObject item = responseObject.getJSONObject(i);
                String time = item.getString("time");
                String domain = item.getString("domain");
                Integer id = item.getInt("id");
                String title = item.getString("title");
                String content = new String("");
                String url = item.getString("url");
                TextJob textJob = new TextJob(title,time,content,id,domain,url);
                pagedata.add(textJob);
            }
            data.addPage(pagedata);
            /*status = responseObject.getInt(API.KEY.STATUS);
            info = responseObject.getString(API.KEY.INFO);
            if (status == API.CODE.SUCCEED){
                Gson gson = new Gson();
                data = gson.fromJson(responseObject.getString(API.KEY.DATA), ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            }*/
        } catch (Exception e) {
            Log.d("icyjob","error when unseri" + e.toString());
            e.printStackTrace();
            JUtils.Log(e.getLocalizedMessage());
            error("数据解析错误");
            return ;
        }
        result(status, info);
        success(info,data);

        /*if (status == API.CODE.SUCCEED){
            success(info,data);
        }else{
            error(info);
        }*/

        super.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        result(-1,"网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info){}
    public abstract void success(String info,TextJobPage data);
    public void error(String errorInfo){
        JUtils.Toast(errorInfo);
    }
}
