package org.milaifontanals.racemanager.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.adapters.CursesAdapter;
import org.milaifontanals.racemanager.databinding.FragmentHomeBinding;
import org.milaifontanals.racemanager.modelsJson.Cursa;
import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CursesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtenir les dades de totes les curses

        int codi_cursa = 1;

        APIManager.getInstance().getCurses(codi_cursa, new Callback<ResponseGetCurses>() {
            @Override
            public void onResponse(Call<ResponseGetCurses> call, Response<ResponseGetCurses> response) {
                // Si no té cap problema en connectar-se amb el webservice entrará per aquí
                List<Cursa> curses = response.body().getCurses();

                // Obtenim la llista de curses i la mostrem amb el recyclerview
                adapter = new CursesAdapter(HomeFragment.this.getContext(), curses);
                binding.rcvCurses.setLayoutManager(new GridLayoutManager(HomeFragment.this.getContext(), 2, LinearLayoutManager.VERTICAL, false));
                binding.rcvCurses.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ResponseGetCurses> call, Throwable t) {
                // Si hi ha algun problema en connectar-se amb el webservice entrará per aquí
                Log.e("XXX", t.toString());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}