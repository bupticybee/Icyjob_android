package cn.icybee.icyjob.model.callback;

import com.jude.utils.JUtils;

import cn.icybee.icyjob.model.bean.ContentPage;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Created by huangxuefeng on 16/5/4.
 */
public abstract class ContentCallback extends LinkCallback {
    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override

    public void onSuccess(String s) {
        int status = 0;
        String info = "";
        ContentPage retval = null;
        try {
            JSONObject item = new JSONObject(s);
            String title = item.getString("title");
            String tags = item.getString("tags");
            String jobtag = item.getString("jobtag");
            String content = item.getString("content");
            int id = item.getInt("id");
            String type = item.getString("type");
            String email = item.getString("email");
            retval = new ContentPage(title,tags,jobtag,content,id,type,email);
        }catch (Exception e) {
            error("数据解析错误");
        }
        result(status, info);
        success(info,retval);
    }

    @Override
    public void onError(String s) {
        result(-1,"网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info){}
    public abstract void success(String info,ContentPage data);
    public void error(String errorInfo){
        JUtils.Toast(errorInfo);
    }
}
