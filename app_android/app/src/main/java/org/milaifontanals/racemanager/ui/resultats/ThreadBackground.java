package org.milaifontanals.racemanager.ui.resultats;

import static java.lang.Thread.sleep;

import android.util.Log;

import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.modelsAuxiliars.ModelMillorResultatParticipant;
import org.milaifontanals.racemanager.modelsJson.Participant;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaCircuitCategoria.CirCatDades;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaCircuitCategoria.CirCatExample;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaParticipants.ParticipantsExample;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsExample;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsRegistre;
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

    private int ccc_id;

    private apiResponseListener listener;

    private List<Participant> totsElsParticipants = new ArrayList<>();

    public ThreadBackground(int id_categoria, int id_circuit, int ccc_id, apiResponseListener arListener) {
        this.cat_id = id_categoria;
        this.cir_id = id_circuit;
        this.ccc_id = ccc_id;
        this.listener = arListener;
    }

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public void start() {
        /*
            Abans de inicia el thread faig una cerca del circuit-categoria per a poder trobar el ccc_id;
            Faig una cerca dels participants
         */
//        obtenirCCCId();
        obtenirParticipants();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final Runnable apiCaller = new Runnable() {
            public void run() {
                makeApiCall();
            }
        };
        scheduler.scheduleAtFixedRate(apiCaller, 0, 15, TimeUnit.SECONDS);
    }

    private void obtenirParticipants() {
        APIManager.getInstance().getAllParticipants(new Callback<ParticipantsExample>() {
            @Override
            public void onResponse(Call<ParticipantsExample> call, Response<ParticipantsExample> response) {
                totsElsParticipants = response.body().getParticipants();
            }

            @Override
            public void onFailure(Call<ParticipantsExample> call, Throwable t) {
                Log.d("ERROR", "Error en obtenir els participants - " + t.getMessage());
            }
        });
    }

//    private void obtenirCCCId() {
//        APIManager.getInstance().getAllCircuitsCategoria(new Callback<CirCatExample>() {
//            @Override
//            public void onResponse(Call<CirCatExample> call, Response<CirCatExample> response) {
//                List<CirCatDades> lCirCats = response.body().getCircuit_categoria();
//
//                for (CirCatDades circuit_categoria : lCirCats) {
//                    if((circuit_categoria.getCircuit().getCirId() == cir_id) && (circuit_categoria.getCategoria().getCatId() == cat_id)) {
//                        ccc_id = circuit_categoria.getCccId();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CirCatExample> call, Throwable t) {
//
//            }
//        });
//    }

    private void makeApiCall() {
        APIManager.getInstance().getAllRegistres(new Callback<ResultsExample>() {
            @Override
            public void onResponse(Call<ResultsExample> call, Response<ResultsExample> response) {
                ResultsExample res = response.body();
                List<ResultsRegistre> results = res.getRegistres();
                if (listener != null) {
                    List<ModelMillorResultatParticipant> resultats =  ordenarfiltrarRes(results);
                    listener.onApiResponseReceived(resultats);
                }
            }

            @Override
            public void onFailure(Call<ResultsExample> call, Throwable t) {
                Log.d("Error", "ThreadResultats - " + t.toString());
            }
        });
    }

    private List<ModelMillorResultatParticipant> ordenarfiltrarRes(List<ResultsRegistre> results) {
        // Obtenim una llista de resultants on sol hi tenim els d'aquesta cursa, circuit i categoria
        results = filtrarPerCircuitICategoria(results);
        return agruparITrobaMillorResultat(results);
    }

    private List<ModelMillorResultatParticipant> agruparITrobaMillorResultat(List<ResultsRegistre> results) {
        List<ModelMillorResultatParticipant> lFinal = new ArrayList<>();

        for (ResultsRegistre rr : results) {
            int idParticipant = rr.getInscripcio().getInsParId();
            short participantTrobat = 0;
            for(ModelMillorResultatParticipant mm : lFinal) {
                if(mm.getParId() == idParticipant) {
                    participantTrobat = 1;
                    if(mm.getKmsCheckpoint() < rr.getCheckpoint().getChkKm()) {
                        // Modifiquem les dades amb puntuacions millors:
                        mm.setKmsCheckpoint(rr.getCheckpoint().getChkKm());
                        mm.setTemps(rr.getRegTemps());
                    }
                    mm.setCheckpoint(mm.getCheckpoint() + 1);
                }
            }
            // Si no s'ha trobat cap participant amb la mateixa id, l'afeigeixo
            if(participantTrobat == 0) {
                String nomParticipant = "";
                for(Participant p : totsElsParticipants) {
                    if(p.getId() == idParticipant) nomParticipant = p.getNom() + " " + p.getCognoms();
                }
                lFinal.add(new ModelMillorResultatParticipant(idParticipant, nomParticipant, rr.getInscripcio().getInsDorsal().toString(), 1, rr.getCheckpoint().getChkKm(), rr.getRegTemps()));
            }
        }
        return lFinal;
    }

    private List<ResultsRegistre> filtrarPerCircuitICategoria(List<ResultsRegistre> results) {
        List<ResultsRegistre> aux = new ArrayList<>();
        for (ResultsRegistre rr : results) {
//            if(rr.getInscripcio().getInsCccId() == ccc_id) {
//                aux.add(rr);
//            }
            if(rr.getCheckpoint().getChkCirId() == cir_id) {
                aux.add(rr);
            }
        }
        return aux;
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


    public void stop() {
        scheduler.shutdown();
    }

}




