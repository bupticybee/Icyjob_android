package cn.icybee.icyjob.module.conetnt;
import com.jude.beam.bijection.Presenter;
import android.os.Bundle;
import android.util.Log;

import cn.icybee.icyjob.model.JobModel;
import cn.icybee.icyjob.model.bean.ContentPage;
import cn.icybee.icyjob.model.callback.ContentCallback;
import cn.icybee.icyjob.R;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by huangxuefeng on 16/5/4.
 */
public class ContentPresenter extends Presenter<ContentActivity> {
    private int articleid;
    @Override
    protected void onCreate(ContentActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        //onRefresh();
        Bundle bundle = getView().getIntent().getExtras();
        int articleid = bundle.getInt("articleid");
        this.articleid = articleid;
        Log.d("icyjob","inside ContentPresenter");
    }

    @Override
    protected void onCreateView(ContentActivity view) {
        Log.d("icyjob","inside Content view");
        super.onCreateView(view);
        JobModel.getInstance().getContentJob(this.articleid, new ContentCallback() {
            @Override
            public void success(String info, ContentPage data) {
                TextView tv = (TextView)getView().findViewById(R.id.contenttext);
                tv.setText(data.getContent() + "\n\n\n");
                //getView().setTitle(data.getTitle());
                getView().setEmail(data.getEmail());
                getView().titleview.setText(data.getTitle());
            }

            @Override
            public void error(String errorInfo) {
                Log.d("icyjob",errorInfo);
            }
        });
    }
}
