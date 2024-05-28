package org.milaifontanals.racemanager.ui.resultats;

import android.util.Log;

import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.modelsAuxiliars.ModelMillorResultatParticipant;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsInscripcion;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsRegistre;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsResponse;
import org.milaifontanals.racemanager.selectedListeners.apiResponseListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThreadBackground {
    private int cat_id;
    private int cir_id;

    private apiResponseListener listener;

    public ThreadBackground(int id_categoria, int id_circuit, apiResponseListener arListener) {
        this.cat_id = id_categoria;
        this.cir_id = id_circuit;
        this.listener = arListener;
    }

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public void start() {
        final Runnable apiCaller = new Runnable() {
            public void run() {
                makeApiCall();
            }
        };
        scheduler.scheduleAtFixedRate(apiCaller, 0, 15, TimeUnit.SECONDS);
    }

    private void makeApiCall() {
        APIManager.getInstance().getAllCircuitsCategoria(cat_id, cir_id, new Callback<ResultsResponse>() {
            @Override
            public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                ResultsResponse results = response.body();
                if (listener != null) {
                    List<ModelMillorResultatParticipant> resultats = ordenarfiltrarRes(results);
                    listener.onApiResponseReceived(resultats);
                }
            }

            @Override
            public void onFailure(Call<ResultsResponse> call, Throwable t) {
                Log.d("Error", "ThreadResultats - " + t.toString());
            }
        });
    }

    static List<ModelMillorResultatParticipant> ordenarLlista(List<ModelMillorResultatParticipant> lista) {
        Comparator<ModelMillorResultatParticipant> comparator = new Comparator<ModelMillorResultatParticipant>() {
            @Override
            public int compare(ModelMillorResultatParticipant o1, ModelMillorResultatParticipant o2) {
                int compareByCheckpoint = Integer.compare(o2.getCheckpoint(), o1.getCheckpoint());
                if (compareByCheckpoint != 0) {
                    return compareByCheckpoint;
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date date1 = sdf.parse(o1.getTemps());
                        Date date2 = sdf.parse(o2.getTemps());
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }
            }
        };

        // Ordenar la lista usando el comparador personalizado
        Collections.sort(lista, comparator);

        return lista;
    }

    private List<ModelMillorResultatParticipant> ordenarfiltrarRes(ResultsResponse results) {
        List<ModelMillorResultatParticipant> resultatsSenseOrdenar = new ArrayList<>();
        List<ModelMillorResultatParticipant> resultats = new ArrayList<>();

        // Els retirats no s'han de mostrar
        List<ResultsInscripcion> inscripcionsSenseRetirats = eliminarRetirats(results);

        // Cada iteració són les dades de UNA persona
        resultatsSenseOrdenar = millorResultatsPerPersona(inscripcionsSenseRetirats);

        // Ordenar la llista
        resultats = ordenarLlista(resultatsSenseOrdenar);


        return resultats;
    }

    private static List<ModelMillorResultatParticipant> millorResultatsPerPersona(List<ResultsInscripcion> inscripcionsSenseRetirats) {
        List<ModelMillorResultatParticipant> resultats = new ArrayList<>();
        for (ResultsInscripcion ri : inscripcionsSenseRetirats) {
            String nom = ri.getParticipant().getParNom() + " " + ri.getParticipant().getParCognoms();
            String dorsal = ri.getInsDorsal().toString();

            int majorNCheckpoint = 0;
            String reg_temps = "";
            Double kms = 0.0;
            for (ResultsRegistre rr : ri.getRegistres()) {
                if(rr.getRegChkId() > majorNCheckpoint) {
                    majorNCheckpoint = rr.getRegChkId();
                    reg_temps = rr.getRegTemps();
                    kms = rr.getCheckpoint().getChkKm();
                }
            }

            if(majorNCheckpoint > 0) {
                resultats.add(new ModelMillorResultatParticipant(nom, dorsal, majorNCheckpoint, kms, reg_temps));
            }
        }

        return resultats;
    }

    private static List<ResultsInscripcion> eliminarRetirats(ResultsResponse results) {
        List<ResultsInscripcion> noRetirats = new ArrayList<>();
        for(ResultsInscripcion ri : results.getInscripcions()) {
            if (ri.getInsRetirat() != 1) noRetirats.add(ri);
        }

        return noRetirats;
    }

    public void stop() {
        scheduler.shutdown();
    }
}
