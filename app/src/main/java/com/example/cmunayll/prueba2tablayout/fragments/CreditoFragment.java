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

import com.example.cmunayll.prueba2tablayout.AnalyticsApplication;
import com.example.cmunayll.prueba2tablayout.R;
import com.example.cmunayll.prueba2tablayout.adapters.CreditoAdapter;

import com.example.cmunayll.prueba2tablayout.interfaces.CreditoInterface;
import com.example.cmunayll.prueba2tablayout.jsons.JSONCredito;
import com.example.cmunayll.prueba2tablayout.models.Credito;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class CreditoFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipe;
    private ArrayList<Credito> creditos;
    private CreditoAdapter adapter;

    private Tracker mTracker;
    private static final String TAG = CreditoFragment.class.getSimpleName();
    String name = "Fragment Credito";

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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.8.102").addConverterFactory(GsonConverterFactory.create()).build();
        CreditoInterface requestInterface = retrofit.create(CreditoInterface.class);
        Call<JSONCredito> call = requestInterface.getJSON();
        call.enqueue(new Callback<JSONCredito>() {
            @Override
            public void onResponse(Call<JSONCredito> call, retrofit2.Response<JSONCredito> response) {
                JSONCredito jsonResponse = response.body();
                creditos = new ArrayList<>(Arrays.asList(jsonResponse.getCredito()));
                adapter = new CreditoAdapter(creditos, R.layout.rv_creditos);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONCredito> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 1500);
                mTracker.send(new HitBuilders.EventBuilder().setCategory("Action").setAction("Swipe").build());
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
