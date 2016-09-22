package cn.icybee.icyjob.module.text;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import cn.icybee.icyjob.model.JobModel;
import cn.icybee.icyjob.model.bean.TextJob;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

/**
 * Created by huangxuefeng on 16/5/2.
 */

@RequiresPresenter(TextJobPresenter.class)
public class TextJobFragment extends BeamListFragment<TextJobPresenter,TextJob> {
    RequestQueue requestQueue;

    private int type = 0;
    public int getType() {
        return type;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(getContext());
        super.onCreate(savedInstanceState);
    }


    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new TextJobVH(viewGroup);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setLoadmoreAble(true)
                .setRefreshAble(true)
                .setNoMoreAble(true)
                .setNoMoreAble(true)
                .setErrorAble(true)
                .setErrorTouchToResumeAble(true);
    }
}