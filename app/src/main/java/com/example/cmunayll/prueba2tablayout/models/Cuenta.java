package com.example.cmunayll.prueba2tablayout.models;



import com.google.gson.annotations.SerializedName;


/**
 * Created by cmunayll on 14/12/2017.
 */

public class Cuenta {

    //@SerializedName("description")
    private String description;
    //@SerializedName("numberFormatted")
    private String numberFormatted;
    //@SerializedName("amount")
    private Double amount;

    public Cuenta(String tipo_cuenta, String num_cuenta, Double cantidad) {
        this.description = tipo_cuenta;
        this.numberFormatted = num_cuenta;
        this.amount = cantidad;
    }

    public String getDescription() {
        return description;
    }

    public String getNumberFormatted() {
        return numberFormatted;
    }

    public Double getAmount() {
        return amount;
    }

}
