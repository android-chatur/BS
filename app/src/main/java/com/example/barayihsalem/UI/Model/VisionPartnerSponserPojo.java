package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisionPartnerSponserPojo {
    String
            UserId;
    String Mem_SrNo;
    String UniqueId;
    String Corpcentreby;

    @SerializedName("ResponseCode")
    @Expose
    public String responseCode;
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("RedirectUrl")
    @Expose
    public String redirectUrl;
    @SerializedName("Attribute1")
    @Expose
    public Object attribute1;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Object getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(Object attribute1) {
        this.attribute1 = attribute1;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMem_SrNo() {
        return Mem_SrNo;
    }

    public void setMem_SrNo(String mem_SrNo) {
        Mem_SrNo = mem_SrNo;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getCorpcentreby() {
        return Corpcentreby;
    }

    public void setCorpcentreby(String corpcentreby) {
        Corpcentreby = corpcentreby;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getVision_Partner_Type() {
        return Vision_Partner_Type;
    }

    public void setVision_Partner_Type(String vision_Partner_Type) {
        Vision_Partner_Type = vision_Partner_Type;
    }

    public String getVP_Corporate_Name() {
        return VP_Corporate_Name;
    }

    public void setVP_Corporate_Name(String VP_Corporate_Name) {
        this.VP_Corporate_Name = VP_Corporate_Name;
    }

    public String getVP_Representative_Name() {
        return VP_Representative_Name;
    }

    public void setVP_Representative_Name(String VP_Representative_Name) {
        this.VP_Representative_Name = VP_Representative_Name;
    }

    public String getVP_Company_Logo() {
        return VP_Company_Logo;
    }

    public void setVP_Company_Logo(String VP_Company_Logo) {
        this.VP_Company_Logo = VP_Company_Logo;
    }

    public String getVP_Donation_Amount() {
        return VP_Donation_Amount;
    }

    public void setVP_Donation_Amount(String VP_Donation_Amount) {
        this.VP_Donation_Amount = VP_Donation_Amount;
    }

    public String getVP_Mobile_Number() {
        return VP_Mobile_Number;
    }

    public void setVP_Mobile_Number(String VP_Mobile_Number) {
        this.VP_Mobile_Number = VP_Mobile_Number;
    }

    public String getVP_Email() {
        return VP_Email;
    }

    public void setVP_Email(String VP_Email) {
        this.VP_Email = VP_Email;
    }

    public String getShort_Desc() {
        return Short_Desc;
    }

    public void setShort_Desc(String short_Desc) {
        Short_Desc = short_Desc;
    }

    String IpAddress;
    String Source;
    String Vision_Partner_Type;
    String VP_Corporate_Name;
    String VP_Representative_Name;
    String VP_Company_Logo;
    String VP_Donation_Amount;
    String VP_Mobile_Number;
    String VP_Email;
    String Short_Desc;
}
