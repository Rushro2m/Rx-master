package com.rushro2m.rx_master.java_rx;

/**
 * Created by 16918 on 2017/12/9.
 */

//具体的某一个观察者
public class ConcreteWatcher implements Watcher {
    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
