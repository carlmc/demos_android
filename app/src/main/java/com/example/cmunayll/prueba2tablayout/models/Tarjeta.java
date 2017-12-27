package com.example.cmunayll.prueba2tablayout.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cmunayll on 14/12/2017.
 */

public class Tarjeta {

    //@SerializedName("description")
    private String description;
    //@SerializedName("numberFormatted")
    private String numberFormatted;
    //@SerializedName("amountAvailable")
    private Double amountAvailable;
    //@SerializedName("limit")
    private Integer limit;

    public Tarjeta(String tarjeta, String numero, Double hay, Integer monto_total) {
        this.description = tarjeta;
        this.numberFormatted = numero;
        this.amountAvailable = hay;
        this.limit = monto_total;
    }

    public String getDescription() {
        return description;
    }

    public String getNumberFormatted() {
        return numberFormatted;
    }

    public Double getAmountAvailable() {
        return amountAvailable;
    }

    public Integer getLimit() {
        return limit;
    }
}
