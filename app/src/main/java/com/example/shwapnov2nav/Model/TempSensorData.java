package com.example.shwapnov2nav.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TempSensorData {
    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("location")
    private String location;
    @Expose
    @SerializedName("value")
    private Double tempValue;
    @Expose
    @SerializedName("timestamp")
    private String timestamp;

    public TempSensorData(Integer id, String location, Double tempValue, String timestamp) {
        this.id = id;
        this.location = location;
        this.tempValue = tempValue;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
