package org.wang.online.model;

import org.wang.online.config.ApiService;
import org.wang.online.config.Callback;
import org.wang.online.config.Error;
import org.wang.online.config.FlatMapResponse;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by 王冰 on 2016/12/1.
 */

public class PageModel<Data> {

    public static final void getPlayers(int index, final Callback<PageModel<Player>> callback) {
        ApiService.Creator.get().pagePlayers(index == 1 ? "" : "_" + index)
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
                .flatMap(new FlatMapResponse<PageModel<Player>>())
                .subscribe(new Subscriber<PageModel<Player>>() {
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
                    public void onNext(PageModel<Player> playerPageModel) {
                        if (callback != null) callback.onSuccess(playerPageModel);
                    }
                });
    }

    private int total;
    private int pageCount;
    private int page;
    private int size;
    private String icon;
    private List<Data> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
