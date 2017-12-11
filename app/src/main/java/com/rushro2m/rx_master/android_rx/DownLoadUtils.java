package com.rushro2m.rx_master.android_rx;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 16918 on 2017/12/10.
 */

public class DownLoadUtils {
    OkHttpClient client;

    public DownLoadUtils() {
        client = new OkHttpClient();
    }

    public Observable<byte[]> downLoadImage(String string) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //访问网络操作
                    Request request = new Request.Builder().url(string).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (data != null) {
                                    subscriber.onNext(data);
                                }
                            }
                            //表示本次访问已经完成
                            subscriber.onCompleted();
                        }
                    });

                }
            }
        });
    }
}
