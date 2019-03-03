package com.example.shwapnov2nav.Model;

import com.google.gson.annotations.SerializedName;

public class DeviceListResponse {
    @SerializedName("devId")
    private String devId;
    @SerializedName("status")
    private Integer status;

    public DeviceListResponse(String devId, Integer status) {
        this.devId = devId;
        this.status = status;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
