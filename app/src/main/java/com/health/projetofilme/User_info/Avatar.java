
package com.health.projetofilme.User_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Avatar {

    @SerializedName("gravatar")
    @Expose
    private Gravatar gravatar;

    public Gravatar getGravatar() {
        return gravatar;
    }

    public void setGravatar(Gravatar gravatar) {
        this.gravatar = gravatar;
    }

}
