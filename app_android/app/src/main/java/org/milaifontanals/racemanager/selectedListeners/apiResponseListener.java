package org.milaifontanals.racemanager.selectedListeners;

import org.milaifontanals.racemanager.modelsAuxiliars.ModelMillorResultatParticipant;
import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsResponse;

import java.util.List;

public interface apiResponseListener {
    void onApiResponseReceived(List<ModelMillorResultatParticipant> data);
}
