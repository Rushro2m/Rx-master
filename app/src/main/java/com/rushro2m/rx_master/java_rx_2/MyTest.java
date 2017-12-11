package com.rushro2m.rx_master.java_rx_2;

/**
 * Created by 16918 on 2017/12/10.
 */

public class MyTest {
    public static void main(String[] args) throws Exception{
        SimpleObservable simple = new SimpleObservable();

        SimpleObserver observer = new SimpleObserver(simple);
        simple.setData(1);
        simple.setData(2);
        //如何没有发生变化，就不会通知观察者
        simple.setData(2);
        simple.setData(3);
    }
}
