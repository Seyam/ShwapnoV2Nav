package com.example.shwapnov2nav.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shwapnov2nav.Database.TempSensorData;
import com.example.shwapnov2nav.R;

import java.util.List;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<com.example.shwapnov2nav.Database.TempSensorData> dataList;
    private Context context;

    public EmployeeAdapter(List<TempSensorData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_tempdata, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, final int position) {
//        holder.txtId.setText(dataList.get(position).getId().toString());
        holder.txtLocation.setText(dataList.get(position).getLocation());
        holder.txtTemp.setText(dataList.get(position).getTempValue().toString()+"°C");
        holder.txtTimestamp.setText(dataList.get(position).getTimestamp());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked: "+dataList.get(position).getLocation(), Toast.LENGTH_SHORT).show();
                Log.e("Tag: ", "Clicked");

            }
        });

//        if(dataList.get(position).getTempValue() > 20.0){
//            holder.linearLayout.setBackgroundColor(Color.RED);
//        }
//        else {
//            holder.linearLayout.setBackgroundColor(Color.GREEN);
//        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView  txtLocation, txtTemp, txtTimestamp;
        LinearLayout linearLayout;

        EmployeeViewHolder(View itemView) {
            super(itemView);
//            txtId = (TextView) itemView.findViewById(R.id.txt_id_field);
            txtLocation = (TextView) itemView.findViewById(R.id.txt_location_field);
            txtTemp = (TextView) itemView.findViewById(R.id.txt_temp_field);
            txtTimestamp = (TextView) itemView.findViewById(R.id.txt_timestamp_field);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout_field);
        }
    }
}