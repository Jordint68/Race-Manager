package org.milaifontanals.racemanager.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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


        // Omplir dades del spinner
        List<String> tipusItems = new ArrayList<>();
        tipusItems.add("Nom");
        tipusItems.add("Esport");
        tipusItems.add("Data");
        tipusItems.add("Lloc");

        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, tipusItems);

        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spnTipus.setAdapter(spnAdapter);


        // Gestionar el spinner

//        binding.spnTipus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//                if(selectedItem.equals("Data")) {
////                    binding.edtCerca.setVisibility(View.INVISIBLE);
//                } else {
////                    binding.edtCerca.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        // Obtenir les dades de totes les curses
        int codiCursa = 1;

        omplirRecyclerCurses(0, null);

//        binding.imbCerca.setOnClickListener(view -> {
////            String filtreNom = binding.edtCerca.getQuery().toString();
//
//            omplirRecyclerCurses(0, filtreNom);
//        });



        // Gestionar el filtre

        binding.edtCerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String filtreNom = binding.edtCerca.getQuery().toString();
                binding.edtCerca.clearFocus();
                omplirRecyclerCurses(0, filtreNom);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")) {
                    omplirRecyclerCurses(0, null);
                }
                return false;
            }
        });


        return root;
    }

    private void omplirRecyclerCurses(int codiCursa, String filtre) {

        APIManager.getInstance().getCurses(codiCursa, new Callback<ResponseGetCurses>() {
            @Override
            public void onResponse(Call<ResponseGetCurses> call, Response<ResponseGetCurses> response) {
                // Si no té cap problema en connectar-se amb el webservice entrará per aquí
                List<Cursa> curses = response.body().getCurses();

                // Filtre pel nom
                if (filtre != null) {
                    if (!(filtre.equals(""))) {
                        switch (binding.spnTipus.getSelectedItem().toString()) {
                            case "Nom":
                                curses = filtreCursesPerNom(curses, filtre);
                                break;
                            case "Esport":
                                curses = filtreCursesPerEsport(curses, filtre);
                                break;
                            case "Lloc":
                                curses = filtreCursesPerLloc(curses, filtre);
                                break;
                            case "Data":

                                break;
                        }




                        binding.edtCerca.setQueryHint(filtre);
                    } else {
                        binding.edtCerca.setQueryHint("Introdueix el títol de la cursa");
                    }
                } else {
                    binding.edtCerca.setQueryHint("Introdueix el títol de la cursa");
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

    private List<Cursa> filtreCursesPerEsport(List<Cursa> lCursa, String filtre) {
        List<Cursa> aux = new ArrayList<>();

        for(Cursa c : lCursa) {
            if (c.getEsport().getNom().toString().toLowerCase().contains(filtre.toLowerCase())) {
                aux.add(c);
            }
        }
        return aux;
    }

    private List<Cursa> filtreCursesPerLloc(List<Cursa> lCursa, String filtre) {
        List<Cursa> aux = new ArrayList<>();

        for(Cursa c : lCursa) {
            if (c.getLloc().toLowerCase().contains(filtre.toLowerCase())) {
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