package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Zone {

    @SerializedName("Zone")
    @Expose
    private String zone;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public List<LiZone> getLiZone() {
        return liZone;
    }

    public void setLiZone(List<LiZone> liZone) {
        this.liZone = liZone;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }


    @SerializedName("Zone_Name")
    @Expose
    private String zoneName;
    @SerializedName("li_Zone")
    @Expose
    private java.util.List<LiZone> liZone = null;
}
