package com.example.barayihsalem.UI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Create_Booth_SlotAPIPojo {

    String
            BoothA_XCode;
    String BoothB_XCode;
    String BoothC_XCode;
    String BoothA_Size;
    String BoothB_Size;
    String BoothC_Size;
    List li;
    String CultureId,
            CorpcentreBy;

    public String getBoothA_XCode() {
        return BoothA_XCode;
    }

    public void setBoothA_XCode(String boothA_XCode) {
        BoothA_XCode = boothA_XCode;
    }

    public String getBoothB_XCode() {
        return BoothB_XCode;
    }

    public void setBoothB_XCode(String boothB_XCode) {
        BoothB_XCode = boothB_XCode;
    }

    public String getBoothC_XCode() {
        return BoothC_XCode;
    }

    public void setBoothC_XCode(String boothC_XCode) {
        BoothC_XCode = boothC_XCode;
    }

    public String getBoothA_Size() {
        return BoothA_Size;
    }

    public void setBoothA_Size(String boothA_Size) {
        BoothA_Size = boothA_Size;
    }

    public String getBoothB_Size() {
        return BoothB_Size;
    }

    public void setBoothB_Size(String boothB_Size) {
        BoothB_Size = boothB_Size;
    }

    public String getBoothC_Size() {
        return BoothC_Size;
    }

    public void setBoothC_Size(String boothC_Size) {
        BoothC_Size = boothC_Size;
    }

    public List getLi() {
        return li;
    }

    public void setLi(List li) {
        this.li = li;
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

    public List<com.example.barayihsalem.UI.Model.List> getList() {
        return list;
    }

    public void setList(List<com.example.barayihsalem.UI.Model.List> list) {
        this.list = list;
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

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @SerializedName("list")
    @Expose
    private java.util.List<com.example.barayihsalem.UI.Model.List> list = null;
    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Attribute1")
    @Expose
    private String attribute1;

}
