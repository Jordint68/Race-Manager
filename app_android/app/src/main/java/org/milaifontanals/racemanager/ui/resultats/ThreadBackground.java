package org.milaifontanals.racemanager.ui.resultats;

import android.util.Log;

import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsResponse;
import org.milaifontanals.racemanager.selectedListeners.apiResponseListener;

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
                    listener.onApiResponseReceived(results);
                    Log.d("XXX", results.toString());
                }
            }

            @Override
            public void onFailure(Call<ResultsResponse> call, Throwable t) {
                Log.d("Error", "ThreadResultats - " + t.toString());
            }
        });
    }

    public void stop() {
        scheduler.shutdown();
    }
}
