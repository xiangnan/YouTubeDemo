package com.royole.youtubedemo.app;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by jini on 2016/8/23.
 */
public class ControlerApplication extends Application {
    public static ControlerApplication sAppContext;
    public static Handler sUiHandler = new Handler(Looper.getMainLooper());
    public static Activity sFrontActivity = null;
    public static boolean sInFront;
    private ArrayList<Activity> activityList;

    // Volley Queue
    public static RequestQueue volleyQueue;

    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }

    @Override
    public void onCreate() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate();
        sAppContext = this;
        activityList = new ArrayList<Activity>();

        // Create Volley Queue
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
        }
    }

    public void exit() {
        if (activityList.size() > 0) {
            for (Activity a : activityList) {
                if (!a.isFinishing()) {
                    a.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
