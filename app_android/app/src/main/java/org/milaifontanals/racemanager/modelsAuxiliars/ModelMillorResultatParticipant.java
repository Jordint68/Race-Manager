package org.milaifontanals.racemanager.modelsAuxiliars;

public class ModelMillorResultatParticipant
{
    private String nom;
    private String dorsal;
    private int checkpoint;
    private double kmsCheckpoint;
    private String temps;

    public ModelMillorResultatParticipant(String nom, String dorsal, int checkpoint, double kmsCheckpoint, String temps) {
        this.nom = nom;
        this.dorsal = dorsal;
        this.checkpoint = checkpoint;
        this.kmsCheckpoint = kmsCheckpoint;
        this.temps = temps;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }

    public double getKmsCheckpoint() {
        return kmsCheckpoint;
    }

    public void setKmsCheckpoint(double kmsCheckpoint) {
        this.kmsCheckpoint = kmsCheckpoint;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }
}
