package com.iigeo.appcode.base;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.iigeo.appcode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * native与JS交互
 */
public class WebActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.handlerBtn)
    Button handlerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        final WebSettings webSettings = webview.getSettings();
        //开启JS交互权限
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 1.JS调用native---加载URL地址
        webview.loadUrl("file:///android_asset/js.html");
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //2.和服js端约定好协议格式，协议名称，根据这两个字段做判断
                Uri uri = Uri.parse(url);
                //3.判断scheme格式
                if (uri.getScheme().equals("js")) {
                    //判断协议名称
                    if (uri.getAuthority().equals("webview")) {
                        System.out.println("js调用了Android的方法");
                        String name = uri.getQuery();
                        System.out.println(name);
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

        });
    }

    @OnClick(R.id.handlerBtn)
    public void onViewClicked() {
          if (Build.VERSION.SDK_INT>18){
                    webview.evaluateJavascript("js:callJS()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            Log.i("TAG",s);
                        }
                    });
                }else {
                    webview.loadUrl("js:callJS()");
                }
    }
}
