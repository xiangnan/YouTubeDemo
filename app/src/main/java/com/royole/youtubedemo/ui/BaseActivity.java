package com.royole.youtubedemo.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.MotionEvent;

import com.royole.youtubedemo.app.ControlerApplication;
import com.royole.youtubedemo.utils.Logger;

import java.util.ArrayList;

public abstract class BaseActivity extends Activity {
    protected String TAG = this.getClass().getSimpleName();

    // Abstract - M
    protected abstract void initValues();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void LoadData();
    // End Abstract - M

    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<>();
    protected Fragment mFragment;

    // lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StylesUtil.initStatusBar(this);
        //StylesUtil.initLandscapeOrientation(this);

        // switch activity
        //overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);

        //Init view and load data
        initValues();
        initView(savedInstanceState);
        LoadData();

        ControlerApplication.sAppContext.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ControlerApplication.sInFront = true;
        ControlerApplication.sFrontActivity = this;
    }


    @Override
    protected void onPause() {
        super.onPause();
        ControlerApplication.sInFront = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ControlerApplication.sAppContext.removeActivity(this);
    }

    // End lifecycle

    // handle fragment on TouchEvent
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            listener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    final public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }

    final public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        ArrayList<MyOnTouchListener> list = new ArrayList<>(onTouchListeners);
        list.remove(myOnTouchListener);
        onTouchListeners = list;
        //onTouchListeners.remove(myOnTouchListener);
    }

    public interface MyOnTouchListener {
        boolean onTouch(MotionEvent ev);
    }
    // End handle fragment onTouchEvent

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Logger.d(TAG, "onBackPressed");
    }

    public void replaceFragment(@IdRes int container, Fragment to) {
        if (to == null) {
            return;
        }
        String name = to.getClass().getSimpleName();
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        //transaction.addToBackStack(name);
        transaction.replace(container, to, name);
        transaction.commit();
    }

    public void addFragment(@IdRes int container, Fragment to) {
        if (to == null)
            return;
        String name = to.getClass().getSimpleName();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (to != mFragment) {
            transaction.addToBackStack(name);
            if (!to.isAdded()) {
                transaction.hide(mFragment).add(container, to, name)
                        .commit();
            } else {
                transaction.hide(mFragment).show(to).commit();
            }
            this.mFragment = to;
        }
    }

}
