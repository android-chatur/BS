package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PDFPojo {
    String Value;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCultureId() {
        return CultureId;
    }

    public void setCultureId(String cultureId) {
        CultureId = cultureId;
    }

    public String getCorpcentreBy() {
        return CorpcentreBy;
    }

    public void setCorpcentreBy(String corpcentreBy) {
        CorpcentreBy = corpcentreBy;
    }

    String UserId;
    String CultureId;
    String CorpcentreBy;

    public PDFPojo(String value, String UserId, String CultureId, String CorpcentreBy) {
        this.UserId = UserId;
        this.Value = value;
        this.CultureId = CultureId;
        this.CorpcentreBy = CorpcentreBy;

    }
    @SerializedName("Data")
    @Expose
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
}
