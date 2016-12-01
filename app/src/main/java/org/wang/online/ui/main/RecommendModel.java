package org.wang.online.ui.main;

import com.google.gson.annotations.SerializedName;

import org.wang.online.config.ApiService;
import org.wang.online.config.Callback;
import org.wang.online.config.Error;
import org.wang.online.config.FlatMapResponse;
import org.wang.online.model.Model;
import org.wang.online.model.Room;
import org.wang.online.model.RoomGroup;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by 王冰 on 2016/11/30.
 */

public class RecommendModel extends Model {

    public static void get(final Callback<RecommendModel> callback) {

        ApiService.Creator.get().homeRecommend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (callback != null) callback.onStart();
                    }
                })
                .doAfterTerminate(new Action0() {//执行完OnCompleted或OnError
                    @Override
                    public void call() {
                        if (callback != null) callback.onAfter();
                    }
                })
                .flatMap(new FlatMapResponse<RecommendModel>())
                .subscribe(new Subscriber<RecommendModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (callback != null && e instanceof Error) {
                            callback.onFail((Error) e);
                        }
                    }

                    @Override
                    public void onNext(RecommendModel recommendModel) {
                        if (callback != null) callback.onSuccess(recommendModel);
                    }
                });

    }

    private List<RoomGroup> list;
    @SerializedName("app-index")
    private List<Room> index;
    @SerializedName("app-classification")
    private List<RoomGroup> hotGroup;
    @SerializedName("app-recommendation")
    private List<Room> recommend;
    @SerializedName("app-lol")
    private List<Room> lol;
    @SerializedName("app-beauty")
    private List<Room> beauty;
    @SerializedName("app-heartstone")
    private List<Room> heartstone;
    @SerializedName("app-huwai")
    private List<Room> huwai;
    @SerializedName("app-overwatch")
    private List<Room> overwatch;
    @SerializedName("app-blizzard")
    private List<Room> blizzard;
    @SerializedName("app-qqfeiche")
    private List<Room> qqfeiche;
    @SerializedName("app-mobilegame")
    private List<Room> mobilegame;
    @SerializedName("app-wangzhe")
    private List<Room> wangzhe;
    @SerializedName("app-dota2")
    private List<Room> dota2;
    @SerializedName("app-tvgame")
    private List<Room> tvgame;
    @SerializedName("app-webgame")
    private List<Room> webgame;
    @SerializedName("app-dnf")
    private List<Room> dnf;


    public List<RoomGroup> getList() {
        return list;
    }

    public void setList(List<RoomGroup> list) {
        this.list = list;
    }

    public List<Room> getIndex() {
        return index;
    }

    public void setIndex(List<Room> index) {
        this.index = index;
    }

    public List<RoomGroup> getHotGroup() {
        return hotGroup;
    }

    public void setHotGroup(List<RoomGroup> hotGroup) {
        this.hotGroup = hotGroup;
    }

    public List<Room> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<Room> recommend) {
        this.recommend = recommend;
    }

    public List<Room> getLol() {
        return lol;
    }

    public void setLol(List<Room> lol) {
        this.lol = lol;
    }

    public List<Room> getBeauty() {
        return beauty;
    }

    public void setBeauty(List<Room> beauty) {
        this.beauty = beauty;
    }

    public List<Room> getHeartstone() {
        return heartstone;
    }

    public void setHeartstone(List<Room> heartstone) {
        this.heartstone = heartstone;
    }

    public List<Room> getHuwai() {
        return huwai;
    }

    public void setHuwai(List<Room> huwai) {
        this.huwai = huwai;
    }

    public List<Room> getOverwatch() {
        return overwatch;
    }

    public void setOverwatch(List<Room> overwatch) {
        this.overwatch = overwatch;
    }

    public List<Room> getBlizzard() {
        return blizzard;
    }

    public void setBlizzard(List<Room> blizzard) {
        this.blizzard = blizzard;
    }

    public List<Room> getQqfeiche() {
        return qqfeiche;
    }

    public void setQqfeiche(List<Room> qqfeiche) {
        this.qqfeiche = qqfeiche;
    }

    public List<Room> getMobilegame() {
        return mobilegame;
    }

    public void setMobilegame(List<Room> mobilegame) {
        this.mobilegame = mobilegame;
    }

    public List<Room> getWangzhe() {
        return wangzhe;
    }

    public void setWangzhe(List<Room> wangzhe) {
        this.wangzhe = wangzhe;
    }

    public List<Room> getDota2() {
        return dota2;
    }

    public void setDota2(List<Room> dota2) {
        this.dota2 = dota2;
    }

    public List<Room> getTvgame() {
        return tvgame;
    }

    public void setTvgame(List<Room> tvgame) {
        this.tvgame = tvgame;
    }

    public List<Room> getWebgame() {
        return webgame;
    }

    public void setWebgame(List<Room> webgame) {
        this.webgame = webgame;
    }

    public List<Room> getDnf() {
        return dnf;
    }

    public void setDnf(List<Room> dnf) {
        this.dnf = dnf;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
