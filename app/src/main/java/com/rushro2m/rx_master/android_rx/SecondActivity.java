package com.rushro2m.rx_master.android_rx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rushro2m.rx_master.R;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PATH = "http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg";
    private static final String TAG = SecondActivity.class.getSimpleName();
    Button btn;
    ImageView iv;
    DownLoadUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        setListener();
    }





    private void initView() {
        btn = findViewById(R.id.downLoad);
        iv = findViewById(R.id.iv);
        utils = new DownLoadUtils();
    }

    private void setListener() {
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.downLoad:
                setData();
                break;
        }
    }

    private void setData() {
        utils
                .downLoadImage(PATH)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<byte[]>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        iv.setImageBitmap(bitmap);
                    }
                });
    }
}
