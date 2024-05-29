package org.milaifontanals.racemanager.ui.resultats;

import android.util.Log;

import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.modelsAuxiliars.ModelMillorResultatParticipant;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsCategoria;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsCircuit;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsCircuitCategorium;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsExample;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.Resultsnscripcion;
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
        APIManager.getInstance().getAllCircuitsCategoria(new Callback<ResultsExample>() {
            @Override
            public void onResponse(Call<ResultsExample> call, Response<ResultsExample> response) {
                ResultsExample res = response.body();
                List<ResultsCircuitCategorium> results = res.getCircuitCategoria();
                if (listener != null) {
                    List<ModelMillorResultatParticipant> resultats = ordenarfiltrarRes(results);
                    listener.onApiResponseReceived(resultats);
                }
            }

            @Override
            public void onFailure(Call<ResultsExample> call, Throwable t) {
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

    private List<ModelMillorResultatParticipant> ordenarfiltrarRes(List<ResultsCircuitCategorium> results) {
        List<ModelMillorResultatParticipant> resultatsSenseOrdenar = new ArrayList<>();
        List<ModelMillorResultatParticipant> resultats = new ArrayList<>();

//        // Els retirats no s'han de mostrar
//        List<ResultsInscripcion> inscripcionsSenseRetirats = eliminarRetirats(results);
//
//        // Cada iteració són les dades de UNA persona
//        resultatsSenseOrdenar = millorResultatsPerPersona(inscripcionsSenseRetirats);
//
//        // Ordenar la llista
//        resultats = ordenarLlista(resultatsSenseOrdenar);
//
        return null;
    }

    private static List<ModelMillorResultatParticipant> millorResultatsPerPersona(List<Resultsnscripcion> inscripcionsSenseRetirats) {
        List<ModelMillorResultatParticipant> resultats = new ArrayList<>();

        Map<Integer, List<Registres>> registrees = registres.stream().collect(Collectors.groupingBy(Registro::getTipus()));
        for (Resultsnscripcion ri : inscripcionsSenseRetirats) {
//            String nom = ri.get + " " + ri.getParticipant().getParCognoms();
            String dorsal = ri.getInsDorsal().toString();

            Double majorNCheckpoint = 0.0;
            String reg_temps = "";
            Double kms = 0.0;
            int i = 0;
            for (ResultsRegistre rr : ri.getRegistres()) {
                if(rr.getCheckpoint().getChkKm() > kms) {
                    majorNCheckpoint = rr.getCheckpoint().getChkKm();
                    reg_temps = rr.getRegTemps();
                    kms = rr.getCheckpoint().getChkKm();
                    i++;
                }
            }

            if(majorNCheckpoint > 0) {
                resultats.add(new ModelMillorResultatParticipant(nom, dorsal, i, kms, reg_temps));
            }
        }

        return resultats;
    }

//    private static List<Re> eliminarRetirats(ResultsResponse results) {
//        List<ResultsInscripcion> noRetirats = new ArrayList<>();
//        for(ResultsInscripcion ri : results.getInscripcions()) {
//            if (ri.getInsRetirat() != 1) noRetirats.add(ri);
//        }
//
//        return noRetirats;
//    }

    public void stop() {
        scheduler.shutdown();
    }


    private static List<ModelMillorResultatParticipant> filtraResultats(List<ResultsCircuitCategorium> lCircuitCategoria, int carreraId, int idCategoria, int idCircuito) {

        List<ModelMillorResultatParticipant> lFinal = new ArrayList<>();

        for (int i = 0; i < lCircuitCategoria.size(); i++) {
            ResultsCircuit rsCircuit = lCircuitCategoria.get(i).getCircuit();
            ResultsCategoria rsCategoria = lCircuitCategoria.get(i).getCategoria();

            int cirId = rsCircuit.getCirId();
            int catId = rsCategoria.getCatId();

            if (idCategoria == catId && idCircuito == cirId) {
                List<Resultsnscripcion> inscripcions = lCircuitCategoria.get(i).getInscripcions();
                List<ModelMillorResultatParticipant> resSenseOrdenar = millorResultatsPerPersona(inscripcions);
            }
        }

        return lFinal;
    }
}




