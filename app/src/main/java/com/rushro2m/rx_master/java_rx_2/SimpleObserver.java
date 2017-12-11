package com.rushro2m.rx_master.java_rx_2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 16918 on 2017/12/10.
 */

//定义一个观察者，去监听被观察者
public class SimpleObserver implements Observer {

    public SimpleObserver(SimpleObservable observable) {
        observable.addObserver(this);
    }

    //当被观察者状态发生改变的时候，被调用
    @Override
    public void update(Observable observable, Object o) {
        System.out.println("data is changed--->"+((SimpleObservable)observable).getData());
    }
}
