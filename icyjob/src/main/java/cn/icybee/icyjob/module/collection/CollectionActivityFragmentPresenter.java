package cn.icybee.icyjob.module.collection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.activeandroid.query.Select;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import cn.icybee.icyjob.model.bean.TextJob;
import cn.icybee.icyjob.model.database.Collection;

/**
 * Created by huangxuefeng on 16/5/12.
 */
public class CollectionActivityFragmentPresenter extends BeamListFragmentPresenter<CollectionActivityFragment,TextJob>{
    private int page = 1;

    @Override
    protected void onCreate(CollectionActivityFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getNew();
        }
    };

    private void getNew(){
        List<Collection> collections = new Select().from(Collection.class).orderBy("Id desc").offset((page - 1) * 10).limit(10).execute();
        List<TextJob> jobs = new ArrayList<TextJob>();
        for(Collection i: collections){
            String title = i.title;
            String time = i.time;
            String content = i.content;
            int id = (int)i.collectionid;
            String domain = i.domain;
            String url = i.url;
            TextJob textJob = new TextJob(title,time,content,id,domain,url);
            jobs.add(textJob);
        }
        Log.d("icyjob","inserted:" + String.valueOf(jobs.size()));
        int datasize = jobs.size();
        if(datasize != 0) {
            getAdapter().addAll(jobs);
            page += 1;
        }else{
            getAdapter().stopMore();
        }
    }

    @Override
    public void onRefresh() {
        this.page = 1;
        getAdapter().clear();
        getNew();
        super.onRefresh();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        //getNew();
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessage(message);
    }

    @Override
    protected void onCreateView(CollectionActivityFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

}
