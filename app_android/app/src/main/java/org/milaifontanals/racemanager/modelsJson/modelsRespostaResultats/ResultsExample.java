
package org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsExample {

    @SerializedName("circuit_categoria")
    @Expose
    private List<ResultsCircuitCategorium> circuitCategoria;

    public List<ResultsCircuitCategorium> getCircuitCategoria() {
        return circuitCategoria;
    }

    public void setCircuitCategoria(List<ResultsCircuitCategorium> circuitCategoria) {
        this.circuitCategoria = circuitCategoria;
    }

}
