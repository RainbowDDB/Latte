package com.rainbow.latte.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rainbow.latte.app.Latte;
import com.rainbow.latte.delegate.web.IPageLoadListener;
import com.rainbow.latte.delegate.web.WebDelegate;
import com.rainbow.latte.delegate.web.route.Router;
import com.rainbow.latte.ui.loader.LatteLoader;
import com.rainbow.latte.util.logger.LatteLogger;

public class WebViewClientImpl extends WebViewClient {

    private static final Handler HANDLER = Latte.getHandler();
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        // 全部由原生进行接管
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(LatteLoader::stopLoading, 1000);
    }
}
