package cn.icybee.icyjob.model;


import android.content.Context;
import android.content.SharedPreferences;

import com.jude.beam.model.AbsModel;
import com.jude.http.RequestManager;
import cn.icybee.icyjob.config.API;
import cn.icybee.icyjob.model.bean.Tag;
import cn.icybee.icyjob.model.callback.ContentCallback;
import cn.icybee.icyjob.model.callback.ListCallback;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.icybee.icyjob.model.callback.TagsCallback;
import cn.icybee.icyjob.module.conetnt.ContentActivity;

/**
 * Created by huangxuefeng on 16/5/2.
 */
public class JobModel extends AbsModel{
    public static JobModel getInstance() {
        return getInstance(JobModel.class);
    }
    private List<Tag> filterlist;
    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        //HashMap<String ,String > header = new HashMap<>();
        //header.put("apikey"," c676a989ffe83f89db5265482ef3222d");
        //RequestManager.getInstance().setHeader(header);
    }

    public void getTextJob(int page , final ListCallback callback, String alltype, RequestQueue queue,Context context){
        //RequestManager.getInstance().post(API.URL.JOB_LIST, new RequestMap("page", page + ""), callback);
        //RequestManager.getInstance().get(API.URL.JOB_LIST, new RequestMap("page", page + ""), callback);

        //RequestManager.getInstance().get(API.URL.JOB_LIST + "?offset=" + String.valueOf((page - 1) * 10) + "&alltype=" + alltype, callback);
        String requesturl = API.URL.JOB_LIST + "?offset=" + String.valueOf((page - 1) * 10) + "&alltype=" + alltype + getAlltype(context);
        Request stringRequest=new StringRequest(Request.Method.GET, requesturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.error(error.toString());
            }
        });
        queue.add(stringRequest);

    }

    public List<Tag> getFilterlist() {
        return filterlist;
    }

    public String getAlltype(Context context){
        final SharedPreferences settings = context.getSharedPreferences("filter", 0);
        final SharedPreferences.Editor editor = settings.edit();
        String result = "";
        if(filterlist != null) {
            for (Tag t : filterlist) {
                if (settings.getBoolean(t.getName(), false)) {
                    result += String.valueOf("," + t.getId());
                }
            }
            editor.putString("alltypebuffer",result);
            editor.commit();
        }else{
            result = settings.getString("alltypebuffer","");
        }

        return result;
    }

    public void setFilterlist(List<Tag> filterlist, Context context) {
        final SharedPreferences settings = context.getSharedPreferences("filter", 0);
        final SharedPreferences.Editor editor = settings.edit();
        this.filterlist = filterlist;
        String taginfo = "";
        for (Tag t : filterlist) {
            if (settings.getBoolean(t.getName(), false)) {
                taginfo += String.valueOf("," + t.getId());
            }
        }
        editor.putString("alltypebuffer",taginfo);
        editor.commit();
    }

    public void getContentJob(int articleid, ContentCallback callback){
        RequestManager.getInstance().get(API.URL.JOB_CONTENT + "?id=" + String.valueOf(articleid), callback);
    }

    public void getTags(final TagsCallback callback,RequestQueue queue){
        RequestManager.getInstance().get(API.URL.JOB_TAGS , callback);

        Request stringRequest=new StringRequest(Request.Method.GET, API.URL.JOB_TAGS , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.error(error.toString());
            }
        });
        queue.add(stringRequest);
    }

    public void getCollectionJob(){

    }
}
