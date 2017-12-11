package com.rushro2m.rx_master.android_rx_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rushro2m.rx_master.R;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    ProgressDialog dialog;
    LoginUtils utils;
    public static final String LOGIN = "";
    private String TAG = LoginActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setListener();
    }

    private void setListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString()).trim();
                params.put("password", password.getText().toString()).trim();
                utils.login(LOGIN, params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                            }

                            @Override
                            public void onNext(String s) {
                                dialog.show();
                                if (JsonUtils.parserJson(s)) {
                                    startActivity(new Intent(LoginActivity.this, SuccessActivity.class));
                                }
                            }
                        });
            }
        });
    }

    private void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        dialog = new ProgressDialog(this);
        utils = new LoginUtils();
    }
}
