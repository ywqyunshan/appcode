package com.iigeo.appcode.rxjava;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
/**
 * 常用操作符
 */
public class OperatorDemo {

    private String TAG;

    public OperatorDemo(String TAG) {
        this.TAG = TAG;
    }

    /**
     * 去重操作符
     */
    public void subDistinct(){
        Observable.just(1,2,3,33,3,3,2).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG,"去重结果："+integer);
            }
        });
    }

    /**
     * 获取前几个数据
     */
    public void subTask(){
        Observable.just(1,2,3,4,6,78).take(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG,"获取前三个元素："+integer);
            }
        });
    }

    /**
     * 合并多个事件-,无须
     */
    public void subMerge(){
        Observable observable1=Observable.just(1,2,3,78,79);
        Observable observable2=Observable.just(4,5,6).delay(10, TimeUnit.MILLISECONDS);
        Observable.merge(observable1,observable2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG,"合并元素："+integer);
            }
        });
    }

    /**
     * 合并多个事件-有序
     */
    public void subConcat(){
        Observable observable1=Observable.just(1,2,3);
        Observable observable2=Observable.just(4,5,6).delay(10, TimeUnit.MILLISECONDS);
        Observable.concat(observable1,observable2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG,"合并元素："+integer);
            }
        });
    }

}
