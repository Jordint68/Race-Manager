package org.milaifontanals.racemanager.modelsJson.modelsRespostaCircuitCategoria;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CirCatExample {
    @SerializedName("circuit_categoria")
    @Expose
    List<CirCatDades> circuit_categoria;

    public List<CirCatDades> getCircuit_categoria() {
        return circuit_categoria;
    }

    public void setCircuit_categoria(List<CirCatDades> circuit_categoria) {
        this.circuit_categoria = circuit_categoria;
    }
}
