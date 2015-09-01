package com.drozda.vk.json.model;

import com.drozda.vk.model.VKAudio;

import java.util.List;

/**
 * Created by GFH on 01.09.2015.
 */
public class VkAdioResponse extends VkBaseResponse {

    private int count;
    private List<VKAudio> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<VKAudio> getItems() {
        return items;
    }

    public void setItems(List<VKAudio> items) {
        this.items = items;
    }
}
