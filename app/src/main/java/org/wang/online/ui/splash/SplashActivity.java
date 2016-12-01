package org.wang.online.ui.splash;

import org.wang.online.R;
import org.wang.online.databinding.ActivitySplashBinding;
import org.wang.online.ui.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    Subscription subscription;

    @Override
    public int layoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscription = Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        toMain(1);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
