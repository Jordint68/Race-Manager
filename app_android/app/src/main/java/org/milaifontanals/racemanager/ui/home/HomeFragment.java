package org.milaifontanals.racemanager.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        binding.imbCerca.setBackgroundColor(Color.TRANSPARENT);

        // Obtenir les dades de totes les curses
        int codiCursa = 1;

        omplirRecyclerCurses(0, null);

        binding.imbCerca.setOnClickListener(view -> {
            String filtreNom = binding.edtCerca.getText().toString();

            omplirRecyclerCurses(0, filtreNom);
        });

        binding.edtCerca.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    String filtreNom = binding.edtCerca.getText().toString();
                    binding.edtCerca.clearFocus();
                    omplirRecyclerCurses(0, filtreNom);
                    return true;
                }
                return false;
            }
        });

        return root;
    }

    private void omplirRecyclerCurses(int codiCursa, String filtreNom) {

        APIManager.getInstance().getCurses(codiCursa, new Callback<ResponseGetCurses>() {
            @Override
            public void onResponse(Call<ResponseGetCurses> call, Response<ResponseGetCurses> response) {
                // Si no té cap problema en connectar-se amb el webservice entrará per aquí
                List<Cursa> curses = response.body().getCurses();

                // Filtre pel nom
                if (filtreNom != null) {
                    if (!(filtreNom.equals(""))) {
                        curses = filtreCursesPerNom(curses, filtreNom);
                        binding.edtCerca.setText("");
                        binding.edtCerca.setHint(filtreNom);
                    } else {
                        binding.edtCerca.setHint("Introdueix el títol de la cursa");
                    }
                } else {
                    binding.edtCerca.setHint("Introdueix el títol de la cursa");
                }

                // Ordenar les curses
                curses = ordenarPerData(curses);

                // Obtenim la llista de curses i la mostrem amb el recyclerview
                adapter = new CursesAdapter(HomeFragment.this.getContext(), curses, HomeFragment.this);
                binding.rcvCurses.setLayoutManager(new GridLayoutManager(HomeFragment.this.getContext(), 2, LinearLayoutManager.VERTICAL, false));
                binding.rcvCurses.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ResponseGetCurses> call, Throwable t) {
                // Si hi ha algun problema en connectar-se amb el webservice entrará per aquí
                Log.e("XXX", t.toString());
            }
        });
    }

    private List<Cursa> ordenarPerData(List<Cursa> curses) {
        Comparator<Cursa> comparaDates = new Comparator<Cursa>() {
            @Override
            public int compare(Cursa c1, Cursa c2) {
                return c1.getDataInici().compareTo(c2.getDataInici());
            }
        };

        Collections.sort(curses, comparaDates);

        return curses;
    }

    private List<Cursa> filtreCursesPerNom(List<Cursa> lCursa, String filtre) {
        List<Cursa> aux = new ArrayList<>();

        for(Cursa c : lCursa) {
            if (c.getNom().toLowerCase().contains(filtre.toLowerCase())) {
                aux.add(c);
            }
        }
        return aux;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}