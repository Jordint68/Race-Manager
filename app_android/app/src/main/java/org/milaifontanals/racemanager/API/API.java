package org.milaifontanals.racemanager.API;

import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaInscripcio.Example;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @GET("get_all_curses")
    Call<ResponseGetCurses> getAllCurses();

    @POST("store_inscripcio")
    Call<Example> postInscripcio(
        @Body String json
    );

    @GET("get_all_circuits_categoria")
    Call<ResultsResponse> getAllCircuitsCategoria(
            @Query("cat_id") int cat_id,
            @Query("cir_id") int cir_id
    );


}
