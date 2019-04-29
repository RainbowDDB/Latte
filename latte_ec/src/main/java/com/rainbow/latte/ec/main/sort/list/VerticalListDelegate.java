package com.rainbow.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rainbow.latte.delegate.LatteDelegate;
import com.rainbow.latte.ec.R;
import com.rainbow.latte.ec.R2;
import com.rainbow.latte.ec.main.sort.SortDelegate;
import com.rainbow.latte.net.RestClient;
import com.rainbow.latte.ui.recycler.data.MultipleItemEntity;
import com.rainbow.latte.util.logger.LatteLogger;

import java.util.List;

import butterknife.BindView;

public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("sort_list")
                .loader(getContext())
                .success((response -> {
                    LatteLogger.json("vertical_list", response);
                    final List<MultipleItemEntity> data =
                            new VerticalDataConverter().setJsonData(response).convert();
                    final SortDelegate delegate = getParentDelegate();
                    final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                    mRecyclerView.setAdapter(adapter);
                }))
                .build()
                .get();

    }
}
