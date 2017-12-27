package com.example.cmunayll.prueba2tablayout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cmunayll.prueba2tablayout.R;
import com.example.cmunayll.prueba2tablayout.models.Credito;

import java.util.List;

/**
 * Created by cmunayll on 13/12/2017.
 */

public class CreditoAdapter extends RecyclerView.Adapter<CreditoAdapter.ViewHolder> {

    private List<Credito> creditos;
    private int layout;

    public CreditoAdapter(List<Credito> creditos, int layout) {
        this.creditos = creditos;
        this.layout = layout;
    }

    @Override
    public CreditoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        CreditoAdapter.ViewHolder vh = new CreditoAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CreditoAdapter.ViewHolder holder, int position) {
        holder.bind(creditos.get(position));
    }


    @Override
    public int getItemCount() {
        return (null != creditos ? creditos.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTarjeta;
        public TextView tvNumero;
        public TextView tvHayCred;
        public TextView tvMontoTotal;
        public ProgressBar progressBarCred;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTarjeta = itemView.findViewById(R.id.tvTarjetaCred);
            tvNumero = itemView.findViewById(R.id.tvNumeroCred);
            tvHayCred = itemView.findViewById(R.id.tvHayCred);
            tvMontoTotal = itemView.findViewById(R.id.MontoTotalCred);
            progressBarCred = itemView.findViewById(R.id.progressBarCred);
        }

        public void bind(final Credito credito) {
            this.tvTarjeta.setText(credito.getDescription());
            this.tvNumero.setText(credito.getNumberFormatted());
            this.tvHayCred.setText("Has Pagado S/ "+String.valueOf(credito.getAmountAvailable()));
            this.tvMontoTotal.setText("S/ "+String.valueOf(credito.getLimit()));
            this.progressBarCred.setMax(credito.getLimit());
            this.progressBarCred.setProgress(credito.getAmountAvailable().intValue());
        }
    }
}
