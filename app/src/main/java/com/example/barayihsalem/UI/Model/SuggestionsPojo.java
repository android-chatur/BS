package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuggestionsPojo {
    public String getComplain() {
        return Complain;
    }

    public void setComplain(String complain) {
        Complain = complain;
    }

    public String getQuestion() {
        return Question;
    }

    public String getOpinion() {
        return Opinion;
    }

    public void setOpinion(String opinion) {
        Opinion = opinion;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    String
            UserId;
    String FullName;
    String Email;
    String Complain;
    String Question;
    String Opinion;

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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSuggestions() {
        return Suggestions;
    }

    public void setSuggestions(String suggestions) {
        Suggestions = suggestions;
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

    String Suggestions;
    String Source;
    String Corpcentreby;
    String IpAddress;
    String Command;
}
