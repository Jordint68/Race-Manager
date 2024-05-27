
package org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsParticipant {

    @SerializedName("par_id")
    @Expose
    private Integer parId;
    @SerializedName("par_nif")
    @Expose
    private String parNif;
    @SerializedName("par_nom")
    @Expose
    private String parNom;
    @SerializedName("par_cognoms")
    @Expose
    private String parCognoms;
    @SerializedName("par_data_naixement")
    @Expose
    private String parDataNaixement;
    @SerializedName("par_telefon")
    @Expose
    private String parTelefon;
    @SerializedName("par_email")
    @Expose
    private String parEmail;
    @SerializedName("par_es_federat")
    @Expose
    private Integer parEsFederat;

    public Integer getParId() {
        return parId;
    }

    public void setParId(Integer parId) {
        this.parId = parId;
    }

    public String getParNif() {
        return parNif;
    }

    public void setParNif(String parNif) {
        this.parNif = parNif;
    }

    public String getParNom() {
        return parNom;
    }

    public void setParNom(String parNom) {
        this.parNom = parNom;
    }

    public String getParCognoms() {
        return parCognoms;
    }

    public void setParCognoms(String parCognoms) {
        this.parCognoms = parCognoms;
    }

    public String getParDataNaixement() {
        return parDataNaixement;
    }

    public void setParDataNaixement(String parDataNaixement) {
        this.parDataNaixement = parDataNaixement;
    }

    public String getParTelefon() {
        return parTelefon;
    }

    public void setParTelefon(String parTelefon) {
        this.parTelefon = parTelefon;
    }

    public String getParEmail() {
        return parEmail;
    }

    public void setParEmail(String parEmail) {
        this.parEmail = parEmail;
    }

    public Integer getParEsFederat() {
        return parEsFederat;
    }

    public void setParEsFederat(Integer parEsFederat) {
        this.parEsFederat = parEsFederat;
    }

}
