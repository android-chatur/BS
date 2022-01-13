package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Create_Event_Pojo {
    String
            UserId;
    String Comm_SrNo;
    String EventType;
    String BoothType_A;
    String BoothType_A_Size;
    String BoothType_B;

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    String BoothType_B_Size;
    Double Price;

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
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

    public String getTrackId() {
        return trackId;
    }

    @SerializedName("Attribute1")
    @Expose
    private String attribute1;
    @SerializedName("TrackId")
    @Expose
    private String trackId;


;

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




    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getComm_SrNo() {
        return Comm_SrNo;
    }

    public void setComm_SrNo(String comm_SrNo) {
        Comm_SrNo = comm_SrNo;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getBoothType_A() {
        return BoothType_A;
    }

    public void setBoothType_A(String boothType_A) {
        BoothType_A = boothType_A;
    }

    public String getBoothType_A_Size() {
        return BoothType_A_Size;
    }

    public void setBoothType_A_Size(String boothType_A_Size) {
        BoothType_A_Size = boothType_A_Size;
    }

    public String getBoothType_B() {
        return BoothType_B;
    }

    public void setBoothType_B(String boothType_B) {
        BoothType_B = boothType_B;
    }

    public String getBoothType_B_Size() {
        return BoothType_B_Size;
    }

    public void setBoothType_B_Size(String boothType_B_Size) {
        BoothType_B_Size = boothType_B_Size;
    }

    public String getBoothType_C() {
        return BoothType_C;
    }

    public void setBoothType_C(String boothType_C) {
        BoothType_C = boothType_C;
    }

    public String getBoothType_C_Size() {
        return BoothType_C_Size;
    }

    public void setBoothType_C_Size(String boothType_C_Size) {
        BoothType_C_Size = boothType_C_Size;
    }

    public String getEventSize_Zone() {
        return EventSize_Zone;
    }

    public void setEventSize_Zone(String eventSize_Zone) {
        EventSize_Zone = eventSize_Zone;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    public String getSDate() {
        return SDate;
    }

    public void setSDate(String SDate) {
        this.SDate = SDate;
    }

    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public String getEventPlanner_Mobile() {
        return EventPlanner_Mobile;
    }

    public void setEventPlanner_Mobile(String eventPlanner_Mobile) {
        EventPlanner_Mobile = eventPlanner_Mobile;
    }

    public String getEventPlanner_Email() {
        return EventPlanner_Email;
    }

    public void setEventPlanner_Email(String eventPlanner_Email) {
        EventPlanner_Email = eventPlanner_Email;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
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

    String BoothType_C;
    String BoothType_C_Size;
    String EventSize_Zone;
    String Days;
    String SDate;
    String EDate;
    String EventPlanner_Mobile;
    String EventPlanner_Email;
    String IBAN;
    String Source;
    String Corpcentreby;
    String IpAddress;
    String Command;

}
