package com.example.young.gdg_yhkim;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young.gdg_yhkim.Model.WeatherListData;

import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private final String strSeoul = "seoul";
    private final String strBeijing = "beijing";
    private final String strChicago = "chicago";
    private final String strDubai = "dubai";
    private final String strLondon = "london";
    private String strCurCity = "seoul";

    @InjectView(R.id.main_drawer_view)
    NavigationView navigationView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


        //retrofit 적용전
        //getWeather(strCurCity);

        //retrofit 적용후
        new NetworkRetrofit().request(callback, strCurCity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NetworkRetrofit().request(callback, strCurCity);
                Snackbar.make(view, getString(R.string.refresh), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton faSearch = (FloatingActionButton) findViewById(R.id.fbSearchCity);
        faSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatEditText acEtSearchCity = (AppCompatEditText)findViewById(R.id.etSearchCity);
                String strSearchCity = acEtSearchCity.getText().toString().trim();
                if(strSearchCity != null && strSearchCity.length() != 0)
                {

                    acEtSearchCity.setText("");
                    new NetworkRetrofit().request(callback, strSearchCity);
                    Snackbar.make(view, getString(R.string.refresh), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.navi_group1_seoul:
                strCurCity = strSeoul;
                break;
            case R.id.navi_group1_beijing:
                strCurCity = strBeijing;
                break;
            case R.id.navi_group1_chicago:
                strCurCity = strChicago;
                break;
            case R.id.navi_group1_dubai:
                strCurCity = strDubai;
                break;
            case R.id.navi_group1_london:
                strCurCity = strLondon;
                break;
        }

        new NetworkRetrofit().request(callback, strCurCity);


            drawerLayout.closeDrawers();
            menuItem.setChecked(true);
        return true;
    }

 /**
  * 상단 Toolbar menu
  **/

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

        Log.e("CITYDATA : ", strCurCity);

        //retrofit 적용전
        //getWeather(strCurCity);

        //retrofit 적용후
        new NetworkRetrofit().request(callback, strCurCity);

        return super.onOptionsItemSelected(item);
    }


    private Callback callback = new Callback<WeatherListData>() {
        @Override
        public void onResponse(Response<WeatherListData> response) {
            Log.d("Weather", "onResponse");
            if(response.body().getCod() == 200){
                weatherDataSet(response.body());
            }else{
                Toast.makeText(getApplication(),"Failed to read data",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Throwable t) {
            Log.d("TEST", "onFailure");
            Toast.makeText(getApplication(),"Failed to read data",Toast.LENGTH_SHORT).show();
        }
    };

    private void weatherDataSet(WeatherListData  weatherdata){
        WeatherListData w = weatherdata;
        strCurCity = w.getName();
        ((TextView) findViewById(R.id.tvCityName)).setText(w.getName());
        ((TextView) findViewById(R.id.tvTemp)).setText(String.valueOf(w.getMain().getTemp()));
        ((TextView) findViewById(R.id.tvLat)).setText(String.valueOf(w.getCoord().getLat()));
        ((TextView) findViewById(R.id.tvIon)).setText(String.valueOf(w.getCoord().getLon()));
        ((TextView) findViewById(R.id.tvPres)).setText(String.valueOf(w.getMain().getPressure()));
        ((TextView) findViewById(R.id.tvHumidity)).setText(String.valueOf(w.getMain().getHumidity()));
        ((TextView) findViewById(R.id.tvTempMax)).setText(String.valueOf(w.getMain().getTemp_max()));
        ((TextView) findViewById(R.id.tvTempMin)).setText(String.valueOf(w.getMain().getTemp_min()));
    }



    /**
     * ASynkTask 사용
     * **/
    //retro fit 적용전
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
