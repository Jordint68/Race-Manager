package org.milaifontanals.racemanager.ui.inscripcio;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.databinding.FragmentInscripcioBinding;
import org.milaifontanals.racemanager.models.Participant;

import java.util.Calendar;
import java.util.Date;

public class inscripcioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String CLAUCURSA = "param_cursa";
    public static final String CLAUCIRCUIT = "param_circuit";
    public static final String CLAUCATEGORIA = "param_categoria";


    private FragmentInscripcioBinding mBinding;


    private String cursa_id;
    private String circuit_id;
    private String categoria_id;


    private Participant participant = new Participant();

    public inscripcioFragment() {}

    public static inscripcioFragment newInstance(String param1, String param2) {
        inscripcioFragment fragment = new inscripcioFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentInscripcioBinding.inflate(getLayoutInflater());


        mBinding.btnDataNaix.setOnClickListener(view -> {
            openDatePicker();
        });

        mBinding.btnEnviar.setOnClickListener(view -> {

            // La feina ja està feta
            NavController nav = NavHostFragment.findNavController(this);
            nav.navigate(R.id.action_inscripcioFragment_to_navigation_home);
        });


        return mBinding.getRoot();
    }

    private void openDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);

                        Date selectedDate = selectedCalendar.getTime();
                        participant.setData_naix(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }
}