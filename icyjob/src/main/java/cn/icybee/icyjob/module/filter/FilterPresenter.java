package cn.icybee.icyjob.module.filter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.jude.beam.bijection.Presenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.icybee.icyjob.R;
import cn.icybee.icyjob.model.JobModel;
import cn.icybee.icyjob.model.bean.Tag;
import cn.icybee.icyjob.model.bean.TagPage;
import cn.icybee.icyjob.model.callback.TagsCallback;

/**
 * Created by huangxuefeng on 16/5/7.
 */
public class FilterPresenter extends Presenter<FilterActivity> {


    @Override
    protected void onCreate(FilterActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    protected void onRefresh(){
        final SharedPreferences settings = getView().getSharedPreferences("filter", 0);
        final SharedPreferences.Editor editor = settings.edit();
        JobModel.getInstance().getTags(new TagsCallback() {
            @Override
            public void success(String info, TagPage data) {
                JobModel.getInstance().setFilterlist(data.getTagpage(),getView());
                RadioAdapter ra = new RadioAdapter(getView(),data);
                getView().tagview.setAdapter(ra);
                for (int i = 0; i < ra.getCount(); i++) {
                    Tag t = ra.getAuthors().getTagpage().get(i);
                    boolean ischecked = settings.getBoolean(t.getName(),false);
                    getView().tagview.setItemChecked(i, ischecked);
                }
            }

            @Override
            public void error(String errorInfo) {
                super.error(errorInfo);
            }
        },getView().requestQueue);
    }

}

