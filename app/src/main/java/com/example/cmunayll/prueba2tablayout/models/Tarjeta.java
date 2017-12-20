package com.example.cmunayll.prueba2tablayout.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cmunayll on 14/12/2017.
 */

public class Tarjeta {

    @SerializedName("description")
    private String tarjeta;
    @SerializedName("numberFormatted")
    private String numero;
    @SerializedName("amountAvailable")
    private Double hay;
    @SerializedName("limit")
    private Integer monto_total;

    public Tarjeta(String tarjeta, String numero, Double hay, Integer monto_total) {
        this.tarjeta = tarjeta;
        this.numero = numero;
        this.hay = hay;
        this.monto_total = monto_total;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public String getNumero() {
        return numero;
    }

    public Double getHay() {
        return hay;
    }

    public Integer getMonto_total() {
        return monto_total;
    }

}
