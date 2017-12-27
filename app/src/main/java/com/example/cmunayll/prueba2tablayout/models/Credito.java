package com.example.cmunayll.prueba2tablayout.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cmunayll on 14/12/2017.
 */

public class Credito {

    //@SerializedName("description")
    private String description;
    //@SerializedName("numberFormatted")
    private String numberFormatted;
    //@SerializedName("amountAvailable")
    private Double amountAvailable;
    //@SerializedName("limit")
    private Integer limit;

    public Credito(String tarjetaCred, String numeroCred, Integer monto_totalCred) {
        this.description = tarjetaCred;
        this.numberFormatted = numeroCred;
        this.limit = monto_totalCred;
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
