package com.rainbow.latte.ec.main.cart;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.rainbow.latte.ui.recycler.data.DataConverter;
import com.rainbow.latte.ui.recycler.data.MultipleFields;
import com.rainbow.latte.ui.recycler.data.MultipleItemEntity;

import java.util.ArrayList;

public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        final JsonArray jsonArray = new JsonParser()
                .parse(getJsonData()).getAsJsonObject()
                .get("data").getAsJsonArray();

        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final ShopCartEntity data = new Gson().fromJson(jsonArray.get(i), ShopCartEntity.class);
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, data.getId())
                    .setField(MultipleFields.IMAGE_URL, data.getImageUrl())
                    .setField(ShopCartItemFields.TITLE, data.getTitle())
                    .setField(ShopCartItemFields.DESC, data.getDesc())
                    .setField(ShopCartItemFields.COUNT, data.getCount())
                    .setField(ShopCartItemFields.PRICE, data.getPrice())
                    .setField(ShopCartItemFields.IS_SELECTED, false)
                    .setField(ShopCartItemFields.POSITION, i)
                    .build();
            dataList.add(entity);
        }
        return dataList;
    }
}
