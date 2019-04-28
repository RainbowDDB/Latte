package com.rainbow.latte.ec.main.index;

import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.rainbow.latte.ec.R;
import com.rainbow.latte.ui.banner.BannerCreator;
import com.rainbow.latte.ui.img.GlideApp;
import com.rainbow.latte.ui.recycler.MultipleRecyclerAdapter;
import com.rainbow.latte.ui.recycler.MultipleViewHolder;
import com.rainbow.latte.ui.recycler.data.DataConverter;
import com.rainbow.latte.ui.recycler.data.ItemType;
import com.rainbow.latte.ui.recycler.data.MultipleFields;
import com.rainbow.latte.ui.recycler.data.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

public final class IndexAdapter extends MultipleRecyclerAdapter implements OnItemClickListener {

    // 确保初始化一次Banner，防止重复item加载
    private boolean mIsInitBanner = false;

    private IndexAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    IndexAdapter() {
        this(null);
    }

    @Override
    protected void addItemTypes() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
    }

    @Override
    public MultipleRecyclerAdapter create(DataConverter converter) {
        return new IndexAdapter(converter.convert());
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.tv_single_item, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                GlideApp.with(mContext)
                        .load(imageUrl)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.img_single_item));
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                holder.setText(R.id.tv_multiple_item, text);
                GlideApp.with(mContext)
                        .load(imageUrl)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.img_multiple_item));
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
