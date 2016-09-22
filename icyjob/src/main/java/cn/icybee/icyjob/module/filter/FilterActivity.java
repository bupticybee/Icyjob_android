package cn.icybee.icyjob.module.filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.icybee.icyjob.R;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jude.beam.expansion.BeamBaseActivity;

import android.view.MenuItem;
import android.widget.ListView;

import com.jude.beam.bijection.RequiresPresenter;
import com.umeng.analytics.MobclickAgent;


@RequiresPresenter(FilterPresenter.class)
public class FilterActivity extends BeamBaseActivity<FilterPresenter> {
    RequestQueue requestQueue;
    @Bind(R.id.taglist)
    ListView tagview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true)；
        getSupportActionBar().setTitle("过滤器（多选）");

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        //return super.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
