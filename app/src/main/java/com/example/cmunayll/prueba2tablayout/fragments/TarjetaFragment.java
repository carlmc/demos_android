package com.example.cmunayll.prueba2tablayout.fragments;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cmunayll.prueba2tablayout.AnalyticsApplication;
import com.example.cmunayll.prueba2tablayout.R;
import com.example.cmunayll.prueba2tablayout.adapters.CuentaAdapter;
import com.example.cmunayll.prueba2tablayout.adapters.TarjetaAdapter;
import com.example.cmunayll.prueba2tablayout.interfaces.CuentaInterface;
import com.example.cmunayll.prueba2tablayout.interfaces.TarjetaInterface;
import com.example.cmunayll.prueba2tablayout.jsons.JSONCuenta;
import com.example.cmunayll.prueba2tablayout.jsons.JSONTarjeta;
import com.example.cmunayll.prueba2tablayout.models.Cuenta;
import com.example.cmunayll.prueba2tablayout.models.Tarjeta;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class TarjetaFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipe;
    private ArrayList<Tarjeta> tarjetas;
    private TarjetaAdapter adapter;

    private Tracker mTracker;
    private static final String TAG = TarjetaFragment.class.getSimpleName();
    String name = "Fragment Tarjeta";

    public TarjetaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recyclerview_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());

        swipe = view.findViewById(R.id.swiperefresh);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

        /*RequestQueue requestQueue = Volley.newRequestQueue(container.getContext());
        String url = "http://192.168.8.102/Volley/CardList.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                tarjetas = Arrays.asList(mGson.fromJson(response, Tarjeta[].class));
                adapter = new TarjetaAdapter(tarjetas, R.layout.rv_tarjetas);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.8.102").addConverterFactory(GsonConverterFactory.create()).build();
        TarjetaInterface requestInterface = retrofit.create(TarjetaInterface.class);
        Call<JSONTarjeta> call = requestInterface.getJSON();
        call.enqueue(new Callback<JSONTarjeta>() {
            @Override
            public void onResponse(Call<JSONTarjeta> call, retrofit2.Response<JSONTarjeta> response) {
                JSONTarjeta jsonResponse = response.body();
                tarjetas = new ArrayList<>(Arrays.asList(jsonResponse.getTarjeta()));
                adapter = new TarjetaAdapter(tarjetas, R.layout.rv_tarjetas);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONTarjeta> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 2500);
            }
        });

        swipe.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Setting screen name: "+name);
        mTracker.setScreenName(name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    /*private List<Tarjeta> getAllAccounts() {
        return new ArrayList<Tarjeta>() {
            {
                add(new Tarjeta("Visa", "4634-0324-9240-4656", "S/ 4,731.84"));
                add(new Tarjeta("Visa", "4213-0300-9240-1010", "S/ 7,500.97"));
                add(new Tarjeta("Visa", "6576-0300-4534-1010", "S/ 4,500.00"));
                add(new Tarjeta("Visa", "4634-4353-9240-0967", "S/ 5,000.00"));
            }
        };
    }*/
}