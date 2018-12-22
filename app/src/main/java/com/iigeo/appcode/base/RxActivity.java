package com.iigeo.appcode.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iigeo.appcode.R;
import com.iigeo.appcode.rxjava.BackPressDemo;
import com.iigeo.appcode.rxjava.BaseObserverDemo;
import com.iigeo.appcode.rxjava.MapDemo;
import com.iigeo.appcode.rxjava.OperatorDemo;
import com.iigeo.appcode.rxjava.ZIPDemo;


public class RxActivity extends AppCompatActivity {

    private String TAG=RxActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        initRx();
    }

    private void initRx() {

        /*-------map flatmap concatmap------------------*/

        /*BaseObserverDemo baseObserverDemo =new BaseObserverDemo(TAG);
        baseObserverDemo.submutilScheduler();*/

        /*--------------------zip------------------------*/

        //ZIPDemo zipDemo=new ZIPDemo(TAG);
        //zipDemo.subZip();

        /*------------------BackPressDemo----------------*/
        /*BackPressDemo backPressDemo=new BackPressDemo(TAG);
        backPressDemo.subSynFlowBackPress();*/

        /*------------------OperatorDemo----------------*/
        OperatorDemo operatorDemo=new OperatorDemo(TAG);
        //operatorDemo.subDistinct();
        //operatorDemo.subTask();
        //operatorDemo.subMerge();
        //operatorDemo.subConcat();
        //operatorDemo.subSingle();
        //operatorDemo.subCompletable();
        //operatorDemo.subMaybe();

        /*------------------MapDemo----------------*/
        MapDemo mapDemo=new MapDemo(TAG);
        //mapDemo.subFlatMap();
        mapDemo.subSwitchMap();
    }
}
