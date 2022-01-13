package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Obj {

    @SerializedName("li_Mem")
    @Expose
    public List<LiMem> liMem = null;
    @SerializedName("li_Comm")
    @Expose
    public List<LiComm> liComm = null;
}
