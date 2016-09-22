package cn.icybee.icyjob.model.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by huangxuefeng on 16/5/11.
 */
@Table(name = "Collection")
public class Collection extends Model{

    @Column(name = "collectionid",index = true,unique = true)
    public long collectionid;

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "time",index = true)
    public String time;

    @Column(name = "domain")
    public String domain;

    @Column(name = "url")
    public String url;

    public Collection() {
        super();
    }

    public Collection(long collectionid, String title, String content, String time, String domain, String url) {
        super();
        this.collectionid = collectionid;
        this.title = title;
        this.content = content;
        this.time = time;
        this.domain = domain;
        this.url = url;
    }
    public List<Collection> queryAll(){
        return new Select().all().from(Collection.class).orderBy("Id desc").execute();
    }
}
