package com.example.young.gdg_yhkim;

import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young.gdg_yhkim.Model.City;
import com.example.young.gdg_yhkim.Model.WeatherListData;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
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

    @Bind(R.id.main_drawer_view)
    NavigationView navigationView;
    //@InjectView
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    //Realm
    private RealmSearchView realmSearchView;
    private CityRecyclerViewAdapter adapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.inject(this);
        ButterKnife.bind(this);

        //realm
        resetRealm();
        loadCityData();
        realmSearchView = (RealmSearchView) findViewById(R.id.search_view);
        realm = Realm.getInstance(this);
        adapter = new CityRecyclerViewAdapter(this, realm, "engname");
        realmSearchView.setAdapter(adapter);


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

        FloatingActionButton faAutoSearch = (FloatingActionButton) findViewById(R.id.fbAutoSearchCity);
        faSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RealmSearchView autoSearchCity = (RealmSearchView)findViewById(R.id.search_view);
                String strSearchCity = autoSearchCity.getSearchBarText().toString().trim();
                if(strSearchCity != null && strSearchCity.length() != 0)
                {
                    new NetworkRetrofit().request(callback, strSearchCity);
                    Snackbar.make(view, getString(R.string.refresh), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadCityData() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser jsonParserCity = jsonFactory.createParser(getResources().openRawResource(R.raw.world_city_b));
            List<City> entries =
                    objectMapper.readValue(jsonParserCity, new TypeReference<List<City>>() {
            });

            Realm realm = Realm.getInstance(this);
            realm.beginTransaction();
            realm.copyToRealm(entries);
            realm.commitTransaction();
            realm.close();

        }catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not load city data.");
        }


    }


    private void resetRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.deleteRealm(realmConfig);
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

    public class CityRecyclerViewAdapter extends RealmSearchAdapter<City,CityRecyclerViewAdapter.ViewHolder> {
        public CityRecyclerViewAdapter(Context context, Realm realm, String filterColumnName){
            super(context, realm, filterColumnName);
        }
        public class ViewHolder extends RealmSearchViewHolder {

            private CityItemView cityItemView;

            public ViewHolder(FrameLayout container, TextView footerTextView) {
                super(container, footerTextView);
            }

            public ViewHolder(CityItemView cityItemView) {
                super(cityItemView);
                this.cityItemView = cityItemView;
            }
        }

        @Override
        public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
            ViewHolder vh = new ViewHolder(new CityItemView(viewGroup.getContext()));
            return vh;
        }

        @Override
        public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
            final City city = realmResults.get(position);
            viewHolder.cityItemView.bind(city);
        }

        @Override
        public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup) {
            View v = inflater.inflate(R.layout.footer_view, viewGroup, false);
            return new ViewHolder(
                    (FrameLayout) v,
                    (TextView) v.findViewById(R.id.footer_text_view));
        }

        @Override
        public void onBindFooterViewHolder(ViewHolder holder, int position) {
            super.onBindFooterViewHolder(holder, position);
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }
            );
        }

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
