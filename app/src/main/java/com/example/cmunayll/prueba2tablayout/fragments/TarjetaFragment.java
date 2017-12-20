package com.example.cmunayll.prueba2tablayout.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cmunayll.prueba2tablayout.R;
import com.example.cmunayll.prueba2tablayout.adapters.TarjetaAdapter;
import com.example.cmunayll.prueba2tablayout.models.Tarjeta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class TarjetaFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipe;
    private List<Tarjeta> tarjetas;
    private TarjetaAdapter adapter;

    public TarjetaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recyclerview_fragment, container, false);
        //tarjetas = this.getAllAccounts();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        //adapter = new TarjetaAdapter(tarjetas, R.layout.rv_tarjetas);

        swipe = view.findViewById(R.id.swiperefresh);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(container.getContext());
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
                }, 3000);
            }
        });

        swipe.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        return view;
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