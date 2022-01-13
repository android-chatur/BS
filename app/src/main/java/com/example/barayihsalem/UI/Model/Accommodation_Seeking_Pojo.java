package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accommodation_Seeking_Pojo {

    String
            UserId;
    String Mem_SrNo;
    String Area;
    String Gender;
    String Nationality;

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

    String BudgetRange;
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

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getBudgetRange() {
        return BudgetRange;
    }

    public void setBudgetRange(String budgetRange) {
        BudgetRange = budgetRange;
    }

    public String getRoomType1() {
        return RoomType1;
    }

    public void setRoomType1(String roomType1) {
        RoomType1 = roomType1;
    }

    public String getRoomType2() {
        return RoomType2;
    }

    public void setRoomType2(String roomType2) {
        RoomType2 = roomType2;
    }

    public String getRoomType3() {
        return RoomType3;
    }

    public void setRoomType3(String roomType3) {
        RoomType3 = roomType3;
    }

    public String getSharingType() {
        return SharingType;
    }

    public void setSharingType(String sharingType) {
        SharingType = sharingType;
    }

    public String getReq_Box() {
        return Req_Box;
    }

    public void setReq_Box(String req_Box) {
        Req_Box = req_Box;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
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

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }

    String RoomType1;
    String RoomType2;
    String RoomType3;
    String SharingType;
    String Req_Box;
    String Source;

    String Corpcentreby;
    String IpAddress;
    String Command;
    boolean IsActive,
            IsDelete;
}
