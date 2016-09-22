package cn.icybee.icyjob.model.callback;
import com.jude.http.RequestListener;
/**
 * Created by huangxuefeng on 16/5/2.
 */
class LinkCallback implements RequestListener {
    private LinkCallback link;
    public LinkCallback add(LinkCallback other){
        other.setLink(this);
        return other;
    }
    private void setLink(LinkCallback link){
        this.link = link;
    }

    @Override
    public void onRequest() {
        if (link != null)
            link.onRequest();
    }

    @Override
    public void onSuccess(String s) {
        if (link != null)
            link.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        if (link != null)
            link.onError(s);
    }
}