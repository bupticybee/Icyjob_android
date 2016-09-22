package cn.icybee.icyjob.model.bean;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by huangxuefeng on 16/5/2.
 */
public class TextJobPage {
    private int allNum;
    private int allPage;
    private List<TextJob> contentlist = new ArrayList<TextJob>();
    private Map<Integer, Boolean> idmap = new HashMap<Integer, Boolean>();

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public List<TextJob> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<TextJob> contentlist) {
        this.contentlist = contentlist;
    }

    public void addId(Integer id){
        this.idmap.put(id,true);
    }

    public void clearId(){
        this.idmap = new HashMap<Integer, Boolean>();
    }

    public Map<Integer, Boolean> getIdmap() {
        return idmap;
    }

    public void setIdmap(Map<Integer, Boolean> idmap) {
        this.idmap = idmap;
    }

    public void addPage(List<TextJob> pagelist){
        this.allPage += 1;
        for (int i = 0;i < pagelist.size();i ++){
            TextJob item = pagelist.get(i);
            if(this.idmap.get(item.getId()) != null){
                continue;
            }
            this.addId(item.getId());
            this.allNum += 1;
            this.contentlist.add(item);
        }
    }

    public void clearAll(){
        this.allPage = 0;
        this.allNum = 0;
        this.clearId();
        this.contentlist.clear();
    }
}
