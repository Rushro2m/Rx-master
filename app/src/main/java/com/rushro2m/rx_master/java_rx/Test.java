package com.rushro2m.rx_master.java_rx;

/**
 * Created by 16918 on 2017/12/9.
 */

public class Test {
    public static void main(String[] args) throws Exception {

        Watched xiaotou = new ConceteWatched();

        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();

        xiaotou.addWathcer(watcher1);
        xiaotou.addWathcer(watcher2);
        xiaotou.addWathcer(watcher3);

        xiaotou.notifyWatcher("小偷要偷东西了");
    }
}
