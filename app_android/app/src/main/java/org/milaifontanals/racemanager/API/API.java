package org.milaifontanals.racemanager.API;

import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaCircuitCategoria.CirCatExample;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaInscripcio.Example;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaParticipants.ParticipantsExample;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsExample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("get_all_curses")
    Call<ResponseGetCurses> getAllCurses();

    @POST("store_inscripcio")
    Call<Example> postInscripcio(
        @Body String json
    );
    @GET("get_all_registres")
    Call<ResultsExample> getAllRegistres();

    @GET("get_all_circuits_categoria")
    Call<CirCatExample> getAllCircuitsCategoria();

    @GET("get_all_participants")
    Call<ParticipantsExample> getAllParticipants();

}
