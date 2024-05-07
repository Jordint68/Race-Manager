
package org.milaifontanals.racemanager.modelsJson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

public class ResponseGetCurses {
    @SerializedName("curses")
    @Expose
    private List<Cursa> curses;
    @SerializedName("status")
    @Expose
    private Response response;

    public List<Cursa> getCurses() {
        return curses;
    }

    public void setCurses(List<Cursa> curses) {
        this.curses = curses;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
