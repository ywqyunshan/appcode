package com.iigeo.appcode.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iigeo.appcode.R;
import com.iigeo.appcode.rxjava.BackPressDemo;
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
        MapDemo mapDemo =new MapDemo(TAG);
        /*mapDemo.subConcatMap();
        mapDemo.subFlatMap();
        mapDemo.submutilScheduler();
        mapDemo.subMap();*/
        //ZIPDemo zipDemo=new ZIPDemo(TAG);
        //zipDemo.subZip();
        /*BackPressDemo backPressDemo=new BackPressDemo(TAG);
        backPressDemo.subSynFlowBackPress();*/
        OperatorDemo operatorDemo=new OperatorDemo(TAG);
        //operatorDemo.subDistinct();
        //operatorDemo.subTask();
        operatorDemo.subMerge();
        operatorDemo.subConcat();
    }
}
