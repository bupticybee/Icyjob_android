package cn.icybee.icyjob.module.filter;

/**
 * Created by huangxuefeng on 16/5/7.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.icybee.icyjob.R;
import cn.icybee.icyjob.model.JobModel;
import cn.icybee.icyjob.model.bean.Tag;

public class ChoiceListItemView extends LinearLayout implements Checkable {

    private TextView nameTxt;
    private CheckBox selectBtn;
    private Tag itemtag;

    public ChoiceListItemView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        final SharedPreferences settings = context.getSharedPreferences("filter", 0);
        final SharedPreferences.Editor editor = settings.edit();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_tag, this, true);
        nameTxt = (TextView) v.findViewById(R.id.tagtext);
        selectBtn = (CheckBox) v.findViewById(R.id.radioch);
        selectBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                editor.putBoolean(itemtag.getName(),isChecked);
                editor.commit();
                JobModel.getInstance().getAlltype(context);
            }
        });
    }

    public Tag getItemtag() {
        return itemtag;
    }

    public void setItemtag(Tag itemtag) {
        this.itemtag = itemtag;
    }

    public void setName(String text) {
        nameTxt.setText(text);
    }

    @Override
    public boolean isChecked() {
        return selectBtn.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        selectBtn.setChecked(checked);
    }

    @Override
    public void toggle() {
        selectBtn.toggle();
    }

}