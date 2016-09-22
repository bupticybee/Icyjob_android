package cn.icybee.icyjob.model.bean;

/**
 * Created by huangxuefeng on 16/5/4.
 */
public class ContentPage {
    private String title;
    private String tags;
    private String jobtag;
    private String content;
    private int id;
    private String type;
    private String email;

    public ContentPage(String title, String tags, String jobtag, String content, int id, String type, String email) {
        this.title = title;
        this.tags = tags;
        this.jobtag = jobtag;
        this.content = content;
        this.id = id;
        this.type = type;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getJobtag() {
        return jobtag;
    }

    public void setJobtag(String jobtag) {
        this.jobtag = jobtag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
