package com.iigeo.appcode.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iigeo.appcode.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
