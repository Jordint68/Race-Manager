package org.milaifontanals.racemanager.selectedListeners;

import org.milaifontanals.racemanager.modelsJson.modelsRespostaResultats.ResultsResponse;

public interface apiResponseListener {
    void onApiResponseReceived(ResultsResponse data);
}
