package org.milaifontanals.racemanager.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultatsAdapter extends RecyclerView.Adapter<ResultatsAdapter.GridViewHolder>{
    @NonNull
    @Override
    public ResultatsAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultatsAdapter.GridViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
