
package org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsRegistre {

    @SerializedName("reg_id")
    @Expose
    private Integer regId;
    @SerializedName("reg_ins_id")
    @Expose
    private Integer regInsId;
    @SerializedName("reg_chk_id")
    @Expose
    private Integer regChkId;
    @SerializedName("reg_temps")
    @Expose
    private String regTemps;
    @SerializedName("inscripcio")
    @Expose
    private ResultsInscripcio inscripcio;
    @SerializedName("checkpoint")
    @Expose
    private ResultsCheckpoint checkpoint;

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public Integer getRegInsId() {
        return regInsId;
    }

    public void setRegInsId(Integer regInsId) {
        this.regInsId = regInsId;
    }

    public Integer getRegChkId() {
        return regChkId;
    }

    public void setRegChkId(Integer regChkId) {
        this.regChkId = regChkId;
    }

    public String getRegTemps() {
        return regTemps;
    }

    public void setRegTemps(String regTemps) {
        this.regTemps = regTemps;
    }

    public ResultsInscripcio getInscripcio() {
        return inscripcio;
    }

    public void setInscripcio(ResultsInscripcio inscripcio) {
        this.inscripcio = inscripcio;
    }

    public ResultsCheckpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(ResultsCheckpoint checkpoint) {
        this.checkpoint = checkpoint;
    }

}
