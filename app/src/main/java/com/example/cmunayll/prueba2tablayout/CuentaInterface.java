package com.example.cmunayll.prueba2tablayout;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cmunayll on 22/12/2017.
 */

public interface CuentaInterface {
    @GET("Volley/AccountList.php")
    Call<JSONResponse> getJSON();
}
