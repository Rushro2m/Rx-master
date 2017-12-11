package com.rushro2m.rx_master.java_rx;

/**
 * Created by 16918 on 2017/12/9.
 */

//被观察者，小偷
public interface Watched {

    //添加观察者
    void addWathcer(Watcher watcher);

    //移除观察者
    void removeWathcer(Watcher watcher);

    //通知观察者
    void notifyWatcher(String str);

}
