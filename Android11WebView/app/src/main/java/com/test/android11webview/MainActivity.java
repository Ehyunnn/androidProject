package com.test.android11webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        WebView webView = new WebView(this);
//        webView.loadUrl("http://developer.android.com");

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                MainActivity.this.setProgress(progress * 1000);
            }

        });


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(
                        view, request);
            }

        });


        webView.loadUrl("http://bootstrapk.com/examples/jumbotron/");
        getWindow().requestFeature(Window.FEATURE_PROGRESS);


        webView.getSettings().setJavaScriptEnabled(true);
        setContentView(webView);

    }
}
