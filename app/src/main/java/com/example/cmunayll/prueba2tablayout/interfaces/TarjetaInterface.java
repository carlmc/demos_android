package com.example.cmunayll.prueba2tablayout.interfaces;

import com.example.cmunayll.prueba2tablayout.jsons.JSONTarjeta;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cmunayll on 27/12/2017.
 */

public interface TarjetaInterface {
    @GET("retrofit/CardList.php")
    Call<JSONTarjeta> getJSON();
}
