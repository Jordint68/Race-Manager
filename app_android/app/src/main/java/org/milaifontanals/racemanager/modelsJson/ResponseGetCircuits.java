package org.milaifontanals.racemanager.modelsJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetCircuits {
    @SerializedName("circuits")
    @Expose
    private List<Circuit> circuits;

    @SerializedName("status")
    @Expose
    private Response response;

    public List<Circuit> getCircuits() {
        return circuits;
    }

    public void setCircuits(List<Circuit> circuits) {
        this.circuits = circuits;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
