package com.iigeo.appcode.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iigeo.appcode.R;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 线程间通信——Handler消息传递
 */
public class HandlerActivity extends AppCompatActivity {

    public MyHandler subHandler;
    private FirstThread firstThread;
    private SecondThread secondThread;
    private Context mContext;
    private ThreadLocal<Boolean> threadLocal=new ThreadLocal<>();
    private String TAG=HandlerActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);
        mContext=this;
        initTheadLocal();
        initThread();
    }

    /**
     * 初始化ThreadLocal
     */
    private void initTheadLocal() {
        threadLocal.set(true);
        Log.d("ThreadLocalMain",threadLocal.get()+"");
    }

    private void initThread() {
        firstThread = new FirstThread();
        firstThread.start();
        secondThread=new SecondThread();
        secondThread.start();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Message message=Message.obtain();
        message.obj="1";
        Log.i("MThread", Thread.currentThread().getName()+"----发送了消息！"+message.obj);
        if (subHandler!=null) subHandler.sendMessage(message);
    }

    public static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("MThread", "收到消息了：" + Thread.currentThread().getName() + "----" + msg.obj);
        }
    }

    public static class WeakHandler extends Handler {
        private WeakReference<HandlerActivity> handlerActivityWeakReference;

        WeakHandler(HandlerActivity handlerActivity) {
            handlerActivityWeakReference = new WeakReference<HandlerActivity>(handlerActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity handlerActivity1 = handlerActivityWeakReference.get();
            super.handleMessage(msg);
            if (handlerActivity1 != null) {
                Log.i("MThread", "收到消息了：" + Thread.currentThread().getName() + "----" + msg.obj);
            }
        }
    }

    class FirstThread extends Thread {

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            subHandler=new MyHandler();
            Looper.loop();
            //SystemClock.sleep(1000);
            threadLocal.set(false);
            Log.i("ThreadLocalFirst",threadLocal.get()+"");

        }
    }

    class SecondThread extends Thread{
        @Override
        public void run() {
            super.run();
            Log.i("ThreadLocalSecond",threadLocal.get()+"");
        }
    }
}
