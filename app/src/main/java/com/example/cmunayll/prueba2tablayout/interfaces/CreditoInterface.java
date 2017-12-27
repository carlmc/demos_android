package com.example.cmunayll.prueba2tablayout.interfaces;

import com.example.cmunayll.prueba2tablayout.jsons.JSONCredito;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cmunayll on 27/12/2017.
 */

public interface CreditoInterface {
    @GET("retrofit/CredproductList.php")
    Call<JSONCredito> getJSON();
}
