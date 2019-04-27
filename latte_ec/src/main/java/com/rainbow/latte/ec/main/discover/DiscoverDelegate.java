package com.rainbow.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rainbow.latte.delegate.bottom.BottomItemDelegate;
import com.rainbow.latte.delegate.web.WebDelegateImpl;
import com.rainbow.latte.ec.R;

import org.jetbrains.annotations.NotNull;

public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NotNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
//        delegate.setPageLoadListener(new IPageLoadListener() {
//            @Override
//            public void onLoadStart() {
//
//            }
//
//            @Override
//            public void onLoadEnd() {
//
//            }
//        });
        // 不添加到返回栈
//        loadRootFragment(R.id.web_discovery_container, delegate, false, false);
        loadRootFragment(R.id.web_discovery_container, delegate);
    }
}
