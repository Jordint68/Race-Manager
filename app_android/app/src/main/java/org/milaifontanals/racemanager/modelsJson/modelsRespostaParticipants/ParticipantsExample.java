package org.milaifontanals.racemanager.modelsJson.modelsRespostaParticipants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.milaifontanals.racemanager.modelsJson.Participant;

import java.util.List;

public class ParticipantsExample {
    @SerializedName("participants")
    @Expose
    private List<Participant> participants;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
