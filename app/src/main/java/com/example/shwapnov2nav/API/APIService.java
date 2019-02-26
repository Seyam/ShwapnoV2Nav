package com.example.shwapnov2nav.API;


import com.example.shwapnov2nav.Database.DeviceControl;
import com.example.shwapnov2nav.Database.TempSensorData;
import com.example.shwapnov2nav.Model.TempGraphDataSet;
import com.example.shwapnov2nav.Model.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface APIService {

    @POST("temp")
    Observable<List<TempSensorData>> getTempData();

    @POST("devlist")
    Observable<List<DeviceControl>> getDeviceInfo();

    @POST("tempdataset")
    Observable<List<TempGraphDataSet>> getTempDataSet(@Query("location") String location);

    @FormUrlEncoded
    @POST("control")
    Observable<UserResponse> sendPostAsFormUrlEncoded(
            @Field("did") String did,
            @Field("command") String command
    );


//    @GET("profile")
//    Observable<Notification> getNotificationProfile();
}
