
package org.milaifontanals.racemanager.modelsJson;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Serializable {

    @SerializedName("ccc_id")
    @Expose
    private Integer cccId;
    @SerializedName("ccc_cat_id")
    @Expose
    private Integer cccCatId;
    @SerializedName("ccc_cir_id")
    @Expose
    private Integer cccCirId;
    @SerializedName("categoria")
    @Expose
    private Categoria categoria;
    @SerializedName("inscripcions")
    @Expose
    private List<Inscripcion> inscripcions;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Inscripcion> getInscripcions() {
        return inscripcions;
    }

    public void setInscripcions(List<Inscripcion> inscripcions) {
        this.inscripcions = inscripcions;
    }

}
