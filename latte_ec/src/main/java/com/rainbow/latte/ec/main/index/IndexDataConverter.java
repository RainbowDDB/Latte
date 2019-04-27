package com.rainbow.latte.ec.main.index;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rainbow.latte.ui.recycler.data.DataConverter;
import com.rainbow.latte.ui.recycler.data.ItemType;
import com.rainbow.latte.ui.recycler.data.MultipleFields;
import com.rainbow.latte.ui.recycler.data.MultipleItemEntity;
import com.rainbow.latte.util.logger.LatteLogger;

import java.util.ArrayList;

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        LatteLogger.json("index-json", getJsonData());
        final JsonObject jsonObject = new JsonParser().parse(getJsonData()).getAsJsonObject();
        final JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final JsonObject data = jsonArray.get(i).getAsJsonObject();

            final String imageUrl = !data.has("imageUrl") ?
                    null : data.get("imageUrl").getAsString();
            final String text = !data.has("text") ?
                    null : data.get("text").getAsString();
            final int spanSize = data.get("spanSize").getAsInt();
            final int id = data.get("goodsId").getAsInt();
            final JsonArray banners = !data.has("banners") ?
                    null : data.get("banners").getAsJsonArray();

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.get(j).getAsString();
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
