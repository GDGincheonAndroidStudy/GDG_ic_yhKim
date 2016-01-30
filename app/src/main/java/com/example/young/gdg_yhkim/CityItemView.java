package com.example.young.gdg_yhkim;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.young.gdg_yhkim.Model.City;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by apple on 2016-01-30.
 */
public class CityItemView extends RelativeLayout {
    @Bind(R.id.tv_eng_city)
    TextView tvEngCity;

    @Bind(R.id.tv_kor_city)
    TextView tvKorCity;

    @Bind(R.id.tv_country)
    TextView tvCountry;

    public CityItemView(Context context){
        super(context);
        init(context);
    }

    private void init(Context context){
        inflate(context, R.layout.city_item_view, this);
        ButterKnife.bind(this);
    }
    public void bind(City city){
        tvEngCity.setText(city.getEngname());
        tvKorCity.setText(city.getKorname());
        tvCountry.setText(city.getCountry()+"");
    }
}
