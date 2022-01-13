package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiComm {

    @SerializedName("Community_SrNo")
    @Expose
    public String communitySrNo;

    public String getCommunitySrNo() {
        return communitySrNo;
    }

    public void setCommunitySrNo(String communitySrNo) {
        this.communitySrNo = communitySrNo;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Boolean getDisable() {
        return isDisable;
    }

    public void setDisable(Boolean disable) {
        isDisable = disable;
    }

    @SerializedName("Community_Name")
    @Expose
    public String communityName;
    @SerializedName("IsDisable")
    @Expose
    public Boolean isDisable;
}
