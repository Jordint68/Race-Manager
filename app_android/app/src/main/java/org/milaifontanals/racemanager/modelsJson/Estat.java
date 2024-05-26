
package org.milaifontanals.racemanager.modelsJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Estat implements Serializable {
    @SerializedName("est_id")
    @Expose
    private Integer estId;
    @SerializedName("est_nom")
    @Expose
    private String estNom;

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public String getEstNom() {
        return estNom;
    }

    public void setEstNom(String estNom) {
        this.estNom = estNom;
    }

}
