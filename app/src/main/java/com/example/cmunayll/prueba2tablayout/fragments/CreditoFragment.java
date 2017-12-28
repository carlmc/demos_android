package com.example.cmunayll.prueba2tablayout.fragments;

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
import com.example.cmunayll.prueba2tablayout.adapters.CreditoAdapter;
import com.example.cmunayll.prueba2tablayout.adapters.TarjetaAdapter;
import com.example.cmunayll.prueba2tablayout.models.Credito;
import com.example.cmunayll.prueba2tablayout.models.Tarjeta;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class CreditoFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipe;
    private List<Credito> creditos;
    private CreditoAdapter adapter;

    private Tracker mTracker;
    private static final String TAG = CreditoFragment.class.getSimpleName();
    String name = new String("Fragment Credito");

    public CreditoFragment() {

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

        RequestQueue requestQueue = Volley.newRequestQueue(container.getContext());
        String url = "http://192.168.8.102/Volley/CredproductList.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                creditos = Arrays.asList(mGson.fromJson(response, Credito[].class));
                adapter = new CreditoAdapter(creditos, R.layout.rv_creditos);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

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


    /*private List<Credito> getAllAccounts() {
        return new ArrayList<Credito>() {
            {
                add(new Credito("Visa", "4634-0324-9240-4656", "S/ 2,465.58"));
                add(new Credito("Visa", "4213-4568-9240-1010", "S/ 4,500.00"));
                add(new Credito("Visa", "6576-0300-4534-1010", "S/ 6,258.32"));
                add(new Credito("Visa", "4634-4353-7895-0967", "S/ 300.35"));
            }
        };
    }*/
}
