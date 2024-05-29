
package org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsExample {

    @SerializedName("registres")
    @Expose
    private List<ResultsRegistre> registres;

    public List<ResultsRegistre> getRegistres() {
        return registres;
    }

    public void setRegistres(List<ResultsRegistre> registres) {
        this.registres = registres;
    }

}
