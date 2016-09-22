package cn.icybee.icyjob.module.text;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import cn.icybee.icyjob.R;
import cn.icybee.icyjob.model.bean.TextJob;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.icybee.icyjob.module.conetnt.ContentActivity;
import android.os.Bundle;

import android.content.Intent;

import org.w3c.dom.Text;

/**
 * Created by huangxuefeng on 16/5/2.
 */
public class TextJobVH extends BaseViewHolder<TextJob>{

    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.title)
    TextView title;

    private TextJob textJob;

    public TextJobVH(final ViewGroup parent) {
        super(parent, R.layout.item_job_text);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(parent.getContext(),ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("articleid",textJob.getId());
                bundle.putString("time",textJob.getTime());
                bundle.putString("title",textJob.getTitle());
                bundle.putString("domain",textJob.getDomain());
                bundle.putString("url",textJob.getUrl());
                i.putExtras(bundle);
                parent.getContext().startActivity(i);
            }
        });
    }

    @Override
    public void setData(TextJob data) {
        content.setText(data.getContent());
        time.setText(data.getTime());
        title.setText(data.getTitle());
        this.textJob = data;
    }

}
