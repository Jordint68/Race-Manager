
package org.milaifontanals.racemanager.modelsJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Esport implements Serializable {

    @SerializedName("esp_id")
    @Expose
    private Integer espId;
    @SerializedName("esp_nom")
    @Expose
    private String espNom;

    public Integer getEspId() {
        return espId;
    }

    public void setEspId(Integer espId) {
        this.espId = espId;
    }

    public String getEspNom() {
        return espNom;
    }

    public void setEspNom(String espNom) {
        this.espNom = espNom;
    }

}
