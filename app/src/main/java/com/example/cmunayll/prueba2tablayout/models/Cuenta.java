package com.example.cmunayll.prueba2tablayout.models;



import com.google.gson.annotations.SerializedName;


/**
 * Created by cmunayll on 14/12/2017.
 */

public class Cuenta {

    @SerializedName("description")
    private String tipo_cuenta;
    @SerializedName("numberFormatted")
    private String num_cuenta;
    @SerializedName("amount")
    private Double cantidad;

    public Cuenta(String tipo_cuenta, String num_cuenta, Double cantidad) {
        this.tipo_cuenta = tipo_cuenta;
        this.num_cuenta = num_cuenta;
        this.cantidad = cantidad;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public Double getCantidad() {
        return cantidad;
    }

}
