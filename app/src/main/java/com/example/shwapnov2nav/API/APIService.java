package com.example.shwapnov2nav.API;


import com.example.shwapnov2nav.Database.DeviceControl;
import com.example.shwapnov2nav.Database.TempSensorData;
import com.example.shwapnov2nav.Model.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIService {

    @GET("temp")
    Observable<List<TempSensorData>> getTempData();

    @POST("devlist")
    Observable<List<DeviceControl>> getDeviceInfo();

    @FormUrlEncoded
    @POST("control")
    Observable<UserResponse> sendPostAsFormUrlEncoded(
            @Field("did") String did,
            @Field("command") String command
    );


//    @GET("profile")
//    Observable<Notification> getNotificationProfile();
}
