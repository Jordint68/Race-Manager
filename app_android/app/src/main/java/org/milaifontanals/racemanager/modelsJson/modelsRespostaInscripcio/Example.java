
package org.milaifontanals.racemanager.modelsJson.modelsRespostaInscripcio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("new_inscripcio")
    @Expose
    private NewInscripcio newInscripcio;

    public NewInscripcio getNewInscripcio() {
        return newInscripcio;
    }

    public void setNewInscripcio(NewInscripcio newInscripcio) {
        this.newInscripcio = newInscripcio;
    }

}
