package org.wang.online.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.wang.online.ui.main.MainActivity;
import org.wang.online.util.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王冰 on 2016/11/17.
 */

public abstract class BaseActivity<Bind extends ViewDataBinding> extends AppCompatActivity {
    /* 存储activity的集合*/
    private static volatile Map<Long, WeakReference<BaseActivity>> activities = new HashMap<>();

    public static Map<Long, WeakReference<BaseActivity>> getActivities() {
        return activities;
    }

    public static BaseActivity getActivity(long key) {
        if (activities.containsKey(key) && activities.get(key) != null) {
            return activities.get(key).get();
        }
        return null;
    }

    private Bind binding;//ViewDataBinding
    private long key;//给每个activity分配一个id
    private volatile boolean exit = false;
    private Handler handler = new Handler();
    private final Runnable resetExit = new Runnable() {
        @Override
        public void run() {
            exit = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.put(key = createKey(), new WeakReference<BaseActivity>(this));
        getParams(getIntent());
        if (layoutId() != 0) {
            binding = DataBindingUtil.setContentView(this, layoutId());
        }
    }

    protected void getParams(Intent intent) {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getParams(intent);
        setIntent(intent);
    }

    @Override
    public void onBackPressed() {
        if (activities.size() == 1 && !exit) {
            exit = true;
            ToastUtils.showMessage("再按一次退出程序");
            handler.postDelayed(resetExit, 2000);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (activities.containsKey(key) && activities.get(key) != null) {
            activities.get(key).clear();
            activities.remove(key);
        }
    }

    private long createKey() {
        long key = System.currentTimeMillis();
        while (activities.containsKey(key)) {
            key++;
        }
        return key;
    }

    public long getKey() {
        return key;
    }

    public Bind getBinding() {
        return binding;
    }

    /**
     * 处理部分点击事件
     *
     * @param view
     */
    public void onClick(View view) {
    }

    /**
     * 返回按钮
     *
     * @param view
     */
    public void back(View view) {
        onBackPressed();
    }

    /**
     * @return activity的布局id
     */
    public abstract int layoutId();

    public final void toMain(int index){
        MainActivity.start(this,index);
    }
}
