package com.rainbow.latte.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rainbow.latte.ui.recycler.data.DataConverter;
import com.rainbow.latte.ui.recycler.data.MultipleFields;
import com.rainbow.latte.ui.recycler.data.MultipleItemEntity;

import java.util.List;

/**
 * Adapter初始化的操作
 */
public abstract class MultipleRecyclerAdapter
        extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup {

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    // 添加Items
    protected abstract void addItemTypes();

    public abstract MultipleRecyclerAdapter create(DataConverter converter);

    private void init() {
        addItemTypes();
        // 设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        // 多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
