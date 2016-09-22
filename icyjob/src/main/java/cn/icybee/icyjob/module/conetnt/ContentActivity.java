package cn.icybee.icyjob.module.conetnt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.jude.beam.expansion.BeamBaseActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.jude.beam.bijection.RequiresPresenter;
import cn.icybee.icyjob.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.icybee.icyjob.model.database.Collection;

import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

@RequiresPresenter(ContentPresenter.class)
public class ContentActivity extends BeamBaseActivity<ContentPresenter>{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.titletext)
    TextView titleview;
    @Bind(R.id.favorite_nice)
    MaterialFavoriteButton favoriteButton;

    private Integer articleid;
    private String time;
    private String title;
    private String domain;
    private String url;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        this.articleid = bundle.getInt("articleid");
        this.time = bundle.getString("time");
        this.title = bundle.getString("title");
        this.domain = bundle.getString("domain");
        this.url = bundle.getString("url");
        this.email = "";

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true)；
        getSupportActionBar().setTitle("查看内容");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                if (email.equals("")){
                    Snackbar.make(view, "无法抽取有效email", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email));
                intent.putExtra(Intent.EXTRA_SUBJECT, "XX希望应聘YY岗位");
                intent.putExtra(Intent.EXTRA_TEXT, "你好，\n\t在XX看到了你们发布的招聘信息，希望应聘，简历见附件。\n祝好");
                startActivity(intent);
            }
        });

        // check whether the collection has already exists
        List<Collection> collections = new Select().from(Collection.class).where("collectionid = ?",articleid).execute();
        boolean favour = false;
        if (collections.size() > 0){
            favour = true;
        }
        favoriteButton.setFavorite(favour);

        // set the favour action
        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener(){
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                if(favorite){
                    Collection collection = new Collection(articleid,title,"",time,domain,url);
                    collection.save();
                }else{
                    new Delete().from(Collection.class).where("collectionid = ?",articleid).execute();
                }
            }
        });

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
        Log.d("icycloud",String.valueOf(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }
}
