package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierPojo {
    String UserId;
    String Mem_SrNo;
    String UniqueId;

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

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLic_Civil_No() {
        return Lic_Civil_No;
    }

    public void setLic_Civil_No(String lic_Civil_No) {
        Lic_Civil_No = lic_Civil_No;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCommercial_Lic_Activity() {
        return Commercial_Lic_Activity;
    }

    public void setCommercial_Lic_Activity(String commercial_Lic_Activity) {
        Commercial_Lic_Activity = commercial_Lic_Activity;
    }

    public String getCommodity() {
        return Commodity;
    }

    public void setCommodity(String commodity) {
        Commodity = commodity;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getAuthorize_Per_Name() {
        return Authorize_Per_Name;
    }

    public void setAuthorize_Per_Name(String authorize_Per_Name) {
        Authorize_Per_Name = authorize_Per_Name;
    }

    public String getRes_Mobile() {
        return Res_Mobile;
    }

    public void setRes_Mobile(String res_Mobile) {
        Res_Mobile = res_Mobile;
    }

    public String getRes_Email() {
        return Res_Email;
    }

    public void setRes_Email(String res_Email) {
        Res_Email = res_Email;
    }

    String Corpcentreby;
    String IpAddress;
    String Source;
    String Command;
    String City;
    String Lic_Civil_No;
    String Address;
    String Commercial_Lic_Activity;
    String Commodity;
    String Company_Name;
    String Authorize_Per_Name;
    String Res_Mobile;
    String Res_Email;

}
