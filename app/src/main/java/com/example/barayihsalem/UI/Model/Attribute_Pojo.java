package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attribute_Pojo {
    boolean IsChange;

    public List<com.example.barayihsalem.UI.Model.Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<com.example.barayihsalem.UI.Model.Size> sizeList) {
        this.sizeList = sizeList;
    }

    public List<com.example.barayihsalem.UI.Model.Pattern> getPatternList() {
        return patternList;
    }

    public void setPatternList(List<com.example.barayihsalem.UI.Model.Pattern> patternList) {
        this.patternList = patternList;
    }

    public List<com.example.barayihsalem.UI.Model.Style> getStyleList() {
        return styleList;
    }

    public void setStyleList(List<com.example.barayihsalem.UI.Model.Style> styleList) {
        this.styleList = styleList;
    }

    String

            ItemXCode;

    @SerializedName("Color_List")
    @Expose
    private List<Color> colorList = null;
    @SerializedName("Size_List")
    @Expose
    private List<Size> sizeList = null;
    @SerializedName("Pattern_List")
    @Expose
    private List<Pattern> patternList = null;
    @SerializedName("Style_List")
    @Expose
    private List<Style> styleList = null;
    @SerializedName("Attr_Prod_Details")
    @Expose
    private AttrProdDetails attrProdDetails;
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









    public boolean isChange() {
        return IsChange;
    }

    public void setChange(boolean change) {
        IsChange = change;
    }

    public String getItemXCode() {
        return ItemXCode;
    }

    public void setItemXCode(String itemXCode) {
        ItemXCode = itemXCode;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public AttrProdDetails getAttrProdDetails() {
        return attrProdDetails;
    }

    public void setAttrProdDetails(AttrProdDetails attrProdDetails) {
        this.attrProdDetails = attrProdDetails;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getPattern() {
        return Pattern;
    }

    public void setPattern(String pattern) {
        Pattern = pattern;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getValue1() {
        return Value1;
    }

    public void setValue1(String value1) {
        Value1 = value1;
    }

    public String getValue2() {
        return Value2;
    }

    public void setValue2(String value2) {
        Value2 = value2;
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

    String ItemType;
    String Color;
    String Size;
    String Pattern;
    String Style;
    String Value1;

    public List<com.example.barayihsalem.UI.Model.Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<com.example.barayihsalem.UI.Model.Color> colorList) {
        this.colorList = colorList;
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

    String Value2;
    String UserId;
    String CultureId;
    String CorpcentreBy;



}
