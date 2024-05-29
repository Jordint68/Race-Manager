package org.milaifontanals.racemanager.modelsJson.modelsRespostaCircuitCategoria;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.milaifontanals.racemanager.modelsJson.Categoria;
import org.milaifontanals.racemanager.modelsJson.Circuit;

public class CirCatDades {
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
    private Circuit circuit;
    @SerializedName("categoria")
    @Expose
    private Categoria categoria;

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

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
