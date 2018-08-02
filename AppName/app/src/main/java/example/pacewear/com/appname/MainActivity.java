package example.pacewear.com.appname;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pacewear.eventbus.AnyEvent;
import com.pacewear.eventbus.SecondEvent;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity implements  View.OnClickListener {

    private  static final String TAG = "MainActivity";
    public final static int DO_WORK = 1;

    private Button mClick;
    private Button mEventBus;
    private Button mSecond;
    private Button mRecycleView;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == DO_WORK) {
                //do something
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClick = (Button)findViewById(R.id.button_test);
        mClick.setOnClickListener(this);
        mEventBus = (Button)findViewById(R.id.eventBUs);
        mEventBus.setOnClickListener(this);
        mSecond = (Button)findViewById(R.id.secondEventBus) ;
        mSecond.setOnClickListener(this);
        EventBus.getDefault().register(this);  // 在这里注册

        mRecycleView = (Button)findViewById(R.id.recycleView);
        mRecycleView.setOnClickListener(this);

        startService();

        appendData();
    }

    private void appendData(){
        int a = 5;
        int b = 10;
        int c = a +b;
        Log.d(TAG,"print c:"+c);

        int name = 15;

        // todo
        Log.d(TAG,"appendData to the name.");

    }

    private void startService(){
        Intent intent = new Intent(MainActivity.this,EventService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_test:
                MainActivity.this.finish();
                mHandler.sendEmptyMessage(DO_WORK);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "btnHandler", Toast.LENGTH_SHORT).show();
                    }
                }, 15 * 1000);
                break;
            case R.id.eventBUs:
                // 发布当前的事件
                sendEvent();
                break;

            case R.id.secondEventBus:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendSubThreadEvent();
                    }
                }).start();

                break;

            case R.id.recycleView:
                Intent intent = new Intent(MainActivity.this,RecycleActivity.class);
                startActivity(intent);

                break;
        }
    }

    /**
     *
     */
    private void sendEvent(){
        EventBus.getDefault().post(new AnyEvent("new task is create."));
    }

    private void sendSubThreadEvent(){
        EventBus.getDefault().post(new SecondEvent(3,"123"));
    }


    public void onEventMainThread(AnyEvent event) {
        Log.d(TAG,"onEventMainThread收到了消息:"+event.getDiscribe());
    }

    public void onEvent(AnyEvent event) {
        Log.d(TAG, "OnEvent收到了消息：" + event.getDiscribe());
    }


    public void onEventBackgroundThread(SecondEvent event){
        Log.d(TAG,"onEventBackgroundThread:"+event.getAge());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
