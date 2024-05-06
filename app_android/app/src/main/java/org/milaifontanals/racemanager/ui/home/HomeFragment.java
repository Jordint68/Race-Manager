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

import com.google.gson.Gson;

import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.databinding.FragmentHomeBinding;
import org.milaifontanals.racemanager.modelsJson.Cursa;
import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

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
                Gson gson = new Gson();
                String json = gson.toJson(curses);

                Log.d("XXX", json);
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