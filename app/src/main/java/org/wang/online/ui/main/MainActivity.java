package org.wang.online.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.wang.online.R;
import org.wang.online.databinding.ActivityMainBinding;
import org.wang.online.ui.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    public static void start(Context context, int index) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra("index", index);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().setModel(new MainViewModel(new MainAdapter(getSupportFragmentManager())));
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getTag().toString()) {
            case "recommend":
                getBinding().content.setCurrentItem(0, false);
                break;
            case "column":
                getBinding().content.setCurrentItem(1, false);
                break;
            case "online":
                getBinding().content.setCurrentItem(2, false);
                break;
            case "self":
                getBinding().content.setCurrentItem(3, false);
                break;
        }
    }
}
