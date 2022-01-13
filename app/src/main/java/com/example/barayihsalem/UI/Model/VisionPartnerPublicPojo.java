package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisionPartnerPublicPojo {
    String UserId;
    String Mem_SrNo;

    public String getLogo_Image_Base64_String() {
        return Logo_Image_Base64_String;
    }

    public void setLogo_Image_Base64_String(String logo_Image_Base64_String) {
        Logo_Image_Base64_String = logo_Image_Base64_String;
    }

    String UniqueId;
    String Corpcentreby;
    String Logo_Image_Base64_String;

    @SerializedName("ResponseCode")
    @Expose
    public String responseCode;

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

    public String getVision_Partner_Type() {
        return Vision_Partner_Type;
    }

    public void setVision_Partner_Type(String vision_Partner_Type) {
        Vision_Partner_Type = vision_Partner_Type;
    }

    public String getVP_AssociationName() {
        return VP_AssociationName;
    }

    public void setVP_AssociationName(String VP_AssociationName) {
        this.VP_AssociationName = VP_AssociationName;
    }

    public String getVP_RepresentativeName() {
        return VP_RepresentativeName;
    }

    public void setVP_RepresentativeName(String VP_RepresentativeName) {
        this.VP_RepresentativeName = VP_RepresentativeName;
    }

    public String getVP_Representative_Email() {
        return VP_Representative_Email;
    }

    public void setVP_Representative_Email(String VP_Representative_Email) {
        this.VP_Representative_Email = VP_Representative_Email;
    }

    public String getLogo_Image() {
        return Logo_Image;
    }

    public void setLogo_Image(String logo_Image) {
        Logo_Image = logo_Image;
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
    String VP_AssociationName;
    String VP_RepresentativeName;
    String VP_Representative_Email;
    String Logo_Image;
    String Short_Desc;

}
