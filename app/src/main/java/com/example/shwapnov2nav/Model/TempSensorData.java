package com.example.shwapnov2nav.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TempSensorData {

    @SerializedName("did")
    private String did;

    @SerializedName("location")
    private String location;

    @SerializedName("value")
    private Double tempValue;

    @SerializedName("level")
    private Integer level;

    @SerializedName("timestamp")
    private String timestamp;

    public TempSensorData(String did, String location, Double tempValue, Integer level, String timestamp) {
        this.did = did;
        this.location = location;
        this.tempValue = tempValue;
        this.level = level;
        this.timestamp = timestamp;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTempValue() {
        return tempValue;
    }

    public void setTempValue(Double tempValue) {
        this.tempValue = tempValue;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
