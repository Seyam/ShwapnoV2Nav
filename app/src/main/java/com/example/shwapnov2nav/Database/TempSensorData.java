package com.example.shwapnov2nav.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "sensor_data_table")
public class TempSensorData {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "row_id")
    @SerializedName("id")
    private Integer id;


    @ColumnInfo(name = "location_table")
    @SerializedName("location")
    private String location;

    @ColumnInfo(name = "temperature_table")
    @SerializedName("value")
    private Double tempValue;

    @ColumnInfo(name = "timestamp_table")
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
