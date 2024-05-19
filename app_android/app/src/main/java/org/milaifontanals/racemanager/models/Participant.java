package org.milaifontanals.racemanager.models;

import java.util.Date;

public class Participant {
    private String nif;
    private String nom;
    private String cognoms;
    private Date data_naix;
    private String telefon;
    private String email;
    private Boolean es_federat;

    public Participant(String nif, String nom, String cognoms, Date data_naix, String telefon, String email, Boolean es_federat) {
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

    public Date getData_naix() {
        return data_naix;
    }

    public void setData_naix(Date data_naix) {
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
