package com.iigeo.appcode.rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 *
 * 自动取消订阅的观察者
 */
public class DisposableDemo {

    private String TAG;

    public DisposableDemo(String TAG) {
        this.TAG = TAG;
    }

    Flowable flowable=Flowable.create(new FlowableOnSubscribe<Integer>() {
        @Override
        public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {

        }
    }, BackpressureStrategy.ERROR);

    public void subDisposFlowable(){

        flowable.subscribe(new DisposableSubscriber() {
            @Override
            public void onNext(Object o) {

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
