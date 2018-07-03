package com.iigeo.appcode.rxjava;

import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * map flatmap concatmap算子
 */
public class MapDemo {

    private String TAG;
    public MapDemo(String s) {
        this.TAG = s;
    };

    //1.创建Observable-被观察者对象
    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
            Log.i(TAG, "Observable;" + Thread.currentThread().getName());
            Log.i(TAG, "em1");
            emitter.onNext(1);
            Log.i(TAG, "em2");
            emitter.onNext(2);
            Log.i(TAG, "em3");
            //上游执行onComplete事件后，下游不会收到上游发送的事件，但上游后续事件可以继续发送
            //onError和onComplete是互斥的，只能执行一个。
            emitter.onComplete();
            //Log.i(TAG, "em4");
            emitter.onNext(3);
            Log.i(TAG, "Observable;" + Thread.currentThread().getName());
        }
    });

    //2.创建observer-观察者对象
    Observer<Integer> observer = new Observer<Integer>() {

        private Disposable disposable;
        private int i;

        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "subscribe");
            disposable = d;
        }

        @Override
        public void onNext(Integer integer) {
            Log.i(TAG, "Observer;" + Thread.currentThread().getName());
            Log.d(TAG, "onNext" + integer);
            i++;
            if (i == 2) {
                Log.d(TAG, "dispose");
                //中断上下游事件通道
                disposable.dispose();
                Log.d(TAG, "isDisposed:" + disposable.isDisposed());

            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete");
        }
    };

    // 定义map映射
    Function mapFunction=new Function<Integer,Object>() {
        @Override
        public Object apply(Integer integer) throws Exception {
            return "This is result"+integer;
        }
    };
    // 定义flatmap映射（一对多的映射）,注意flatmap不能保证下游的事件顺序
    Function flatMapFunction=new Function<Integer,ObservableSource<String>>(){
        @Override
        public ObservableSource<String> apply(Integer integer) throws Exception {
            List<String> stringList=new ArrayList<>();
            for (int i=0;i<5;i++){
                stringList.add("This is result"+integer);
            }
            return Observable.fromIterable(stringList);
        }
    };

    //concatMap和flatmap基本一致，可以确保下游事件的顺序和上游一致
    public void subConcatMap() {
        observable.concatMap(flatMapFunction).
                subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {
                        Log.i(TAG, o);
                    }
                });
    }

    //flatmap
   public void subFlatMap(){
            observable.flatMap(flatMapFunction).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                Log.i(TAG,o);
            }
        });
    }

    //多线程切换
   public void submutilScheduler(){
        //订阅/关注-subscribeOn是确定上游事件的线程 subscribeOn指定多个，只执行第一个指定的线程,observerOn是确定下游事件的线程，。
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //3.订阅
   public void subMap(){
        observable.map(mapFunction).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i(TAG,o+"");
            }
        });
    }


}
