package com.iigeo.appcode.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.iigeo.appcode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.handlerBtn)
    Button handlerBtn;
    @BindView(R.id.webviewBtn)
    Button webviewBtn;
    @BindView(R.id.BinderBtn)
    Button BinderBtn;
    @BindView(R.id.CustomViewBtn)
    Button CustomViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.handlerBtn, R.id.webviewBtn, R.id.BinderBtn,R.id.CustomViewBtn,
            R.id.AnimationBtn,R.id.SocketClientBIOBtn,R.id.RxBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.handlerBtn:
                startActivity(new Intent(this, HandlerActivity.class));
                break;
            case R.id.webviewBtn:
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.BinderBtn:
                startActivity(new Intent(this, BinderActivity.class));
                break;
            case R.id.CustomViewBtn:
                startActivity(new Intent(this,CustomViewActivity.class));
                break;
            case R.id.AnimationBtn:
                startActivity(new Intent(this,AnimationActivity.class));
                break;
            case R.id.SocketClientBIOBtn:
                startActivity(new Intent(this,SocketActivity.class));
                break;
            case R.id.RxBtn:
                startActivity(new Intent(this,RxActivity.class));
                break;
        }
    }

}
