package cn.icybee.icyjob.module.collection;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.icybee.icyjob.model.bean.TextJob;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import cn.icybee.icyjob.module.text.TextJobVH;
import cn.icybee.icyjob.R;

/**
 * A placeholder fragment containing a simple view.
 */
@RequiresPresenter(CollectionActivityFragmentPresenter.class)
public class CollectionActivityFragment extends BeamListFragment<CollectionActivityFragmentPresenter,TextJob>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
