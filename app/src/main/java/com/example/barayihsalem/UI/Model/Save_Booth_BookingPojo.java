package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Save_Booth_BookingPojo {

    String
            UserId;
    String Event_SrNo;
    String Phone;
    String Email;
    String FName;
    String LName;
    boolean IsCommitment;
    String Business_Nature,
            Business_License,
            Civilid,
            NoOfTables,
            TablesPrice,
            NoOfChairs,
            ChairsPrice,
            Equipment,
            OtherEquipment,
            Business_Insta,
            TotalPrice,

    Source,
            UniqueId,
            Corpcentreby,
            IpAddress,
            Command;
    List Booth_Trx,
            Booth_Booking_Slot_Trx;

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEvent_SrNo() {
        return Event_SrNo;
    }

    public void setEvent_SrNo(String event_SrNo) {
        Event_SrNo = event_SrNo;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isCommitment() {
        return IsCommitment;
    }

    public void setCommitment(boolean commitment) {
        IsCommitment = commitment;
    }

    public String getBusiness_Nature() {
        return Business_Nature;
    }

    public void setBusiness_Nature(String business_Nature) {
        Business_Nature = business_Nature;
    }

    public String getBusiness_License() {
        return Business_License;
    }

    public void setBusiness_License(String business_License) {
        Business_License = business_License;
    }

    public String getCivilid() {
        return Civilid;
    }

    public void setCivilid(String civilid) {
        Civilid = civilid;
    }

    public String getNoOfTables() {
        return NoOfTables;
    }

    public void setNoOfTables(String noOfTables) {
        NoOfTables = noOfTables;
    }

    public String getTablesPrice() {
        return TablesPrice;
    }

    public void setTablesPrice(String tablesPrice) {
        TablesPrice = tablesPrice;
    }

    public String getNoOfChairs() {
        return NoOfChairs;
    }

    public void setNoOfChairs(String noOfChairs) {
        NoOfChairs = noOfChairs;
    }

    public String getChairsPrice() {
        return ChairsPrice;
    }

    public void setChairsPrice(String chairsPrice) {
        ChairsPrice = chairsPrice;
    }

    public String getEquipment() {
        return Equipment;
    }

    public void setEquipment(String equipment) {
        Equipment = equipment;
    }

    public String getOtherEquipment() {
        return OtherEquipment;
    }

    public void setOtherEquipment(String otherEquipment) {
        OtherEquipment = otherEquipment;
    }

    public String getBusiness_Insta() {
        return Business_Insta;
    }

    public void setBusiness_Insta(String business_Insta) {
        Business_Insta = business_Insta;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
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

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public List getBooth_Trx() {
        return Booth_Trx;
    }

    public void setBooth_Trx(List booth_Trx) {
        Booth_Trx = booth_Trx;
    }

    public List getBooth_Booking_Slot_Trx() {
        return Booth_Booking_Slot_Trx;
    }

    public void setBooth_Booking_Slot_Trx(List booth_Booking_Slot_Trx) {
        Booth_Booking_Slot_Trx = booth_Booking_Slot_Trx;
    }

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

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("RedirectUrl")
    @Expose
    private String redirectUrl;
    @SerializedName("Attribute1")
    @Expose
    private String attribute1;
    @SerializedName("TrackId")
    @Expose
    private String trackId;
}
