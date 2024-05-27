
package org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsResponse {

    @SerializedName("inscripcions")
    @Expose
    private List<ResultsInscripcion> resultsInscripcions;

    public List<ResultsInscripcion> getInscripcions() {
        return resultsInscripcions;
    }

    public void setInscripcions(List<ResultsInscripcion> resultsInscripcions) {
        this.resultsInscripcions = resultsInscripcions;
    }

}
