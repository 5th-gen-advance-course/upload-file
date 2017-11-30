package com.example.ratha.homework07_upload_iamge.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ratha on 11/30/2017.
 */

public class BaseEntity {

    @SerializedName("code")
    private String code;
    @SerializedName("status")
    private boolean status;
    @SerializedName("msg")
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
