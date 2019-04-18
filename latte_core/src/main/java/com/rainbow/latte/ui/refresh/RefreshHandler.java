package com.rainbow.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.rainbow.latte.util.timer.TimerHelper;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        new TimerHelper() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }.start(2000);
    }
}
