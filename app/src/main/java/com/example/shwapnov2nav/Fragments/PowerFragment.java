package com.example.shwapnov2nav.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shwapnov2nav.API.APIService;
import com.example.shwapnov2nav.API.RetrofitInstance;
import com.example.shwapnov2nav.Database.DataLogger;
import com.example.shwapnov2nav.Database.TempSensorData;
import com.example.shwapnov2nav.Model.PowerResponse;
import com.example.shwapnov2nav.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PowerFragment extends Fragment {

    APIService apiService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_power, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        makeNetworkRequest(view);
    }

    private void makeNetworkRequest(final View view) {
        Log.e("Power","called API");
        DataLogger dataLogger = new DataLogger(getContext());
//        Log.e("authHeader",dataLogger.loadData("authHeader"));
        Observable<PowerResponse> myObservable= apiService.getPower(dataLogger.loadData("authHeader"))
                .subscribeOn(Schedulers.io())//we told RxJava to do all the work on the background(io) thread
                .observeOn(AndroidSchedulers.mainThread());//When the work is done and our data is ready, observeOn() ensures that onNext() or onSuccess() or onError() or accept() are called on the main thread.

        myObservable.subscribe(new Observer<PowerResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
//                Log.e("onSubscribe ","called for     TempSensorData");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(PowerResponse powerResponses) {
                Log.e("onNext ",powerResponses.getPower());


                TextView powerTextView = view.findViewById(R.id.txt_power_field);
                powerTextView.setText("Power: "+powerResponses.getPower()+" Watt");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError ","called for     Power");
            }

            @Override
            public void onComplete() {
//                Log.e("onComplete ","called for     TempSensorData");

            }
        });

    }
}
