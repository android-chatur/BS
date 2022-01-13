package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreTimeInfo {
    public String getDaysName() {
        return daysName;
    }

    public void setDaysName(String daysName) {
        this.daysName = daysName;
    }

    public String getDaysTime() {
        return daysTime;
    }

    public void setDaysTime(String daysTime) {
        this.daysTime = daysTime;
    }

    @SerializedName("Days_Name")
    @Expose
    private String daysName;
    @SerializedName("Days_Time")
    @Expose
    private String daysTime;
}
