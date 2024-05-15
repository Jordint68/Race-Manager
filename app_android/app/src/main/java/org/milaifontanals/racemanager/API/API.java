package org.milaifontanals.racemanager.API;

import org.milaifontanals.racemanager.modelsJson.ResponseGetCircuits;
import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("JSON_doc/getCurses/getCursesRS.json")
//    @GET("getCurses")
    Call<ResponseGetCurses> getCurses(
            @Query("cursa_id") int id  
    );

    @GET("JSON_doc/getCircuits/getCircuitsRS.json")
    Call<ResponseGetCircuits> getCircuits(
            @Query("cursa_id") int id
    );
}
