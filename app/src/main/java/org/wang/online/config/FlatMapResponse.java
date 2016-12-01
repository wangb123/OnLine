package org.wang.online.config;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * 变换响应体味响应数据
 * 作者：wang on 2016/9/21 09:32
 */
public class FlatMapResponse<Data> implements Func1<Response<Data>, Observable<Data>> {
    @Override
    public Observable<Data> call(Response<Data> response) {
        if (response.isSuccessful()) {
            return Observable.just(response.body());
        } else {
            try {
                return Observable.error(new Error(response.code(), response.errorBody().string()));
            } catch (IOException e) {
                return Observable.error(new Error(response.code(), e.getMessage()));
            }
        }
    }
}
