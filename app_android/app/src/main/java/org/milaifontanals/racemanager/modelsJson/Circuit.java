
package org.milaifontanals.racemanager.modelsJson;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Circuit implements Serializable {

    @SerializedName("cir_id")
    @Expose
    private Integer cirId;
    @SerializedName("cir_cur_id")
    @Expose
    private Integer cirCurId;
    @SerializedName("cir_num")
    @Expose
    private Integer cirNum;
    @SerializedName("cir_distancia")
    @Expose
    private Integer cirDistancia;
    @SerializedName("cir_nom")
    @Expose
    private String cirNom;
    @SerializedName("cir_preu")
    @Expose
    private String cirPreu;
    @SerializedName("cir_temps_estimat")
    @Expose
    private String cirTempsEstimat;
    @SerializedName("cir_checkpoints")
    @Expose
    private Integer cirCheckpoints;
    @SerializedName("categories")
    @Expose
    private List<Category> categories;
    @SerializedName("checkpoints")
    @Expose
    private List<Checkpoint> checkpoints;

    public Integer getCirId() {
        return cirId;
    }

    public void setCirId(Integer cirId) {
        this.cirId = cirId;
    }

    public Integer getCirCurId() {
        return cirCurId;
    }

    public void setCirCurId(Integer cirCurId) {
        this.cirCurId = cirCurId;
    }

    public Integer getCirNum() {
        return cirNum;
    }

    public void setCirNum(Integer cirNum) {
        this.cirNum = cirNum;
    }

    public Integer getCirDistancia() {
        return cirDistancia;
    }

    public void setCirDistancia(Integer cirDistancia) {
        this.cirDistancia = cirDistancia;
    }

    public String getCirNom() {
        return cirNom;
    }

    public void setCirNom(String cirNom) {
        this.cirNom = cirNom;
    }

    public String getCirPreu() {
        return cirPreu;
    }

    public void setCirPreu(String cirPreu) {
        this.cirPreu = cirPreu;
    }

    public String getCirTempsEstimat() {
        return cirTempsEstimat;
    }

    public void setCirTempsEstimat(String cirTempsEstimat) {
        this.cirTempsEstimat = cirTempsEstimat;
    }

    public Integer getCirCheckpoints() {
        return cirCheckpoints;
    }

    public void setCirCheckpoints(Integer cirCheckpoints) {
        this.cirCheckpoints = cirCheckpoints;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

}
