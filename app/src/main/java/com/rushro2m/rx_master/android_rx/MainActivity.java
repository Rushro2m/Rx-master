package com.rushro2m.rx_master.android_rx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rushro2m.rx_master.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rxAndroid(View view) {
        switch (view.getId()) {
            case R.id.create_1:
                RxUtils.createObservable();
                break;
            case R.id.create_2:
                RxUtils.createPrint();
                break;
            case R.id.from:
                RxUtils.from();
                break;
            case R.id.interval:
                RxUtils.interval();
                break;
            case R.id.just:
                RxUtils.just();
                break;
            case R.id.range:
                RxUtils.range();
                break;
            case R.id.filter:
                RxUtils.filter();
                break;
            case R.id.goSecond:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }
}
