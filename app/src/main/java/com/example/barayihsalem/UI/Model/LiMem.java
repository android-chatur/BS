package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiMem {

    public String getMembershipSrNo() {
        return membershipSrNo;
    }

    public void setMembershipSrNo(String membershipSrNo) {
        this.membershipSrNo = membershipSrNo;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public Boolean getDisable() {
        return isDisable;
    }

    public void setDisable(Boolean disable) {
        isDisable = disable;
    }

    @SerializedName("Membership_SrNo")
    @Expose
    public String membershipSrNo;
    @SerializedName("Membership_Name")
    @Expose
    public String membershipName;
    @SerializedName("IsDisable")
    @Expose
    public Boolean isDisable;
}
