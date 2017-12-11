package com.rushro2m.rx_master.android_rx;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 16918 on 2017/12/10.
 */

public class RxUtils {

    public static final String TAG = RxUtils.class.getSimpleName();

    public static void createObservable() {
        //定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("hello");
                    subscriber.onNext("hi");
                    //静态方法，否则不能调用
                    subscriber.onNext(downLoadJson());
                    subscriber.onNext("world");
                    //表示消息发送完成
                    subscriber.onCompleted();


                }
            }
        });

        /**
         * onNext():Observable调用这个方法发送数据，方法的参数就是Observable发送的数据，
         *          这个方法可能被调用多次，取决于你的实现
         *
         * onError():当Observable遇到错误或者无法返回期望的数据时会调用这个方法，
         *              这个方法会终止Observable，后续不会再调用onNext()和onCompleted()
         *                onError()方法的参数是抛出的异常
         *
         * onCompleted():正常终止，如果没有遇到错误，Observable在最后一次调用onNext()之后调用此方法
         */
        //观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            //
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            //当Observable遇到错误或者无法返回期望的数据时会调用这个方法，这个方法会终止Observable，
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "result--->: " + s);
            }
        };
        //关联被观察者
        observable.subscribe(subscriber);
    }

    public static String downLoadJson() {
        return "Json data";
    }

    public static void createPrint() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 5; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }
        });
    }


    //被观察者返回的对象一般为数值类型
    public static void from() {
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Observable observable = Observable.from(items);
        //new Action:表示执行这个动作
//        observable.subscribe(new Action1() {
//            @Override
//            public void call(Object o) {
//                Log.e(TAG, "call: " + o.toString());
//            }
//        });
        observable.subscribe(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                Log.e(TAG, "call: "+o.toString() );
            }
        });
    }

    //指定某一时刻进行数据发送
    public static void interval() {
        //每隔一秒发送一条数据
        Observable observable = Observable.interval(2, TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.e(TAG, "call: " + o.toString());
            }
        });
    }

    public static void just() {
        Integer[] items1 = {1, 2, 3, 4, 5, 6};
        Integer[] items2 = {6, 5, 4, 3, 2, 1};
        Observable observable = Observable.just(items1, items2);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer[] integers) {
                for (int i = 0; i < integers.length; i++) {
                    Log.e(TAG, "onNext: " + integers[i]);
                }
            }

        });
    }

    //指定数据输出的范围
    public static void range() {
        Observable observable = Observable.range(1, 10);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }

        });
    }

    /**
     * 被观察者先将消息过滤一遍后在发送给观察者
     */
    public static void filter() {
        Observable observable = Observable.just(1,2,3,4,5,6,7,8);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                Log.e(TAG, "onNext: " + o.toString());
            }
        });
    }


}
