package com.drozda.vk.json.model.wrapper;

import com.drozda.vk.json.model.VkBaseResponse;

/**
 * Created by GFH on 01.09.2015.
 */
public class BaseResponseWrapper<T extends VkBaseResponse> {
    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
