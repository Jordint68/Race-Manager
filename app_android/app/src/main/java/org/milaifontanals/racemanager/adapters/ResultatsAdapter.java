package org.milaifontanals.racemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.modelsAuxiliars.ModelMillorResultatParticipant;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsInscripcion;
import org.milaifontanals.racemanager.utils.Utils;

import java.util.List;

public class ResultatsAdapter extends RecyclerView.Adapter<ResultatsAdapter.GridViewHolder>{
    private List<ModelMillorResultatParticipant> lRes;
    private Context mContext;

    public ResultatsAdapter(List<ModelMillorResultatParticipant> lRes, Context c) {
        this.lRes = lRes;
        this.mContext = c;
    }

    @NonNull
    @Override
    public ResultatsAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_individual_resultats, parent, false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultatsAdapter.GridViewHolder holder, int position) {
        ModelMillorResultatParticipant resActual = lRes.get(position);
        holder.txvNom.setText(resActual.getNom());
        holder.txvCheckpoint.setText("Checkpoint: " + resActual.getCheckpoint() + "");
        holder.txvDorsal.setText("Dorsal: " + resActual.getDorsal());
        holder.txvPosicio.setText(position + 1 + "- .");
        holder.txvTemps.setText("Temps: " + extreureHora(resActual.getTemps()));
        holder.txvKms.setText(resActual.getKmsCheckpoint() + " kms");
    }

    @Override
    public int getItemCount() {
        return lRes.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView txvPosicio;
        TextView txvNom;
        TextView txvDorsal;
        TextView txvTemps;
        TextView txvCheckpoint;
        TextView txvKms;
        public GridViewHolder(@NonNull View vista) {
            super(vista);
            txvPosicio = vista.findViewById(R.id.txvPosicio);
            txvNom = vista.findViewById(R.id.txvNom);
            txvDorsal = vista.findViewById(R.id.txvDorsal);
            txvTemps = vista.findViewById(R.id.txvTemps);
            txvCheckpoint = vista.findViewById(R.id.txvCheckpoint);
            txvKms = vista.findViewById(R.id.txvKms);
        }
    }

    public static String extreureHora(String fechaCompleta) {
        if (fechaCompleta == null || fechaCompleta.length() != 19) {
            throw new IllegalArgumentException("La fecha completa debe tener el formato 'yyyy-MM-dd HH-mm-ss'");
        }

        return fechaCompleta.substring(11);
    }
}
