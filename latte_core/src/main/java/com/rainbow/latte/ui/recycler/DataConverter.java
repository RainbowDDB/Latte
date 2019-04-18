package com.rainbow.latte.ui.recycler;

import java.util.ArrayList;

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }
}
