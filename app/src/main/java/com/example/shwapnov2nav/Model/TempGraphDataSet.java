package com.example.shwapnov2nav.Model;


import com.google.gson.annotations.SerializedName;

public class TempGraphDataSet {
    @SerializedName("value")
    private Float tempValue;

    @SerializedName("timestamp")
    private String timestamp;

    public TempGraphDataSet(Float tempValue, String timestamp) {
        this.tempValue = tempValue;
        this.timestamp = timestamp;
    }

    public Float getTempValue() {
        return tempValue;
    }

    public void setTempValue(Float tempValue) {
        this.tempValue = tempValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
