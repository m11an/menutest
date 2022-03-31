package com.example.menutest.network.dto;

import com.google.gson.annotations.SerializedName;

public class WrapperDto<T> {
    @SerializedName("data")
    public T data;
    @SerializedName("code")
    public int code;
    @SerializedName("status")
    public String status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
