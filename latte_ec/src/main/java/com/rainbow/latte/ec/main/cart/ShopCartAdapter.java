package com.rainbow.latte.ec.main.cart;

import android.widget.ImageView;
import android.widget.TextView;

import com.rainbow.latte.ec.R;
import com.rainbow.latte.ui.img.GlideApp;
import com.rainbow.latte.ui.recycler.MultipleRecyclerAdapter;
import com.rainbow.latte.ui.recycler.MultipleViewHolder;
import com.rainbow.latte.ui.recycler.data.MultipleFields;
import com.rainbow.latte.ui.recycler.data.MultipleItemEntity;

import java.util.List;

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    @Override
    protected void addItemTypes() {
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                final String imageUrl = item.getField(MultipleFields.IMAGE_URL);
                final int id = item.getField(MultipleFields.ID);
                final String desc = item.getField(ShopCartItemFields.DESC);
                final int count = item.getField(ShopCartItemFields.COUNT);
                final int price = item.getField(ShopCartItemFields.PRICE);
                final String title = item.getField(ShopCartItemFields.TITLE);
                final int position = item.getField(ShopCartItemFields.POSITION);
                final boolean isSelected = item.getField(ShopCartItemFields.IS_SELECTED);

                final ImageView image = holder.getView(R.id.img_item_shop_cart);
                final TextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final TextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final TextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final TextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);

                tvCount.setText(String.valueOf(count));
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvTitle.setText(title);
                GlideApp.with(mContext).load(imageUrl).centerCrop().dontAnimate().into(image);

                break;
            default:
                break;
        }
    }
}
