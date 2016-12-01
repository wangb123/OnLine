package org.wang.online;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.squareup.leakcanary.LeakCanary;

import org.wang.online.config.CrashHandler;
import org.wang.online.config.NetConfig;

import io.rong.common.SystemUtils;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 王冰 on 2016/11/17.
 */

public class OnLine extends Application {
    private static OnLine instance;

    public static OnLine getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Observable.just(getApplicationInfo().packageName.equals(SystemUtils.getCurrentProcessName(getApplicationContext())))
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            instance = OnLine.this;
                            CrashHandler.getInstance().init(getApplicationContext());//崩溃日志
                            //AVOS初始化,参数依次为 this, AppId, AppKey
//                            AVObject.registerSubclass(RoomSetting.class);
                            AVOSCloud.initialize(instance, "bX0UuxqOkcYTg83itpUsqkhR-gzGzoHsz", "rYBs5RWI92Pmc1rLlVsumttL");
                            LeakCanary.install(instance);//检测内存泄漏
                            Fresco.initialize(instance, OkHttpImagePipelineConfigFactory
                                    .newBuilder(getApplicationContext(), NetConfig.getInstance().getOkHttpClient())
                                    .build());//初始化Fresco
                            CrashHandler.getInstance().init(getApplicationContext());
//                            RongEvent.init(instance);//初始化融云
                        }
                    }
                });
    }
}
