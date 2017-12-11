package com.rushro2m.rx_master.java_rx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16918 on 2017/12/9.
 */

//具体的某一个被观察者
public class ConceteWatched implements Watched {

    List<Watcher> watcherList = new ArrayList<>();
    @Override
    public void addWathcer(Watcher watcher) {
        watcherList.add(watcher);
    }

    @Override
    public void removeWathcer(Watcher watcher) {
        watcherList.remove(watcher);
    }

    @Override
    public void notifyWatcher(String str) {
        for (Watcher watcher : watcherList) {
            watcher.update(str);
        }
    }
}
