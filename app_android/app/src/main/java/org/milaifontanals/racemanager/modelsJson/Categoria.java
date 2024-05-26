
package org.milaifontanals.racemanager.modelsJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Categoria implements Serializable {

    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("cat_esp_id")
    @Expose
    private Integer catEspId;
    @SerializedName("cat_nom")
    @Expose
    private String catNom;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getCatEspId() {
        return catEspId;
    }

    public void setCatEspId(Integer catEspId) {
        this.catEspId = catEspId;
    }

    public String getCatNom() {
        return catNom;
    }

    public void setCatNom(String catNom) {
        this.catNom = catNom;
    }

}
