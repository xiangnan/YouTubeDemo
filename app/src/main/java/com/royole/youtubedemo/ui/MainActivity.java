package com.royole.youtubedemo.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.royole.youtubedemo.R;
import com.royole.youtubedemo.youtube.oauth.OnOAuthListener;
import com.royole.youtubedemo.ui.fragment.OAuthFragment;
import com.royole.youtubedemo.ui.fragment.RefusedOAuthFragment;

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
