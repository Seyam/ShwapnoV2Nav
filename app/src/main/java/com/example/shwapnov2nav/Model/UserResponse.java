package com.example.shwapnov2nav.Model;

import com.google.gson.annotations.SerializedName;


public class UserResponse {

    @SerializedName("response")
    private String response;

    public UserResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

}
