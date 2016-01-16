package com.example.young.gdg_yhkim;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dong on 2016-01-17.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<Weather> mWeatherArrayList;

    public WeatherListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mWeatherArrayList = new ArrayList<>();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_weather, parent, false);

        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        final Weather weather = mWeatherArrayList.get(position);

        holder.tvTemp.setText(weather.getTemprature() + "");
        holder.tvDatetime.setText(weather.getDatetime() + "");
        holder.tvTempMax.setText(weather.getTempMax() + "");
        holder.tvTempMin.setText(weather.getTempMin() + "");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //디테일 정보 보여주는 액티비티 실행
                Intent intent = new Intent(mContext, DetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWeatherArrayList.size();
    }

    public void setWeatherArrayList(ArrayList<Weather> weatherArrayList){
        mWeatherArrayList = weatherArrayList;
        notifyDataSetChanged();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView tvTemp;
        TextView tvDatetime;
        TextView tvTempMin;
        TextView tvTempMax;

        View mView;

        public WeatherViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            tvTemp = (TextView) itemView.findViewById(R.id.textView_temprature);
            tvDatetime = (TextView) itemView.findViewById(R.id.textView_datetime);
            tvTempMin = (TextView) itemView.findViewById(R.id.textView_tempMin);
            tvTempMax = (TextView) itemView.findViewById(R.id.textView_tempMax);
        }
    }
}
