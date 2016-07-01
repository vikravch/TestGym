package com.example.vitaly.test_gym.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.vitaly.test_gym.R;

public class ShowPageActivity extends AppCompatActivity {

    public static final String URL_EXTRAS = "url";
    private static final int INTERNET_PERMISSION_REQUEST = 1;

    private WebView webView;
    private ProgressDialog progressDialog;

    boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_page);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION_REQUEST);
            } else {
                initViews();
            }
        }
        else{
            initViews();
        }
    }

    private void initViews() {
        Intent incomingIntent = getIntent();
        progressDialog =new ProgressDialog(this);
        webView = (WebView) findViewById(R.id.wvShowPage);
        webView.getSettings().setJavaScriptEnabled(true);
        String url = incomingIntent.getStringExtra(URL_EXTRAS);
        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebViewClient());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(url);
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if(!isLoaded) {
                progressDialog = ProgressDialog.show(ShowPageActivity.this,
                        getResources().getString(R.string.title_dialog_load),
                        getResources().getString(R.string.message_dialog_load));
            }
            isLoaded = true;
        }


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            progressDialog.dismiss();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case INTERNET_PERMISSION_REQUEST:
                if ((grantResults.length>0)&&(grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    initViews();
                }
        }
    }
}
