package org.milaifontanals.racemanager.ui.resultats;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.databinding.FragmentResultatsBinding;
import org.milaifontanals.racemanager.ui.inscripcio.inscripcioFragment;

public class resultatsFragment extends Fragment {
    private FragmentResultatsBinding mBinding;

    private resultatsFragment thisFragment = this;

    // Dades obtenides del fragment anterior:
    public static final String CLAUCURSA = "param_cursa";
    public static final String CLAUCIRCUIT = "param_circuit";
    public static final String CLAUCATEGORIA = "param_categoria";
    public static final String CLAUCCC = "param_ccc";

    private String cursa_id;
    private String circuit_id;
    private String categoria_id;
    private String ccc_id;

    public resultatsFragment() {}


    public static resultatsFragment newInstance(String param1, String param2) {
        resultatsFragment fragment = new resultatsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            cursa_id = bundle.getString(CLAUCURSA);
            circuit_id = bundle.getString(CLAUCIRCUIT);
            categoria_id = bundle.getString(CLAUCATEGORIA);
            ccc_id = bundle.getString(CLAUCCC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentResultatsBinding.inflate(getLayoutInflater());

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Resultats de la cursa");
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavController nav = NavHostFragment.findNavController(thisFragment);
                        nav.navigate(R.id.action_resultatsFragment_to_navigation_home);
                    }
                });
            }
        }

        obtenirResultats() {

        }



        return mBinding.getRoot();
    }
}