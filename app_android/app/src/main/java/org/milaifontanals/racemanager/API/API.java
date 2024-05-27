package org.milaifontanals.racemanager.API;

import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

//    @GET("getCurses/{cursaId}")
//    Call<ResponseGetCurses> getCurses(
////            @Query("cursaID") int id
//            @Path("cursaId") int id
//    );

    @GET("get_all_curses")
    Call<ResponseGetCurses> getAllCurses();

    @POST("store_inscripcio")
    Call postInscripcio(
        @Body String json
    );
}
