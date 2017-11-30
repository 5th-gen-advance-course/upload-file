package com.example.ratha.homework07_upload_iamge.entity.response;

import com.example.ratha.homework07_upload_iamge.entity.BaseEntity;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ratha on 11/30/2017.
 */

public class UrlIconResponse extends BaseEntity {
    @SerializedName("data")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
