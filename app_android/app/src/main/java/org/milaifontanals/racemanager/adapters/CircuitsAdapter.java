package org.milaifontanals.racemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.modelsJson.Circuit;

import java.util.ArrayList;
import java.util.List;

public class CircuitsAdapter extends RecyclerView.Adapter<CircuitsAdapter.GridViewHolder> {
    List<Circuit> lCircuits = new ArrayList<>();

    Context mContext;

    public CircuitsAdapter(Context c, List<Circuit> lc) {
        this.mContext = c;
        lCircuits = lc;
    }

    @NonNull
    @Override
    public CircuitsAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_individual_circuit, parent, false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CircuitsAdapter.GridViewHolder holder, int position) {
        Circuit circuitActual = lCircuits.get(position);
        holder.txvNum.setText(circuitActual.getNum().toString());
        holder.txvNom.setText(circuitActual.getNom().toString());
        holder.txvDistancia.setText(circuitActual.getDistancia() + "");
        holder.txvTemps.setText(circuitActual.getTemps().toString());
        holder.txvPreu.setText(circuitActual.getPreu().toString());
    }

    @Override
    public int getItemCount() {
        return  lCircuits.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView txvNum;
        TextView txvNom;
        TextView txvDistancia;
        TextView txvTemps;
        TextView txvPreu;
        public GridViewHolder(@NonNull View vista) {
            super(vista);
            txvNum = vista.findViewById(R.id.txvNum);
            txvNom = vista.findViewById(R.id.txvNom);
            txvDistancia = vista.findViewById(R.id.txvDistancia);
            txvTemps = vista.findViewById(R.id.txvTemps);
            txvPreu = vista.findViewById(R.id.txvPreu);
        }
    }
}
