package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResidentrPojo {

    String
            UserId;

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

    public String getRes_Nationality() {
        return Res_Nationality;
    }

    public void setRes_Nationality(String res_Nationality) {
        Res_Nationality = res_Nationality;
    }

    public String getRes_DOB() {
        return Res_DOB;
    }

    public void setRes_DOB(String res_DOB) {
        Res_DOB = res_DOB;
    }

    public String getRes_Gender() {
        return Res_Gender;
    }

    public void setRes_Gender(String res_Gender) {
        Res_Gender = res_Gender;
    }

    String Mem_SrNo;
    String UniqueId;
    String Corpcentreby;
    String IpAddress;
    String Source;
    String Command;
    String City;
    String Lic_Civil_No;
    String Address;
    String Res_Mobile;
    String Res_Email;
    String Res_Nationality;
    String Res_DOB;

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

    String Res_Gender;
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
}
