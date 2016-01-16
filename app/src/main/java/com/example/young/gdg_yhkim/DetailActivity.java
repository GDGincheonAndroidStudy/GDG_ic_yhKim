package com.example.young.gdg_yhkim;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//          String temperature = String.valueOf(w.getTemprature());
//        ((TextView) findViewById(R.id.tvCityName)).setText(strCity);
//        ((TextView) findViewById(R.id.tvTemp)).setText(temperature);
//        ((TextView) findViewById(R.id.tvLat)).setText(String.valueOf(w.getLat()));
//        ((TextView) findViewById(R.id.tvIon)).setText(String.valueOf(w.getIon()));
//        ((TextView) findViewById(R.id.tvPres)).setText(String.valueOf(w.getPres()));
//        ((TextView) findViewById(R.id.tvHumidity)).setText(String.valueOf(w.getHumidity()));
//        ((TextView) findViewById(R.id.tvTempMax)).setText(String.valueOf(w.getTempMax()));
//        ((TextView) findViewById(R.id.tvTempMin)).setText(String.valueOf(w.getTempMin()));
//        ((TextView) findViewById(R.id.tvCityName)).setText(strCity);

    }

}
