package org.milaifontanals.racemanager.modelsJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Circuit {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("cursaId")
    @Expose
    private int cursaId;

    @SerializedName("num")
    @Expose
    private Integer num;

    @SerializedName("distancia")
    @Expose
    private float distancia;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("preu")
    @Expose
    private BigDecimal preu;

    @SerializedName("temps")
    @Expose
    private String temps;

    @SerializedName("checkpoints")
    @Expose
    private List<Checkpoint> checkpoints;

    @SerializedName("categories")
    @Expose
    private List<String> categories;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public int getCursaId() {
        return cursaId;
    }

    public void setCursaId(int cursaId) {
        this.cursaId = cursaId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public void setPreu(BigDecimal preu) {
        this.preu = preu;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
