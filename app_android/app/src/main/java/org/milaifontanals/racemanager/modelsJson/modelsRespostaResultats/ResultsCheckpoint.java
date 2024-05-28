
package org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsCheckpoint {

    @SerializedName("chk_id")
    @Expose
    private Integer chkId;
    @SerializedName("chk_km")
    @Expose
    private Double chkKm;
    @SerializedName("chk_cir_id")
    @Expose
    private Integer chkCirId;

    public Integer getChkId() {
        return chkId;
    }

    public void setChkId(Integer chkId) {
        this.chkId = chkId;
    }

    public Double getChkKm() {
        return chkKm;
    }

    public void setChkKm(Double chkKm) {
        this.chkKm = chkKm;
    }

    public Integer getChkCirId() {
        return chkCirId;
    }

    public void setChkCirId(Integer chkCirId) {
        this.chkCirId = chkCirId;
    }

}
