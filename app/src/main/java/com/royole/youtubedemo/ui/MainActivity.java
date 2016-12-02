package com.royole.youtubedemo.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.royole.youtubedemo.R;
import com.royole.youtubedemo.youtube.http.MySSLSocketFactory;
import com.royole.youtubedemo.youtube.oauth.OnOAuthListener;
import com.royole.youtubedemo.ui.fragment.OAuthFragment;
import com.royole.youtubedemo.ui.fragment.RefusedOAuthFragment;

import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends BaseActivity implements OnOAuthListener {
    private FrameLayout mRootView;
    private Fragment mMainFragment;

    @Override
    protected void initValues() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mRootView = (FrameLayout) findViewById(R.id.fl_root_view);
        replaceFragment(R.id.fl_root_view, new OAuthFragment());

        /*VideoView videoView = new VideoView(this);
        setContentView(videoView);
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            sf.fixHttpsURLConnection();
            HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        videoView.setVideoURI(Uri.parse("https://www.youtube.com/embed/hDoAxehFvn8?autoplay=1&origin=http://example.com"));
        videoView.setVideoURI(Uri.parse("https://www.youtube.com/embed/u0hmMt5znX0?autoplay=1&origin=http://example.com"));
        videoView.setVideoURI(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
        videoView.start();*/
    }

    @Override
    protected void LoadData() {

    }

    //  Implements OnOAuthListener
    @Override
    public void onAuthorized(String accessToken) {
        Intent intent = new Intent(MainActivity.this, YouTubeListActivity.class);
        intent.putExtra("accessToken", accessToken);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRefused() {
        replaceFragment(R.id.fl_root_view, new RefusedOAuthFragment());
    }
    // End implements OnOAuthListener

}
