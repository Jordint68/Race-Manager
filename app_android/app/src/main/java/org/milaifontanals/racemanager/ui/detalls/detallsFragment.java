package org.milaifontanals.racemanager.ui.detalls;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.modelsJson.Cursa;

public class detallsFragment extends Fragment {

    private static final String CURSA = "cursa";

    private Cursa cursa;

    public detallsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static detallsFragment newInstance(Cursa c) {
        detallsFragment fragment = new detallsFragment();
        Bundle args = new Bundle();
        args.putSerializable(CURSA, c);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cursa = (Cursa) getArguments().getSerializable(CURSA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalls, container, false);
    }
}