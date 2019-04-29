package com.rainbow.latte.ec.main.sort.list;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rainbow.latte.ui.recycler.data.DataConverter;
import com.rainbow.latte.ui.recycler.data.ItemType;
import com.rainbow.latte.ui.recycler.data.MultipleFields;
import com.rainbow.latte.ui.recycler.data.MultipleItemEntity;

import java.util.ArrayList;

public class VerticalDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JsonArray jsonArray = new JsonParser()
                .parse(getJsonData()).getAsJsonObject()
                .get("data").getAsJsonObject()
                .get("list").getAsJsonArray();

        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final JsonObject data = jsonArray.get(i).getAsJsonObject();
            final int id = data.get("id").getAsInt();
            final String name = data.get("name").getAsString();

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, false)
                    .build();

            dataList.add(entity);
            // 设置第一个点击
            dataList.get(0).setField(MultipleFields.TAG, true);
        }
        return dataList;
    }
}
