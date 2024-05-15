package org.milaifontanals.racemanager.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.modelsJson.Circuit;
import org.milaifontanals.racemanager.selectedListeners.ICircuitSelectedListener;
import org.milaifontanals.racemanager.ui.infoCursa.infoCursaFragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CircuitsAdapter extends RecyclerView.Adapter<CircuitsAdapter.GridViewHolder> {
    private List<Circuit> lCircuits = new ArrayList<>();
    private Context mContext;
    private ICircuitSelectedListener mCircuitListener;

    private int idxCircuitSeleccionat = -1;

    public CircuitsAdapter(Context c, List<Circuit> lc) {
        this.mContext = c;
        lCircuits = lc;

//        if ((c instanceof ICircuitSelectedListener) == false) {
//            throw new RuntimeException("El context no és un ICircuitSelectedListener");
//        }
//        mCircuitListener = (ICircuitSelectedListener)c;
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

        String s_data_incorrecte  = circuitActual.getTemps();
        String s_data = s_data_incorrecte;

        DateTimeFormatter dtf;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            try {
                LocalDateTime dataAmbHora = LocalDateTime.parse(s_data_incorrecte, dtf);
                DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                s_data = dataAmbHora.format(dtfHora);
            } catch (DateTimeParseException ex) {
            }
        }

        holder.txvNum.setText(circuitActual.getNum().toString());
        holder.txvNom.setText(circuitActual.getNom().toString());
        holder.txvDistancia.setText(circuitActual.getDistancia() + "");
        holder.txvTemps.setText(s_data);
        holder.txvPreu.setText(circuitActual.getPreu().toString());

        holder.itemView.setOnClickListener(view -> {
            int posicioAnterior = this.idxCircuitSeleccionat;
            this.idxCircuitSeleccionat = holder.getAdapterPosition();
            this.notifyItemChanged(idxCircuitSeleccionat);
            this.notifyItemChanged(posicioAnterior);

            infoCursaFragment icf = new infoCursaFragment();
            icf.onCircuitSelected(lCircuits.get(this.idxCircuitSeleccionat));
        });

        if (position == idxCircuitSeleccionat) {
            holder.clyCircuit.setBackgroundColor(Color.parseColor("#d98080"));
        } else {
            holder.clyCircuit.setBackgroundTintList(null);
        }
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

        ConstraintLayout clyCircuit;

        public GridViewHolder(@NonNull View vista) {
            super(vista);
            txvNum = vista.findViewById(R.id.txvNum);
            txvNom = vista.findViewById(R.id.txvNom);
            txvDistancia = vista.findViewById(R.id.txvDistancia);
            txvTemps = vista.findViewById(R.id.txvTemps);
            txvPreu = vista.findViewById(R.id.txvPreu);
            clyCircuit = vista.findViewById(R.id.clyCircuit);
        }
    }
}
