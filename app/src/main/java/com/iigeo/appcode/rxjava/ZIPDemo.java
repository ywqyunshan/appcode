package com.iigeo.appcode.rxjava;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * zip操作符
 */
public class ZIPDemo {

    private String TAG;

    public ZIPDemo(String TAG) {
        this.TAG = TAG;
    }

    Observable<Integer> observable1= Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
            Log.i(TAG, "em1");
            emitter.onNext(1);
            Log.i(TAG, "em2");
            emitter.onNext(2);
        }
    }).subscribeOn(Schedulers.io());

    Observable<String> observable2=Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            Log.i(TAG, "emA");
            emitter.onNext("A");
            Log.i(TAG, "emN");
            emitter.onNext("N");

        }
    }).subscribeOn(Schedulers.io());

    public void subZip(){
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer+s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "onNext: " + s);
            }
        });
    }
}
