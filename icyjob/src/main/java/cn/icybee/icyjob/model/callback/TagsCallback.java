package cn.icybee.icyjob.model.callback;

import com.jude.utils.JUtils;

import cn.icybee.icyjob.model.bean.Tag;
import cn.icybee.icyjob.model.bean.TagPage;
import cn.icybee.icyjob.model.bean.TextJobPage;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by huangxuefeng on 16/5/7.
 */
public abstract class TagsCallback extends LinkCallback{
    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override

    public void onSuccess(String s) {
        try {
            JSONArray jsons = new JSONArray(s);
            ArrayList<Tag> tags = new ArrayList<Tag>();
            for(int i=0;i<jsons.length();i++){
                JSONObject item = jsons.getJSONObject(i);
                int id = item.getInt("id");
                String tag = item.getString("tag");
                if(tag.equals("")){
                    continue;
                }
                tags.add(new Tag(id,tag));
                result(0,s);

            }
            success(s,new TagPage(tags));
        }catch(Exception e){
            error(e.toString());
        }
    }
    @Override
    public void onError(String s) {
        result(-1,"网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info){}
    public abstract void success(String info,TagPage data);
    public void error(String errorInfo){
        JUtils.Toast(errorInfo);
    }
}
