package com.example.tailor.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static WebView  myWebView;
    static TextView tv;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //レイアウトで指定したTextViewのIDを指定する
        tv = (TextView)findViewById(R.id.tv1);
        //レイアウトで指定したWebViewのIDを指定する
        myWebView = (WebView)findViewById(R.id.webView1);

        //alertとpromptが来た時の処理を追加
        myWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onJsAlert(WebView view, String url, String message, android.webkit.JsResult result) {
                    tv.setText("Displayed alert");
                    System.out.println("-----alert-----");
                    return false;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, android.webkit.JsPromptResult result) {
                    //Toast.makeText(this, "jsのpromptを検知しました：", Toast.LENGTH_SHORT).show();
                    tv.setText("Displayed prompt");
                    System.out.println("-----prompt-----");
                    return false;
            }
        });

        //URLのページを表示する
        myWebView.loadUrl("http://192.168.1.5");

        //jacascriptを許可する
        myWebView.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, 1, Menu.NONE, "更新");
        return true;
    }

    // メニューが選択された時の処理
    public boolean onOptionsItemSelected(MenuItem item) {
        // addしたときのIDで識別
        switch (item.getItemId()) {
            case 1:
                return true;
            }
        return false;
    }

    //バックキーが押されたもアプリを終了しない
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            WebView webView = (WebView) findViewById(R.id.webView1);
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
