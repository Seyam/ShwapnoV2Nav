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
import com.example.shwapnov2nav.Activity.MainActivity;
import com.example.shwapnov2nav.Adapter.EmployeeAdapter;
import com.example.shwapnov2nav.Database.DataLogger;
import com.example.shwapnov2nav.Database.TempSensorData;
import com.example.shwapnov2nav.Database.TempSensorDataDatabase;
import com.example.shwapnov2nav.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TemperatureFragment extends Fragment {

    private APIService apiService;
    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DataLogger dataLogger;

//    TempSensorDataDatabase dBService;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*init API. We must first create an instance of the ApiService using the Retrofit object we get as returned from RetrofitInstance class.*/
        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        //Create only a single instance of DB
//        dBService = TempSensorDataDatabase.getInstance(MainActivity.this);
        View rootView = inflater.inflate(R.layout.fragment_temperature, null);

        makeNetworkRequestForTemp(rootView);





        return rootView;
    }



    private void makeNetworkRequestForTemp(final View rootView) {
        Log.e("Temp","called API");

        dataLogger = new DataLogger(getContext());

        Observable<List<TempSensorData>> myObservable= apiService.getTempData(dataLogger.loadData("authHeader"))
                .subscribeOn(Schedulers.io())//we told RxJava to do all the work on the background(io) thread
                .observeOn(AndroidSchedulers.mainThread());//When the work is done and our data is ready, observeOn() ensures that onNext() or onSuccess() or onError() or accept() are called on the main thread.
        myObservable.subscribe(new Observer<List<TempSensorData>>() {
            @Override
            public void onSubscribe(Disposable d) {
//                Log.e("onSubscribe ","called for     TempSensorData");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<TempSensorData> tempSensorData) {
                Log.e("onNext ","got data");
                generateTempDataList(rootView, tempSensorData);
//                dBService.getTempSensorDataDAO().insert(tempSensorData);

//                Log.e("data", tempSensorData.toString()); //Won't work for lists of data unless you run a for loop through!
//                for(TempSensorData tsData : tempSensorData){
//                    Log.e("data", tsData.getLocation());
//                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError ","called for     TempSensorData");
                Toast.makeText(getContext(),"Request Failed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
//                Log.e("onComplete ","called for     TempSensorData");

            }
        });
    }


    private void generateTempDataList(View rootView, List<TempSensorData> empDataList) {

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_data_list);


        //create a new constructor of EmployeeAdapter class with required params
        adapter = new EmployeeAdapter(empDataList, getActivity());
        //create a new constructor of LinearLayoutManager class with required params
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }


}
