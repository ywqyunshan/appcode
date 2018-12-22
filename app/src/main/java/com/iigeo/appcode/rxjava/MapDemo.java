package com.iigeo.appcode.rxjava;

import android.nfc.Tag;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            Log.i(TAG, "em1");
            emitter.onNext(1);
            Log.i(TAG, "em2");
            emitter.onNext(2);
            Log.i(TAG, "em3");
            emitter.onNext(3);
        }
    });

    //2.创建observer-观察者对象
    Observer<Integer> observer = new Observer<Integer>() {

        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "subscribe");
        }

        @Override
        public void onNext(Integer integer) {
            Log.d(TAG, "onNext" + integer);
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
            for (int i=0;i<3;i++){
                stringList.add("This is result"+integer);
            }
            return Observable.fromIterable(stringList).delay(5, TimeUnit.MILLISECONDS);
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

    public void subSwitchMap(){
        observable.
                switchMap(flatMapFunction).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });
    }


    //3.map映射
   public void subMap(){
        observable.map(mapFunction).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i(TAG,o+"");
            }
        });
    }


}
