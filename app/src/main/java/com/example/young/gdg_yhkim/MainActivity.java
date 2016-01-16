package com.example.young.gdg_yhkim;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private final String strSeoul = "seoul";
    private final String strBeijing = "beijing";
    private final String strChicago = "chicago";
    private final String strDubai = "dubai";
    private final String strLondon = "london";
    private String strCurCity = "seoul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeather(strCurCity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "coming soon", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Seoul) {
            strCurCity = strSeoul;
        } else if (id == R.id.action_Beijing) {
            strCurCity = strBeijing;
        } else if (id == R.id.action_Chicago) {
            strCurCity = strChicago;
        } else if (id == R.id.action_Dubai) {
            strCurCity = strDubai;
        } else if (id == R.id.action_London) {
            strCurCity = strLondon;
        }
        getWeather(strCurCity);

        return super.onOptionsItemSelected(item);
    }


    public void getWeather(String strCity) {
        strCurCity = strCity;

        // 날씨를 읽어오는 API 호출
        OpenWeatherAPITask t = new OpenWeatherAPITask();
        try {
            Weather w = t.execute(strCity).get();

            String temperature = String.valueOf(w.getTemprature());

            ((TextView) findViewById(R.id.tvCityName)).setText(strCity);
            ((TextView) findViewById(R.id.tvTemp)).setText(temperature);
            ((TextView) findViewById(R.id.tvLat)).setText(String.valueOf(w.getLat()));
            ((TextView) findViewById(R.id.tvIon)).setText(String.valueOf(w.getIon()));
            ((TextView) findViewById(R.id.tvPres)).setText(String.valueOf(w.getPres()));
            ((TextView) findViewById(R.id.tvHumidity)).setText(String.valueOf(w.getHumidity()));
            ((TextView) findViewById(R.id.tvTempMax)).setText(String.valueOf(w.getTempMax()));
            ((TextView) findViewById(R.id.tvTempMin)).setText(String.valueOf(w.getTempMin()));
            ((TextView) findViewById(R.id.tvCityName)).setText(strCity);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
