package cn.icybee.icyjob.app;
import android.app.Application;
import com.jude.beam.Beam;
import com.jude.http.RequestManager;
import com.jude.utils.JUtils;
import com.activeandroid.ActiveAndroid;

/**
 * Created by huangxuefeng on 16/5/1.
 */
public class APP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        //JUtils.setDebug(BuildConfig.DEBUG, "JoyLog");
        RequestManager.getInstance().init(this);
        //RequestManager.getInstance().setDebugMode(BuildConfig.DEBUG, "JoyNet");
        Beam.init(this);
        ActiveAndroid.initialize(this);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
