package com.rushro2m.rx_master.java_rx_2;

import java.util.Observable;

/**
 * Created by 16918 on 2017/12/10.
 */

//定义一个被观察者，发生改变时，通知观察者
public class SimpleObservable extends Observable {
    //监听的数据变化
    int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        if (data != data) {
            this.data = data;
            setChanged();
            //通知观察者，表示状态发生改变
            notifyObservers();
        }
    }
}
