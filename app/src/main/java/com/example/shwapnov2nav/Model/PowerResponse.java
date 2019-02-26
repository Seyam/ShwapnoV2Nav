package com.example.shwapnov2nav.Model;

import com.google.gson.annotations.SerializedName;

public class PowerResponse {

    @SerializedName("power")
    String power;

    public PowerResponse(String power) {
        this.power = power;
    }

    public String getPower() {
        return power;
    }
}
