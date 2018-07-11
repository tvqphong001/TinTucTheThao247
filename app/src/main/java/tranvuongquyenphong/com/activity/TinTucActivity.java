package tranvuongquyenphong.com.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import tranvuongquyenphong.com.R;

public class TinTucActivity extends AppCompatActivity {

    WebView webView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Save:
                    Toast.makeText(TinTucActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_Comments:
                    Toast.makeText(TinTucActivity.this, "Comments", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_Share:
                    Toast.makeText(TinTucActivity.this, "Shared", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getResources().getString(R.string.app_name));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        webView = findViewById(R.id.webview_tintuc);

        // open javaScript to watch video
        webView.getSettings().setJavaScriptEnabled(true);
        // webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        // webView.getSettings().setSupportZoom(true);
        //webView.getSettings().setBuiltInZoomControls(true);
        // webView.getSettings().setAllowFileAccess(true);

        webView.loadUrl(link);


//        webView.setWebViewClient(new WebViewClient()
//        {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                webView.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
//            }
//        });
//        webView.loadDataWithBaseURL(null, link, "text/html", "UTF-8", null);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
