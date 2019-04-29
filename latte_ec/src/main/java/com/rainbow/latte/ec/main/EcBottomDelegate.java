package com.rainbow.latte.ec.main;

import android.graphics.Color;

import com.rainbow.latte.delegate.bottom.BaseBottomDelegate;
import com.rainbow.latte.delegate.bottom.BottomItemDelegate;
import com.rainbow.latte.delegate.bottom.BottomTabBean;
import com.rainbow.latte.delegate.bottom.ItemBuilder;
import com.rainbow.latte.ec.main.cart.ShopCartDelegate;
import com.rainbow.latte.ec.main.discover.DiscoverDelegate;
import com.rainbow.latte.ec.main.index.IndexDelegate;
import com.rainbow.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new SortDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
