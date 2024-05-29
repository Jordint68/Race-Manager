
package org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsCircuitCategorium {

    @SerializedName("ccc_id")
    @Expose
    private Integer cccId;
    @SerializedName("ccc_cat_id")
    @Expose
    private Integer cccCatId;
    @SerializedName("ccc_cir_id")
    @Expose
    private Integer cccCirId;
    @SerializedName("circuit")
    @Expose
    private ResultsCircuit circuit;
    @SerializedName("categoria")
    @Expose
    private ResultsCategoria categoria;
    @SerializedName("inscripcions")
    @Expose
    private List<Resultsnscripcion> resultsnscripcions;

    public Integer getCccId() {
        return cccId;
    }

    public void setCccId(Integer cccId) {
        this.cccId = cccId;
    }

    public Integer getCccCatId() {
        return cccCatId;
    }

    public void setCccCatId(Integer cccCatId) {
        this.cccCatId = cccCatId;
    }

    public Integer getCccCirId() {
        return cccCirId;
    }

    public void setCccCirId(Integer cccCirId) {
        this.cccCirId = cccCirId;
    }

    public ResultsCircuit getCircuit() {
        return circuit;
    }

    public void setCircuit(ResultsCircuit circuit) {
        this.circuit = circuit;
    }

    public ResultsCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ResultsCategoria categoria) {
        this.categoria = categoria;
    }

    public List<Resultsnscripcion> getInscripcions() {
        return resultsnscripcions;
    }

    public void setInscripcions(List<Resultsnscripcion> resultsnscripcions) {
        this.resultsnscripcions = resultsnscripcions;
    }

}
