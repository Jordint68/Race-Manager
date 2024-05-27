package org.milaifontanals.racemanager.modelsJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.GregorianCalendar;

public class Participant {

    @SerializedName("par_id")
    @Expose
    private int id;
    @SerializedName("par_nif")
    @Expose
    private String nif;
    @SerializedName("par_nom")
    @Expose
    private String nom;

    @SerializedName("par_cognoms")
    @Expose
    private String cognoms;

    @SerializedName("par_data_naixement")
    @Expose
    private GregorianCalendar data_naix;

    @SerializedName("par_telefon")
    @Expose
    private String telefon;

    @SerializedName("par_email")
    @Expose
    private String email;

    @SerializedName("par_es_federat")
    @Expose
    private Boolean es_federat;

    public Participant(String nif, String nom, String cognoms, GregorianCalendar data_naix, String telefon, String email, Boolean es_federat) {
        this.nif = nif;
        this.nom = nom;
        this.cognoms = cognoms;
        this.data_naix = data_naix;
        this.telefon = telefon;
        this.email = email;
        this.es_federat = es_federat;
    }

    public Participant() {

    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public GregorianCalendar getData_naix() {
        return data_naix;
    }

    public void setData_naix(GregorianCalendar data_naix) {
        this.data_naix = data_naix;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEs_federat() {
        return es_federat;
    }

    public void setEs_federat(Boolean es_federat) {
        this.es_federat = es_federat;
    }
}
