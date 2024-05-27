package org.milaifontanals.racemanager.ui.inscripcio;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.databinding.FragmentInscripcioBinding;
import org.milaifontanals.racemanager.models.Participant;
import org.milaifontanals.racemanager.modelsJson.Inscripcion;
import org.milaifontanals.racemanager.modelsJson.NewInscripcio;
import org.milaifontanals.racemanager.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inscripcioFragment extends Fragment {
    private Utils utils = new Utils();

    public static final String CLAUCURSA = "param_cursa";
    public static final String CLAUCIRCUIT = "param_circuit";
    public static final String CLAUCATEGORIA = "param_categoria";
    public static final String CLAUCCC = "param_ccc";

    private static final String PREF_NIF = "NIF";
    private static final String PREF_NOM = "NOM";
    private static final String PREF_COGNOMS = "COGNOMS";
    private static final String PREF_DATA = "DATA";
    private static final String PREF_TELF = "TELF";
    private static final String PREF_EMAIL = "EMAIL";
    private static final String PREF_FEDERAT = "FEDERAT";

    private SharedPreferences sh;
    private SharedPreferences.Editor shEditor;


    private FragmentInscripcioBinding mBinding;

    private String cursa_id;
    private String circuit_id;
    private String categoria_id;
    private String ccc_id;

    private inscripcioFragment thisFragment = this;

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
            ccc_id = bundle.getString(CLAUCCC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentInscripcioBinding.inflate(getLayoutInflater());

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Inscripció");
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavController nav = NavHostFragment.findNavController(thisFragment);
                        nav.navigate(R.id.action_inscripcioFragment_to_navigation_home);
                    }
                });
            }
        }


        recollirDadesDeCache();

        mBinding.btnDataNaix.setOnClickListener(view -> {
            openDatePicker();
        });

        mBinding.btnEnviar.setOnClickListener(view -> {
            funcBtnEnviar();

        });
        
        return mBinding.getRoot();
    }

    private void recollirDadesDeCache() {
        sh = getActivity().getPreferences(Context.MODE_PRIVATE);
        String NIF = sh.getString(PREF_NIF, "");
        String nom = sh.getString(PREF_NOM, "");
        String cognoms = sh.getString(PREF_COGNOMS, "");
        String s_data = sh.getString(PREF_DATA, "");
        String telf = sh.getString(PREF_TELF, "");
        String email = sh.getString(PREF_EMAIL, "");
        boolean federat = sh.getBoolean(PREF_FEDERAT, false);

        mBinding.edtNIF.setText(NIF);
        mBinding.edtNom.setText(nom);
        mBinding.edtCognoms.setText(cognoms);
        mBinding.edtTelefon.setText(telf);
        mBinding.edtEMail.setText(email);
        mBinding.chkEsFederat.setChecked(federat);

        Date data = utils.convertirStringADate(s_data);
        if (data != null) {
            mBinding.txvResDataNaix.setText(s_data);
            participant.setData_naix(data);
        }

    }

    private void funcBtnEnviar() {
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
        boolean esFederat = mBinding.chkEsFederat.isChecked();

        // Afegir les dades del participant
        if(!error) {
            participant.setNif(NIF);
            participant.setNom(nom);
            participant.setCognoms(cognoms);
            participant.setTelefon(telf);
            participant.setEmail(email);
            participant.setEs_federat(esFederat);

            // Desar les dades com a 'caché'

            desarDadesCache();

            // Pujar el participant al webservice

            String jsonString = formarJson();

            enviarParticipacio(jsonString);

            // Es torna a la pàgina principal
            NavController nav = NavHostFragment.findNavController(this);
            nav.navigate(R.id.action_inscripcioFragment_to_navigation_home);
        }
    }

    private void enviarParticipacio(String jsonString) {
        if(jsonString != null) {
            APIManager.getInstance().storeInscripcio(jsonString, new Callback<NewInscripcio>() {

                @Override
                public void onResponse(Call<NewInscripcio> call, Response<NewInscripcio> response) {
                    Log.d("XXX", "Participació enviada");
                    Inscripcion resposta = response.body().inscripcion;
                    Log.d("XXX", resposta.toString());
                }

                @Override
                public void onFailure(Call<NewInscripcio> call, Throwable t) {
                    Log.d("ERROR", t.toString());
                }
            });
        }
    }

    private String formarJson() {
        JSONObject json = new JSONObject();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String dataNaix = "";
            Date data = participant.getData_naix();
            if(data != null) {
                dataNaix = dateFormat.format(data);
            } else {
                dataNaix = mBinding.txvResDataNaix.getText().toString();
            }



            JSONObject jsonInscripcio = new JSONObject();
            jsonInscripcio.put("ins_ccc_id", ccc_id);
            jsonInscripcio.put("ins_retirat", "0");

            JSONObject jsonParticipant = new JSONObject();
            jsonParticipant.put("par_cognoms", participant.getCognoms());
            jsonParticipant.put("par_data_naixement", dataNaix);
            jsonParticipant.put("par_email", participant.getEmail());
            jsonParticipant.put("par_es_federat", participant.getEs_federat());
            jsonParticipant.put("par_nif", participant.getNif());
            jsonParticipant.put("par_nom", participant.getNom());
            jsonParticipant.put("par_telefon", participant.getTelefon());

            json.put("inscripcio", jsonInscripcio);
            json.put("participant", jsonParticipant);

            String jsonString = json.toString();
            Log.d("XXX", jsonString);

            return jsonString;
        } catch(JSONException ex) {
            Log.d("ERROR", ex.getMessage().toString());
        }

        return null;
    }

    private void desarDadesCache() {
        shEditor = sh.edit();
        shEditor.putString(PREF_NIF, participant.getNif());
        shEditor.putString(PREF_NOM, participant.getNom());
        shEditor.putString(PREF_COGNOMS, participant.getCognoms());
        shEditor.putString(PREF_DATA, mBinding.txvResDataNaix.getText().toString());
        shEditor.putString(PREF_TELF, participant.getTelefon());
        shEditor.putString(PREF_EMAIL, participant.getEmail());
        shEditor.putBoolean(PREF_FEDERAT, participant.getEs_federat());
        shEditor.apply();
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