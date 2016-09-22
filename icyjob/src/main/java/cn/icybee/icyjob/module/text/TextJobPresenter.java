package cn.icybee.icyjob.module.text;


import android.os.Bundle;
import android.util.Log;

import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import cn.icybee.icyjob.model.bean.TextJob;
import cn.icybee.icyjob.model.bean.TextJobPage;
import cn.icybee.icyjob.model.JobModel;
import cn.icybee.icyjob.model.callback.ListCallback;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by huangxuefeng on 16/5/2.
 */
public class TextJobPresenter  extends BeamListFragmentPresenter<TextJobFragment,TextJob> {
    int page = 1;
    private String bufferedAlltype;

    private HashMap<Integer,Boolean> iddic = new HashMap<Integer, Boolean>();

    private String getAlltype(){
        TextJobFragment tf = getView();
        int type = tf.getType();
        if(type == 1) return "intern";
        if(type == 2) return "job";
        return "";
    }

    @Override
    protected void onResume() {
        String actualAlltype = JobModel.getInstance().getAlltype(getView().getContext());
        if ( ! actualAlltype.equals(this.bufferedAlltype)){
            this.bufferedAlltype = actualAlltype;
            onRefresh();
        }
        super.onResume();
    }

    @Override
    protected void onCreate(TextJobFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        this.bufferedAlltype = JobModel.getInstance().getAlltype(getView().getContext());

        //onRefresh();
    }

    @Override
    protected void onCreateView(TextJobFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        JobModel.getInstance().getTextJob(1, new ListCallback(true) {
            @Override
            public void success(String info, TextJobPage data) {
                Log.d("icyjob","insdeSuccess");
                getAdapter().clear();
                clearIdDic();
                getAdapter().addAll(filterContent(data.getContentlist()));
                page = 2;
            }

            @Override
            public void error(String errorInfo) {
                getView().showError(new Throwable(errorInfo));
            }
        },getAlltype(),getView().requestQueue,getView().getContext());
    }

    private List<TextJob> filterContent(List<TextJob> tbs){
        List<TextJob> result = new ArrayList<TextJob>();
        for (TextJob i:tbs) {
            if(iddic.get(i.getId()) == null){
                iddic.put(i.getId(),true);
                result.add(i);
            }
        }
        return result;
    }

    private void clearIdDic(){
        this.iddic.clear();
    }

    @Override
    public void onLoadMore() {
        JobModel.getInstance().getTextJob(page, new ListCallback(false) {
            @Override
            public void success(String info, TextJobPage data) {
                getAdapter().addAll(filterContent(data.getContentlist()));
                page++;
            }

            @Override
            public void error(String errorInfo) {

                getAdapter().pauseMore();
            }
        },getAlltype(),getView().requestQueue,getView().getContext());
    }

}
