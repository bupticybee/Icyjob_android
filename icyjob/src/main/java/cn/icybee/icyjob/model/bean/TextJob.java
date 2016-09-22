package cn.icybee.icyjob.model.bean;

/**
 * Created by huangxuefeng on 16/5/2.
 */
public class TextJob {
    private String title;
    private String time;
    private String content;
    private Integer id;
    private String domain;
    private String url;

    public TextJob(String title, String time, String content, Integer id, String domain, String url) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.id = id;
        this.domain = domain;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
