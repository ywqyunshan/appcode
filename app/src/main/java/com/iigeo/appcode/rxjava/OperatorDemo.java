package com.iigeo.appcode.rxjava;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
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
     * 合并多个事件-,无序
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
    public void subConcat() {
        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.just(4, 5, 6).delay(10, TimeUnit.MILLISECONDS);
        Observable.concat(observable1, observable2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "合并元素：" + integer);
            }
        });

    }

    //定义只发射单个数据的被观察者,success和error只能发送一个
    Single single=Single.create(new SingleOnSubscribe<Integer>() {
        @Override
        public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
            //emitter.onSuccess(1);
            //emitter.onSuccess(2);
            emitter.onError(new Exception());
        }
    });

    //定义发送完成通知的被观察者，complete和errot只能发送一个
    Completable completable=Completable.create(new CompletableOnSubscribe() {
        @Override
        public void subscribe(CompletableEmitter emitter) throws Exception {
            //emitter.onComplete();
            emitter.onError(new Exception());
        }
    });

    //定义发送单个数据或者完成通知的被观察者，complete和error只能发送一个
    Maybe maybe=Maybe.create(new MaybeOnSubscribe<Integer>() {
        @Override
        public void subscribe(MaybeEmitter<Integer> emitter) throws Exception {
            emitter.onSuccess(1);
            //emitter.onComplete();
        }
    });

    public void subMaybe(){
        maybe.subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"MayBe:"+"onSubscribe");
            }

            @Override
            public void onSuccess(Integer o) {
                Log.i(TAG,"MayBe:"+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"MayBe:"+"onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"MayBe:"+"onComplete");
            }
        });
    }

    public void subCompletable(){
        completable.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"Completable:"+"onSubscribe");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"Completable:"+"onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"Completable:"+"onError");
            }
        });
    }


    public void subSingle(){
        single.subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"Single:"+"onSubscribe");
            }

            @Override
            public void onSuccess(Integer o) {
                Log.i(TAG,"Single:"+"onSuccess:"+o);

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"Single:"+"onError");
            }
        });
    }
}
