package com.rushro2m.rx_master.android_rx_2;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscriber;

/**
 * Created by 16918 on 2017/12/10.
 * <p>
 * 定义login，使用RxAndroid的编程思想
 */

public class LoginUtils {
    OkHttpClient client;

    public LoginUtils() {
        client = new OkHttpClient();
    }

    public rx.Observable<String> login(String url, Map<String, String> params) {
        return rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (params != null && !params.isEmpty()) {
                        for (Map.Entry<String, String> entyr : params.entrySet()) {
                            builder.add(entyr.getKey(), entyr.getValue());
                        }
                    }
                    //构建一个请求体
                    RequestBody requestBody = builder.build();
                    Request request = new Request.Builder().url(url).post(requestBody).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                subscriber.onNext(response.body().string());
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }
}
