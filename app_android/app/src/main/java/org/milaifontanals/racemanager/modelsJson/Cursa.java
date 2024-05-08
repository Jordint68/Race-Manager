
package org.milaifontanals.racemanager.modelsJson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;


public class Cursa implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("dataInici")
    @Expose
    private String dataInici;
    @SerializedName("dataFi")
    @Expose
    private String dataFi;
    @SerializedName("lloc")
    @Expose
    private String lloc;
    @SerializedName("esport")
    @Expose
    private Esport esport;
    @SerializedName("estat")
    @Expose
    private Estat estat;
    @SerializedName("descripcio")
    @Expose
    private String descripcio;
    @SerializedName("limit")
    @Expose
    private String limit;
    @SerializedName("participants")
    @Expose
    private int participants;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("web")
    @Expose
    private String web;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDataInici() {
        return dataInici;
    }

    public void setDataInici(String dataInici) {
        this.dataInici = dataInici;
    }

    public String getDataFi() {
        return dataFi;
    }

    public void setDataFi(String dataFi) {
        this.dataFi = dataFi;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public Esport getEsport() {
        return esport;
    }

    public void setEsport(Esport esport) {
        this.esport = esport;
    }

    public Estat getEstat() {
        return estat;
    }

    public void setEstat(Estat estat) {
        this.estat = estat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

}
