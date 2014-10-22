package android.tovalina.blogreader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class BlogWebActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_web);

        Intent intent = getIntent(); //gets Intent Data
        Uri blogUri = intent.getData(); //gets Uri data from the intent

        WebView webView = (WebView)findViewById(R.id.webView);
        webView.loadUrl(blogUri.toString());
    }
}
