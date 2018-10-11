package com.baitulmalfkam.baitulmalfkam;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class DonasiActivity extends AppCompatActivity {
    WebView web_confirm;
    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi);
        String url = "http://baitulmalfkam.com/layanan/konfirmasi-donasi/";
        final ProgressDialog dialog = new ProgressDialog(this);

        //progress_bar = findViewById(R.id.progress_bar);
        web_confirm = findViewById(R.id.web_confirm);
        web_confirm.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
        dialog.setMessage("Loading, Tunggu Sebentar");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        web_confirm.loadUrl(url);

        WebSettings webSettings = web_confirm.getSettings();
        webSettings.setJavaScriptEnabled(true);
       // web_confirm.getSettings().setJavaScriptEnabled(true);
        //web_confirm.loadUrl("http://baitulmalfkam.com/layanan/konfirmasi-donasi/");
        //web_confirm.setWebViewClient(new WebViewClient());
    }
}
