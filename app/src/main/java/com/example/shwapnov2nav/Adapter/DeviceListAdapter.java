package com.example.shwapnov2nav.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shwapnov2nav.API.APIService;
import com.example.shwapnov2nav.API.RetrofitInstance;
import com.example.shwapnov2nav.Database.DeviceControl;
import com.example.shwapnov2nav.Database.TempSensorData;
import com.example.shwapnov2nav.Model.UserResponse;
import com.example.shwapnov2nav.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceListViewHolder> {
    private List<DeviceControl> dataList;
    private Context context;

    public DeviceListAdapter(List<DeviceControl> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeviceListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_device_control, viewGroup, false);
        return new DeviceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceListViewHolder holder, final int position) {
        holder.txtDevId.setText(dataList.get(position).getDevId());
//        holder.txtStatus.setText(dataList.get(position).getStatus().toString());

        if(dataList.get(position).getStatus() == 1){
            holder.mSwitch.setChecked(true);
        }
        else{
            holder.mSwitch.setChecked(false);
        }

        holder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(context, "You switched "+dataList.get(position).getDevId()+" ON", Toast.LENGTH_SHORT).show();
                    Observable<UserResponse> myObservable = apiService.sendPostAsFormUrlEncoded(dataList.get(position).getDevId(), "1")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    myObservable.subscribe(new Observer<UserResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UserResponse userResponse) {
                            Toast.makeText(context, "Response : "+userResponse.getResponse(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

                }
                else {
                    Toast.makeText(context, "You switched "+dataList.get(position).getDevId()+" OFF", Toast.LENGTH_SHORT).show();
                    Observable<UserResponse> myObservable = apiService.sendPostAsFormUrlEncoded(dataList.get(position).getDevId(), "0")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    myObservable.subscribe(new Observer<UserResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UserResponse userResponse) {
                            Toast.makeText(context, "Response: "+userResponse.getResponse(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DeviceListViewHolder extends RecyclerView.ViewHolder{

        TextView  txtDevId;
        Switch mSwitch;

//        LinearLayout linearLayout;

        public DeviceListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDevId = (TextView) itemView.findViewById(R.id.txt_devId_field);
            mSwitch = (Switch) itemView.findViewById(R.id.switch_field);
//            txtStatus = (TextView) itemView.findViewById(R.id.txt_status_field);
//            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout_field);

        }
    }
}
