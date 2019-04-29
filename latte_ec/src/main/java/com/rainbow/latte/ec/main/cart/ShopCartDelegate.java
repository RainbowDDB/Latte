package com.rainbow.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rainbow.latte.delegate.bottom.BottomItemDelegate;
import com.rainbow.latte.ec.R;
import com.rainbow.latte.ec.R2;
import com.rainbow.latte.net.RestClient;
import com.rainbow.latte.net.callback.ISuccess;
import com.rainbow.latte.ui.recycler.data.DataConverter;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NotNull View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("shop_cart")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        DataConverter converter = new ShopCartDataConverter().setJsonData(response);
        ShopCartAdapter adapter = new ShopCartAdapter(converter.convert());
        mRecyclerView.setAdapter(adapter);
    }
}
