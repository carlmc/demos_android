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

import com.example.cmunayll.prueba2tablayout.interfaces.CuentaInterface;
import com.example.cmunayll.prueba2tablayout.jsons.JSONCuenta;
import com.example.cmunayll.prueba2tablayout.R;
import com.example.cmunayll.prueba2tablayout.adapters.CuentaAdapter;
import com.example.cmunayll.prueba2tablayout.models.Cuenta;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class CuentaFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipe;
    private ArrayList<Cuenta> cuentas;
    private CuentaAdapter adapter;

    public CuentaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recyclerview_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());

        swipe = view.findViewById(R.id.swiperefresh);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        /*RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = "http://192.168.8.102/Volley/AccountList.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                cuentas = Arrays.asList(mGson.fromJson(response, Cuenta[].class));
                adapter = new CuentaAdapter(cuentas, R.layout.rv_cuentas);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.8.102").addConverterFactory(GsonConverterFactory.create()).build();
        CuentaInterface requestInterface = retrofit.create(CuentaInterface.class);
        Call<JSONCuenta> call = requestInterface.getJSON();
        call.enqueue(new Callback<JSONCuenta>() {
            @Override
            public void onResponse(Call<JSONCuenta> call, retrofit2.Response<JSONCuenta> response) {
                JSONCuenta jsonResponse = response.body();
                cuentas = new ArrayList<>(Arrays.asList(jsonResponse.getCuenta()));
                adapter = new CuentaAdapter(cuentas, R.layout.rv_cuentas);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONCuenta> call, Throwable t) {
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
                }, 3000);
            }
        });

        swipe.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light);

        //cuentas = this.getAllAccounts();

        return view;
    }


    /*private List<Cuenta> getAllAccounts() {
        return new ArrayList<Cuenta>() {{
            add(new Cuenta("Cuentas de Ahorro","193-11295209-1-01",4731.84456654));
            add(new Cuenta("Cuentas de Ahorro","193-11295454209-1-01",2163.97));
            add(new Cuenta("Deposito A Plazo","193-11295209-1-01",2163.99));
            //add(new Cuenta("Cuenta Mm","193-1145454545295209-1-01",500045465.97));
            //add(new Cuenta("Deposito A Plazo","193-11295209-1-01",8856.99));
            //add(new Cuenta("Cuentas de Ahorro","193-11232209-1-01",7000.97));
            //add(new Cuenta("Deposito A Plazo","193-11295209-1-01",8000.99));
        }};
    }*/
}
