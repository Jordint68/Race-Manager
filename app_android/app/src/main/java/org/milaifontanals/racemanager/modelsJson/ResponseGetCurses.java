
package org.milaifontanals.racemanager.modelsJson;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class ResponseGetCurses implements Serializable {

    @SerializedName("curses")
    @Expose
    private List<Cursa> curses;

    public List<Cursa> getCurses() {
        return curses;
    }

    public void setCurses(List<Cursa> curses) {
        this.curses = curses;
    }

}
