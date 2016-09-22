package cn.icybee.icyjob.model.bean;

import java.util.List;

/**
 * Created by huangxuefeng on 16/5/7.
 */
public class TagPage {
    private List<Tag>  tagpage;

    public TagPage(List<Tag> tagpage) {
        this.tagpage = tagpage;
    }

    public List<Tag> getTagpage() {
        return tagpage;
    }

    public void setTagpage(List<Tag> tagpage) {
        this.tagpage = tagpage;
    }
}
