package com.example.shwapnov2nav.API;


import com.example.shwapnov2nav.Database.TempSensorData;
import com.example.shwapnov2nav.Model.DeviceListResponse;
import com.example.shwapnov2nav.Model.PowerResponse;
import com.example.shwapnov2nav.Model.TempGraphDataSet;
import com.example.shwapnov2nav.Model.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface APIService {

    @POST("login")
    Observable<UserResponse> sendPostAsBasicAuthentication(@Header("Authorization") String authHeader);

    @POST("temp")
    Observable<List<TempSensorData>> getTempData(@Header("Authorization") String authHeader);

    @POST("tempdataset")
    Observable<List<TempGraphDataSet>> getTempDataSet(@Query("location") String location);

    @POST("lightlist")
    Observable<List<DeviceListResponse>> getLightList(@Header("Authorization") String authHeader);

    @POST("aclist")
    Observable<List<DeviceListResponse>> getAcList(@Header("Authorization") String authHeader);

    @POST("power")
    Observable<PowerResponse> getPower(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("control")
    Observable<UserResponse> sendControlCommand(
            @Field("did") String did,
            @Field("command") String command
    );


//    @GET("profile")
//    Observable<Notification> getNotificationProfile();
}
