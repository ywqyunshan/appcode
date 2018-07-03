package com.iigeo.appcode.rxjava;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 背压
 */
public class BackPressDemo {

    private String TAG;

    public BackPressDemo(String TAG) {
        this.TAG = TAG;
    }

    //上游发送数据和下游接受数据的速率不一致
    public void subBackPress() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                while (true) {
                    //无限循环发送
                    emitter.onNext(1);
                }

            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "" + integer);
            }
        });
    }
    //创建Flowable（背压）对象，设置背压策略
    Flowable<Integer> flowable=Flowable.create(new FlowableOnSubscribe<Integer>() {
        @Override
        public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
            Log.i(TAG,"上游发送能力1"+emitter.requested());
            Log.d(TAG, "emit 1");
            emitter.onNext(1);
            Log.d(TAG, "emit 2");
            Log.i(TAG,"上游发送能力2"+emitter.requested());
            emitter.onNext(2);
            Log.i(TAG,"上游发送能力3"+emitter.requested());

        }
    },BackpressureStrategy.ERROR);

    //同步调用,如果下游不处理request方法，将会报MissingBackpressureException错误
    public void subSynFlowBackPress(){
        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i(TAG,"onSubscribe");
                //下游处理事件的能力
                //s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG,"onNext"+integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"onComplete");
            }
        });
    }
    //异步调用，如果下游不处理数据，上游会将发出的数据存储到缓存队列中(默认大小是128个)
    public void subAsyFlowBackPress(){
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                //
                  s.request(Long.MAX_VALUE);
            }
            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
