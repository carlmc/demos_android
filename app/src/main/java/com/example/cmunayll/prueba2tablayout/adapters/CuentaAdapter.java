package com.example.cmunayll.prueba2tablayout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cmunayll.prueba2tablayout.R;
import com.example.cmunayll.prueba2tablayout.models.Cuenta;

import java.util.List;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class CuentaAdapter extends RecyclerView.Adapter<CuentaAdapter.ViewHolder> {

    private List<Cuenta> cuentas;
    private int layout;

    public CuentaAdapter(List<Cuenta> cuentas, int layout) {
        this.cuentas = cuentas;
        this.layout = layout;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cuentas.get(position));
    }

    public void clear() {
        cuentas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (cuentas != null ? cuentas.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTipoCuenta;
        public TextView tvNumCuenta;
        public TextView tvMonto;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTipoCuenta = itemView.findViewById(R.id.tvTipo);
            tvNumCuenta = itemView.findViewById(R.id.tvNum);
            tvMonto = itemView.findViewById(R.id.Monto);
        }

        public void bind(final Cuenta cuenta) {
            this.tvTipoCuenta.setText(cuenta.getTipo_cuenta());
            this.tvNumCuenta.setText(cuenta.getNum_cuenta());
            this.tvMonto.setText("S/ " + String.valueOf(cuenta.getCantidad()));
        }
    }
}
