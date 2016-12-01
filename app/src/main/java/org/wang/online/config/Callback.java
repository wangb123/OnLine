package org.wang.online.config;

/**
 * Created by 王冰 on 2016/11/30.
 */

public interface Callback<Data> {
    void onStart();

    void onProgress(int progress);

    void onSuccess(Data data);

    void onFail(Error error);

    void onAfter();

    abstract class SimpleCallback<Data> implements Callback<Data> {

        @Override
        public void onStart() {

        }

        @Override
        public void onProgress( int progress) {

        }

        @Override
        public void onAfter() {

        }
    }
}
