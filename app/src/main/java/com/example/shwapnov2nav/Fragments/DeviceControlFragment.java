package com.example.shwapnov2nav.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shwapnov2nav.API.APIService;
import com.example.shwapnov2nav.API.RetrofitInstance;
import com.example.shwapnov2nav.Adapter.DeviceListAdapter;
import com.example.shwapnov2nav.Database.DataLogger;
import com.example.shwapnov2nav.Model.DeviceListResponse;
import com.example.shwapnov2nav.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DeviceControlFragment extends Fragment {
    APIService apiService;
    private DeviceListAdapter adapter;
    private RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*init API. We must first create an instance of the ApiService using the Retrofit object we get as returned from RetrofitInstance class.*/
        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        View rootView = inflater.inflate(R.layout.fragment_device_list, null);
        //Create only a single instance of DB
//        dBService = TempSensorDataDatabase.getInstance(MainActivity.this);


        makeNetworkRequestForDeviceList(rootView);





        return rootView;
    }

    private void makeNetworkRequestForDeviceList(final View rootView) {
        Log.e("Control"," called API");
        DataLogger dataLogger = new DataLogger(getContext());
        Observable<List<DeviceListResponse>> myObservable= apiService.getLightList(dataLogger.loadData("authHeader"))
                .subscribeOn(Schedulers.io())//we told RxJava to do all the work on the background(io) thread
                .observeOn(AndroidSchedulers.mainThread());//When the work is done and our data is ready, observeOn() ensures that onNext() or onSuccess() or onError() or accept() are called on the main thread.
        myObservable.subscribe(new Observer<List<DeviceListResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
//                Log.e("onSubscribe ","called for     TempSensorData");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<DeviceListResponse> deviceListResponse) {
                Log.e("onNext ","got data");
                generateViewForDeviceControl(rootView, deviceListResponse);
//                dBService.getTempSensorDataDAO().insert(tempSensorData);

//                Log.e("data", tempSensorData.toString()); //Won't work for lists of data unless you run a for loop through!
//                for(TempSensorData tsData : tempSensorData){
//                    Log.e("data", tsData.getLocation());
//                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError ","called for     TempSensorData");
                Toast.makeText(getContext(), "Request failed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
//                Log.e("onComplete ","called for     TempSensorData");

            }
        });
    }


    private void generateViewForDeviceControl(View rootView, List<DeviceListResponse> deviceListResponse) {

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_device_control);


        //create a new constructor of EmployeeAdapter class with required params
        adapter = new DeviceListAdapter(deviceListResponse, getActivity());
        //create a new constructor of LinearLayoutManager class with required params
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
