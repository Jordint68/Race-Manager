
package org.milaifontanals.racemanager.modelsJson;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Cursa implements Serializable {

    @SerializedName("cur_id")
    @Expose
    private Integer curId;
    @SerializedName("cur_nom")
    @Expose
    private String curNom;
    @SerializedName("cur_data_inici")
    @Expose
    private String curDataInici;
    @SerializedName("cur_data_fi")
    @Expose
    private String curDataFi;
    @SerializedName("cur_lloc")
    @Expose
    private String curLloc;
    @SerializedName("cur_esp_id")
    @Expose
    private Integer curEspId;
    @SerializedName("cur_est_id")
    @Expose
    private Integer curEstId;
    @SerializedName("cur_desc")
    @Expose
    private String curDesc;
    @SerializedName("cur_limit_inscr")
    @Expose
    private Integer curLimitInscr;
    @SerializedName("cur_foto")
    @Expose
    private String curFoto;
    @SerializedName("cur_web")
    @Expose
    private String curWeb;
    @SerializedName("cur_inscrits")
    @Expose
    private Integer curInscrits;
    @SerializedName("esport")
    @Expose
    private Esport esport;
    @SerializedName("estat")
    @Expose
    private Estat estat;
    @SerializedName("circuits")
    @Expose
    private List<Circuit> circuits;

    public Integer getCurId() {
        return curId;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }

    public String getCurNom() {
        return curNom;
    }

    public void setCurNom(String curNom) {
        this.curNom = curNom;
    }

    public String getCurDataInici() {
        return curDataInici;
    }

    public void setCurDataInici(String curDataInici) {
        this.curDataInici = curDataInici;
    }

    public String getCurDataFi() {
        return curDataFi;
    }

    public void setCurDataFi(String curDataFi) {
        this.curDataFi = curDataFi;
    }

    public String getCurLloc() {
        return curLloc;
    }

    public void setCurLloc(String curLloc) {
        this.curLloc = curLloc;
    }

    public Integer getCurEspId() {
        return curEspId;
    }

    public void setCurEspId(Integer curEspId) {
        this.curEspId = curEspId;
    }

    public Integer getCurEstId() {
        return curEstId;
    }

    public void setCurEstId(Integer curEstId) {
        this.curEstId = curEstId;
    }

    public String getCurDesc() {
        return curDesc;
    }

    public void setCurDesc(String curDesc) {
        this.curDesc = curDesc;
    }

    public Integer getCurLimitInscr() {
        return curLimitInscr;
    }

    public void setCurLimitInscr(Integer curLimitInscr) {
        this.curLimitInscr = curLimitInscr;
    }

    public String getCurFoto() {
        return curFoto;
    }

    public void setCurFoto(String curFoto) {
        this.curFoto = curFoto;
    }

    public String getCurWeb() {
        return curWeb;
    }

    public void setCurWeb(String curWeb) {
        this.curWeb = curWeb;
    }

    public Integer getCurInscrits() {
        return curInscrits;
    }

    public void setCurInscrits(Integer curInscrits) {
        this.curInscrits = curInscrits;
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

    public List<Circuit> getCircuits() {
        return circuits;
    }

    public void setCircuits(List<Circuit> circuits) {
        this.circuits = circuits;
    }

}
