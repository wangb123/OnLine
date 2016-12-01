package org.wang.online.config;

import org.wang.online.util.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：wang on 2016/8/22 16:11
 */
public class NetConfig implements Interceptor, CookieJar {

    private static NetConfig instance;

    private NetConfig() {
    }

    public static NetConfig getInstance() {
        if (instance == null) {
            synchronized (NetConfig.class) {
                instance = new NetConfig();
            }
        }
        return instance;
    }

    private OkHttpClient client;

    public OkHttpClient getOkHttpClient() {
        if (client == null)
            client = new OkHttpClient.Builder().addInterceptor(this).cookieJar(this).build();
        return client;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null)
            LogUtils.e(cookies.toString());
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        LogUtils.e(url.toString());
        List<Cookie> cookies = new ArrayList<>();

        return cookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.d("request:" + request.toString());
        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        LogUtils.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
