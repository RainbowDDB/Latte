package com.rainbow.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.rainbow.latte.delegate.LatteDelegate;
import com.rainbow.latte.net.RestClient;
import com.rainbow.latte.net.callback.IRequest;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test();
    }

    private void test() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .params("", "")
                .loader(getContext())
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success((response) -> {
                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                })
                .failure(() -> {

                })
                .error((code, msg) -> {

                })
                .build()
                .get();

    }
}
