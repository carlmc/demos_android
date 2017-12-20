package com.example.cmunayll.prueba2tablayout.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cmunayll on 14/12/2017.
 */

public class Credito {

    @SerializedName("description")
    private String tarjetaCred;
    @SerializedName("numberFormatted")
    private String numeroCred;
    @SerializedName("amountAvailable")
    private Double hayCred;
    @SerializedName("limit")
    private Integer monto_totalCred;

    public Credito(String tarjetaCred, String numeroCred, Integer monto_totalCred) {
        this.tarjetaCred = tarjetaCred;
        this.numeroCred = numeroCred;
        this.monto_totalCred = monto_totalCred;
    }

    public String getTarjetaCred() {
        return tarjetaCred;
    }

    public String getNumeroCred() {
        return numeroCred;
    }

    public Double getHayCred() {
        return hayCred;
    }

    public Integer getMonto_totalCred() {
        return monto_totalCred;
    }

}
