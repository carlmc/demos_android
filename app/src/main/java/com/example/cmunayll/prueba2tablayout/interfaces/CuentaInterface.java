package com.example.cmunayll.prueba2tablayout.interfaces;

import com.example.cmunayll.prueba2tablayout.jsons.JSONCuenta;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cmunayll on 22/12/2017.
 */

public interface CuentaInterface {
    @GET("retrofit/AccountList.php")
    Call<JSONCuenta> getJSON();
}
