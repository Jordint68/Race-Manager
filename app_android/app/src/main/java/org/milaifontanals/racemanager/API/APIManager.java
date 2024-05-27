package org.milaifontanals.racemanager.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.milaifontanals.racemanager.modelsJson.ResponseGetCurses;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaInscripcio.Example;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    // Constant amb la la base url

    // url casa
//    private final String BASE_URL = "http://192.168.1.35/projecteApp/public/api/";

    // URL per al simulador
    private final String BASE_URL = "http://10.0.2.2/projecteApp/public/api/";

    // url clase
//    private final String BASE_URL = "http://10.100.0.125/projecteApp/public/api/";
    private static APIManager mInstance;
    private API mApiService;


    private APIManager(){
        Gson gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .registerTypeAdapter(Boolean.class, new TinyIntToBooleanTypeAdapter())
                .registerTypeAdapter(GregorianCalendar.class, new GregorianCalendarTypeAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
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
        Call<ResponseGetCurses> call = mApiService.getAllCurses();
        call.enqueue(cb);
    }

    public void getAllCurses(Callback<ResponseGetCurses> cb) {
        Call<ResponseGetCurses> call = mApiService.getAllCurses();
        call.enqueue(cb);
    }

    public void storeInscripcio(String json, Callback<Example> cb) {
        Call<Example> call = mApiService.postInscripcio(json);
        call.enqueue(cb);
    }

    public void getAllCircuitsCategoria(int cat_id, int cir_id, Callback<ResultsResponse> cb) {
        Call<ResultsResponse> call = mApiService.getAllCircuitsCategoria(cat_id, cir_id);
        call.enqueue(cb);
    }



}

class DateTypeAdapter extends TypeAdapter<Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            String dateFormatAsString = dateFormat.format(value);
            out.value(dateFormatAsString);
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        try {
            return dateFormat.parse(in.nextString());
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }
}

class TinyIntToBooleanTypeAdapter extends TypeAdapter<Boolean> {

    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value ? 1 : 0);
    }

    @Override
    public Boolean read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        int intValue = in.nextInt();
        return intValue != 0;
    }
}

class GregorianCalendarTypeAdapter implements JsonSerializer<GregorianCalendar>, JsonDeserializer<GregorianCalendar> {

    private final SimpleDateFormat dateFormat;

    public GregorianCalendarTypeAdapter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public JsonElement serialize(GregorianCalendar src, Type typeOfSrc, JsonSerializationContext context) {
        String formattedDate = dateFormat.format(src.getTime());
        return new JsonPrimitive(formattedDate);
    }

    @Override
    public GregorianCalendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(json.getAsString()));
            return new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
        } catch (ParseException e) {
            throw new JsonParseException("No es pot transformar la data: " + json.getAsString(), e);
        }
    }
}