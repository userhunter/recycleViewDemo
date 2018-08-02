package example.pacewear.com.appname;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.pacewear.eventbus.SecondEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by p_billylu on 2018/7/5.
 */

public class EventService extends Service{

    private static final String TAG = "EventService";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);  // 在这里注册
    }

    public void onEventBackgroundThread(SecondEvent event){
        Log.d(TAG,"onEventBackgroundThread:"+event.getAge());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);    // 反注册
    }
}
