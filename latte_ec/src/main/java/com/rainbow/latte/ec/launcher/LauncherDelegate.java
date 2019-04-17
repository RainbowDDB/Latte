package com.rainbow.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rainbow.latte.delegate.LatteDelegate;
import com.rainbow.latte.ec.R;
import com.rainbow.latte.ec.R2;
import com.rainbow.latte.ui.launcher.ScrollLauncherTag;
import com.rainbow.latte.util.storage.LattePreference;
import com.rainbow.latte.util.timer.BaseTimerTask;
import com.rainbow.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    TextView mTvTimer;

    private int mCount = 5;
    private Timer mTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScrollPages();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    // 判断是否显示初次滚动页
    private void checkIsShowScrollPages() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 检查用户是否已经登录
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(() -> {
            if (mTvTimer != null) {
                mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                mCount--;
                if (mCount < 0) {
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                        checkIsShowScrollPages();
                    }
                }
            }
        });
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }
}
