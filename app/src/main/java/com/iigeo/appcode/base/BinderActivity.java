package com.iigeo.appcode.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.iigeo.appcode.R;
import com.iigeo.appcode.libraryaidl.IMyAidlInterface;
import com.iigeo.appcode.libraryaidl.service.MyService;

/**
 * 进程间通信-AIDL
 */
public class BinderActivity extends AppCompatActivity {
    private String TAG="客户端Service";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        initService();
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //1.客户端连接远程Service
            Log.i(TAG,TAG+getClass().getName());
            //2,获取服务端的BinderProxy对象并转换为AIDL接口的像是
           IMyAidlInterface myAidlObject= IMyAidlInterface.Stub.asInterface(iBinder);
           Log.i(TAG,TAG+myAidlObject.getClass().getName());
           //3.执行服务端的add方法
            try {
                int result=myAidlObject.add(1,2);
                Log.i(TAG,TAG+result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private void initService() {
        Intent intent=new Intent(this, MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);


    }
}
