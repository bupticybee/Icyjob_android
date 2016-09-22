package cn.icybee.icyjob.module.filter;

/**
 * Created by huangxuefeng on 16/5/7.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cn.icybee.icyjob.model.bean.TagPage;

public class RadioAdapter extends BaseAdapter {

    private TagPage authors;
    private Context c;

    public TagPage getAuthors() {
        return authors;
    }

    public RadioAdapter(Context c, TagPage authors) {
        super();
        this.c = c;
        this.authors = authors;
    }

    @Override
    public int getCount() {
        return authors.getTagpage().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        //返回每一条Item的Id
        return arg0;
    }

    @Override
    public boolean hasStableIds() {
        //getCheckedItemIds()方法要求此处返回为真
        return true;
    }
    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        ChoiceListItemView choiceListItemView = new ChoiceListItemView(c, null);
        choiceListItemView.setName(authors.getTagpage().get(arg0).getName());
        choiceListItemView.setItemtag(authors.getTagpage().get(arg0));
        return choiceListItemView;
    }

}