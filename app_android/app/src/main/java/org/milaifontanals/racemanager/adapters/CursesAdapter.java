package org.milaifontanals.racemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.modelsJson.Cursa;
import org.milaifontanals.racemanager.ui.home.HomeFragment;
import org.milaifontanals.racemanager.ui.home.HomeFragmentDirections;

import java.util.List;

public class CursesAdapter extends RecyclerView.Adapter<CursesAdapter.GridViewHolder> {
    private Fragment fragHome;
    private List<Cursa> lCurses;
    private Context mContext;

    public CursesAdapter(Context c, List<Cursa> curses, Fragment frag) {
        this.lCurses = curses;
        this.mContext = c;
        this.fragHome = frag;
    }

    @NonNull
    @Override
    public CursesAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_individual_cursa, parent, false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CursesAdapter.GridViewHolder holder, int position) {
        Cursa cursaActual = lCurses.get(position);
        holder.txvNom.setText(cursaActual.getNom());
        holder.txvLloc.setText(cursaActual.getLloc());
        holder.txvData.setText(cursaActual.getDataInici() + " / " + cursaActual.getDataFi());
        holder.txvWebsite.setText(cursaActual.getWeb());
        String imgUrl = cursaActual.getFoto().toString();
        ImageLoader.getInstance().displayImage(imgUrl, holder.imvFoto);

        holder.btnMostrarDetalls.setOnClickListener(view -> {
            NavController nav = NavHostFragment.findNavController(fragHome);
            NavDirections n = HomeFragmentDirections.actionNavigationHomeToDetallsFragment2(cursaActual);
            nav.navigate(R.id.action_navigation_home_to_detallsFragment2);
        });
    }

    @Override
    public int getItemCount() {
        return lCurses.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imvFoto;
        TextView txvNom;
        TextView txvData;
        TextView txvLloc;
        TextView txvWebsite;
        Button btnMostrarDetalls;

        public GridViewHolder(@NonNull View vista) {
            super(vista);
            imvFoto = vista.findViewById(R.id.imvFoto);
            txvNom = vista.findViewById(R.id.txvNom);
            txvData = vista.findViewById(R.id.txvData);
            txvLloc = vista.findViewById(R.id.txvLloc);
            txvWebsite = vista.findViewById(R.id.txvWebsite);
            btnMostrarDetalls = vista.findViewById(R.id.btnMostrarDetalls);
        }
    }


}
