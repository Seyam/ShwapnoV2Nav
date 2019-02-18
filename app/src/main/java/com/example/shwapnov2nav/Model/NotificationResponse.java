package com.example.shwapnov2nav.Model;


import com.google.gson.annotations.SerializedName;

public class NotificationResponse {
    @SerializedName("fishLevel")
    private Integer fishLevel;

    @SerializedName("meatLevel")
    private Integer meatLevel;

    @SerializedName("fruitLevel")
    private Integer fruitLevel;


    public NotificationResponse(Integer fishLevel, Integer meatLevel, Integer fruitLevel) {
        this.fishLevel = fishLevel;
        this.meatLevel = meatLevel;
        this.fruitLevel = fruitLevel;
    }


    public Integer getFishLevel() {
        return fishLevel;
    }

    public Integer getMeatLevel() {
        return meatLevel;
    }

    public Integer getFruitLevel() {
        return fruitLevel;
    }
}
