package org.wang.online.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by 王冰 on 2016/11/30.
 */

public class Model {
    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .create();
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public static Gson getGson() {
        return gson;
    }
}
