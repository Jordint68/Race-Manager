package org.milaifontanals.racemanager.ui.inscripcio;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.databinding.FragmentInscripcioBinding;
import org.milaifontanals.racemanager.models.Participant;
import org.milaifontanals.racemanager.utils.Utils;

import java.util.Calendar;
import java.util.Date;

public class inscripcioFragment extends Fragment {
    private Utils utils = new Utils();

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
            boolean error = false;

            // Obtenir les dades del participant
            String NIF = mBinding.edtNIF.getText().toString();
            if(!validaNIF(NIF)) {
                error = true;
                mBinding.warnNIF.setVisibility(View.VISIBLE);
            } else mBinding.warnNIF.setVisibility(View.INVISIBLE);

            String telf = mBinding.edtTelefon.getText().toString();
            if (telf == null || telf.length() != 9) {
                error = true;
                mBinding.warnTelefon.setVisibility(View.VISIBLE);
            } else mBinding.warnTelefon.setVisibility(View.INVISIBLE);

            String nom = mBinding.edtNom.getText().toString();
            if (nom == null || nom.length() > 50 || nom.equals("")) {
                error = true;
                mBinding.warnNom.setVisibility(View.VISIBLE);
            } else mBinding.warnNom.setVisibility(View.INVISIBLE);

            String cognoms = mBinding.edtCognoms.getText().toString();
            if (cognoms == null || cognoms.length() > 50 ||cognoms.equals("")) {
                error = true;
                mBinding.warnCognoms.setVisibility(View.VISIBLE);
            } else mBinding.warnCognoms.setVisibility(View.INVISIBLE);

            Date dataNaix = participant.getData_naix();
            if (validaData(dataNaix)) {
                error = true;
                mBinding.warnDataNaix.setVisibility(View.VISIBLE);
            } else mBinding.warnDataNaix.setVisibility(View.INVISIBLE);

            String email = mBinding.edtEMail.getText().toString();
            if(!validaEmail(email)) {
                error = true;
                mBinding.warnEMail.setVisibility(View.VISIBLE);
            } else mBinding.warnEMail.setVisibility(View.INVISIBLE);


            // Afegir les dades del participant
            if(!error) {
                participant.setNif(NIF);
                participant.setNom(nom);
                participant.setCognoms(cognoms);
                participant.setTelefon(telf);
                participant.setEmail(email);
                participant.setEs_federat(mBinding.chkEsFederat.isActivated());

                // Desar les dades com a 'caché'



                // Pujar el participant al webservice



                // Es torna a la pàgina principal
                NavController nav = NavHostFragment.findNavController(this);
                nav.navigate(R.id.action_inscripcioFragment_to_navigation_home);
            }

        });


        return mBinding.getRoot();
    }

    private boolean validaEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validaData(Date dataNaix) {
        if (dataNaix == null) return false;

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar providedDate = Calendar.getInstance();
        providedDate.setTime(dataNaix);
        providedDate.set(Calendar.HOUR_OF_DAY, 0);
        providedDate.set(Calendar.MINUTE, 0);
        providedDate.set(Calendar.SECOND, 0);
        providedDate.set(Calendar.MILLISECOND, 0);

        return providedDate.after(today);
    }

    private boolean validaNIF(String nif) {
        if (nif == null || nif.length() != 9) {
            return false;
        }

        String numbers = nif.substring(0, 8);
        if (!numbers.matches("\\d+")) {
            return false;
        }

        char letter = nif.charAt(8);

        char[] controlLetters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        int remainder = Integer.parseInt(numbers) % 23;

        return controlLetters[remainder] == letter;
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

                        mBinding.txvResDataNaix.setText(utils.formatarData(selectedDate));

                        participant.setData_naix(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }
}