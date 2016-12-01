package org.wang.online.config;

import org.wang.online.model.Model;
import org.wang.online.ui.main.RecommendModel;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 王冰 on 2016/11/30.
 */

public interface ApiService {

    @GET("json/page/appv2-index/info.json")
    Observable<Response<RecommendModel>> homeRecommend();

    class Creator {
        private static ApiService apiService;

        public static ApiService get() {
            if (apiService == null) {
                create();
            }
            return apiService;
        }

        private static synchronized void create() {
            if (apiService == null) {
                apiService = getRetrofit().create(ApiService.class);
            }
        }

        private static Retrofit getRetrofit() {

            return new Retrofit.Builder()
                    .baseUrl("http://www.quanmin.tv/")
                    .client(NetConfig.getInstance().getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(Model.getGson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }
}
