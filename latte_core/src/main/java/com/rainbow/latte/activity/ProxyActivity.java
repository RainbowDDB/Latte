package com.rainbow.latte.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.rainbow.latte.R;
import com.rainbow.latte.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import qiu.niorgai.StatusBarCompat;

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        // 全局父容器
        final FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            if (findFragment(setRootDelegate().getClass()) == null) {
                loadRootFragment(R.id.delegate_container, setRootDelegate());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 横向转场动画
        return new DefaultHorizontalAnimator();
    }
}
