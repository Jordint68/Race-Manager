package org.milaifontanals.racemanager.modelsJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Checkpoint {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("pk")
    @Expose
    private float pk;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public float getPk() {
        return pk;
    }

    public void setPk(float pk) {
        this.pk = pk;
    }
}
