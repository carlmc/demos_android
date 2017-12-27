package com.example.cmunayll.prueba2tablayout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cmunayll.prueba2tablayout.R;
import com.example.cmunayll.prueba2tablayout.models.Tarjeta;

import java.util.List;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class TarjetaAdapter extends RecyclerView.Adapter<TarjetaAdapter.ViewHolder> {

    private List<Tarjeta> tarjetas;
    private int layout;

    public TarjetaAdapter(List<Tarjeta> tarjetas, int layout) {
        this.tarjetas = tarjetas;
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
        holder.bind(tarjetas.get(position));
    }


    @Override
    public int getItemCount() {
        return (null != tarjetas ? tarjetas.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTarjeta;
        public TextView tvNumero;
        public TextView tvHay;
        public TextView tvMontoTotal;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTarjeta = itemView.findViewById(R.id.tvTarjeta);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvHay = itemView.findViewById(R.id.tvHay);
            tvMontoTotal = itemView.findViewById(R.id.MontoTotal);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        public void bind(final Tarjeta tarjeta) {
            this.tvTarjeta.setText(tarjeta.getDescription());
            this.tvNumero.setText(tarjeta.getNumberFormatted());
            this.tvHay.setText("S/ "+String.valueOf(tarjeta.getAmountAvailable()));
            this.tvMontoTotal.setText("S/ "+String.valueOf(tarjeta.getLimit()));
            this.progressBar.setMax(tarjeta.getLimit());
            this.progressBar.setProgress(tarjeta.getAmountAvailable().intValue());


        }
    }
}
