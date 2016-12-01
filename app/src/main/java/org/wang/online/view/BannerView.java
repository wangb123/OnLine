package org.wang.online.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王冰 on 2016/11/30.
 */

public class BannerView extends ViewPager {
    private static final int Scroll = 1;
    private static final int ALTERNATION = 5000;
    PagerAdapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Scroll:
                    startScroll();
                    if (getCurrentItem() < Integer.MAX_VALUE - 1) {
                        setCurrentItem(getCurrentItem() + 1);
                    } else {
                        setCurrentItem(1000 * adapter.getCount(), false);
                    }
                    break;
            }
        }
    };

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (adapter == null) return;
        this.adapter = adapter;
        super.setAdapter(new LooperAdapter(adapter));
        initCurrentItem();
        startScroll();
    }

    @Override
    public PagerAdapter getAdapter() {
        return adapter;
    }

    /**
     * 触摸的时候停止翻页
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE) {
            // 开始翻页
            startScroll();
        } else if (action == MotionEvent.ACTION_DOWN) {
            // 停止翻页
            stopScroll();
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 不在页面显示的时候停止翻页
     *
     * @param visibility
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == View.VISIBLE) {
            startScroll();
        } else if (visibility == INVISIBLE || visibility == GONE) {
            stopScroll();
        }
    }

    private void startScroll() {
        if (adapter == null || adapter.getCount() < 2) return;
        stopScroll();
        this.handler.sendEmptyMessageDelayed(Scroll, ALTERNATION);
    }

    private void stopScroll() {
        this.handler.removeMessages(Scroll);
    }

    private void initCurrentItem() {
        int count = adapter.getCount();
        if (count > 1) {
            setCurrentItem(1000 * count, false);
        }
    }

    private class LooperAdapter extends PagerAdapter {
        PagerAdapter adapter;

        public LooperAdapter(PagerAdapter adapter) {
            this.adapter = adapter;
            adapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    notifyDataSetChanged();
                    initCurrentItem();
                }
            });
        }

        @Override
        public int getCount() {
            return adapter.getCount() > 1 ? Integer.MAX_VALUE : adapter.getCount();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return adapter.isViewFromObject(view, object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            adapter.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return adapter.instantiateItem(container, getPosition(position));
        }

        private int getPosition(int position) {
            return position % adapter.getCount();
        }
    }
}
