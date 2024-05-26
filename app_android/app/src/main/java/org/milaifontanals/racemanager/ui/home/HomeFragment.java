package org.milaifontanals.racemanager.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.milaifontanals.racemanager.API.APIManager;
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
    private static final String ERROR = "ERROR";

    private FragmentHomeBinding binding;
    private CursesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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


        // Obtenir les dades de totes les curses
        int codiCursa = 0;

        omplirListCurses(0, null);


        // Gestionar el filtre

        binding.edtCerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String filtreNom = binding.edtCerca.getQuery().toString();
                binding.edtCerca.clearFocus();
                omplirListCurses(0, filtreNom);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")) {
                    omplirListCurses(0, null);
                }
                return false;
            }
        });


        return root;
    }


    private void filtrarCurses(List<Cursa> curses, String filtre) {
        if(curses.size() != 0) {
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
    }

    private void omplirListCurses(int codiCursa, String filtre) {
        List<Cursa> llistaCurses = new ArrayList<>();

        if(codiCursa == 0) {
            APIManager.getInstance().getAllCurses(new Callback<ResponseGetCurses>() {
                @Override
                public void onResponse(Call<ResponseGetCurses> call, Response<ResponseGetCurses> response) {
                    List<Cursa> lCurses = response.body().getCurses();

                    // Filtre
                    filtrarCurses(lCurses, filtre);
                }

                @Override
                public void onFailure(Call<ResponseGetCurses> call, Throwable t) {
                    Log.e(ERROR, "GetAllCurses - ", t);
                }
            });
        } else {
            APIManager.getInstance().getCurses(codiCursa, new Callback<ResponseGetCurses>() {
                @Override
                public void onResponse(Call<ResponseGetCurses> call, Response<ResponseGetCurses> response) {
                    List<Cursa> lCurses = response.body().getCurses();

                    filtrarCurses(lCurses, filtre);
                }

                @Override
                public void onFailure(Call<ResponseGetCurses> call, Throwable t) {
                    Log.d(ERROR, "GetCurses - " + t.toString());
                }
            });
        }


    }

    private List<Cursa> ordenarPerData(List<Cursa> curses) {
        Comparator<Cursa> comparaDates = new Comparator<Cursa>() {
            @Override
            public int compare(Cursa c1, Cursa c2) {
                return c1.getCurDataInici().compareTo(c2.getCurDataInici());
            }
        };

        Collections.sort(curses, comparaDates);

        return curses;
    }

    private List<Cursa> filtreCursesPerNom(List<Cursa> lCursa, String filtre) {
        List<Cursa> aux = new ArrayList<>();

        for(Cursa c : lCursa) {
            if (c.getCurNom().toLowerCase().contains(filtre.toLowerCase())) {
                aux.add(c);
            }
        }
        return aux;
    }

    private List<Cursa> filtreCursesPerEsport(List<Cursa> lCursa, String filtre) {
        List<Cursa> aux = new ArrayList<>();

        for(Cursa c : lCursa) {
            if (c.getEsport().getEspNom().toString().toLowerCase().contains(filtre.toLowerCase())) {
                aux.add(c);
            }
        }
        return aux;
    }

    private List<Cursa> filtreCursesPerLloc(List<Cursa> lCursa, String filtre) {
        List<Cursa> aux = new ArrayList<>();

        for(Cursa c : lCursa) {
            if (c.getCurLloc().toLowerCase().contains(filtre.toLowerCase())) {
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