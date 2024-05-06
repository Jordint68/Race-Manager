package org.milaifontanals.racemanager.API;

import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    // Constant amb la la base url
    private final String BASE_URL = "https://raw.githubusercontent.com/acalvet2k23/racemanagerjson/main/";

    private static APIManager mInstance;
    private API mApiService;


    private APIManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService=retrofit.create(API.class);
    }

    public static synchronized APIManager getInstance(){
        if(mInstance == null){
            mInstance = new APIManager();
        }

        return mInstance;
    }


    public void getCurses(int codi_cursa, Callback<ResponseGetCurses> cb) {
        Call<ResponseGetCurses> call = mApiService.getCurses(codi_cursa);
        call.enqueue(cb);
    }

}
