package com.iigeo.appcode.libraryaidl.service;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.iigeo.appcode.libraryaidl.IMyAidlInterface;

public class MyService extends Service {

    private static final String TAG="服务端进程";
    public MyService() {

    }
    //继承Binder抽象类，自定义实现Binder方法
    private Binder mBinder=new IMyAidlInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            Log.i(TAG,TAG+getClass().getName()+"执行add方法");
            return a+b;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
}
