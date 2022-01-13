package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getApprove() {
        return isApprove;
    }

    public void setApprove(Boolean approve) {
        isApprove = approve;
    }
    @SerializedName("Url")
    @Expose
    private String url;

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Sr")
    @Expose
    public Integer sr;
    @SerializedName("IsApprove")
    @Expose
    private Boolean isApprove;
    public EventList getEventList() {
        return eventList;
    }

    public void setEventList(EventList eventList) {
        this.eventList = eventList;
    }

    public java.util.List<EventGallery> getEventGalleryList() {
        return eventGalleryList;
    }

    public void setEventGalleryList(java.util.List<EventGallery> eventGalleryList) {
        this.eventGalleryList = eventGalleryList;
    }

    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Description")
    @Expose
    public String description;
    @SerializedName("Ordering")
    @Expose
    public Integer ordering;
    @SerializedName("Mem_SrNo")
    @Expose
    public String memSrNo;
    @SerializedName("Mem_Name")
    @Expose
    public String memName;
    @SerializedName("Mem_Color")
    @Expose
    public String memColor;
    @SerializedName("Mem_Background_Color")
    @Expose
    public String memBackgroundColor;
    @SerializedName("Logo_Color")
    @Expose
    public String logoColor;
    @SerializedName("Txt_Color")
    @Expose
    public String txtColor;
    @SerializedName("XCode")
    @Expose
    public String xCode;
    @SerializedName("XName")
    @Expose
    public String xName;
    @SerializedName("Image")
    @Expose
    public String image;

    @SerializedName("Zone")
    @Expose
    private String zone;
    @SerializedName("Zone_Name")
    @Expose
    private String zoneName;

    @SerializedName("Event_List")
    @Expose
    private EventList eventList;
    @SerializedName("Event_Gallery_List")
    @Expose
    private java.util.List<EventGallery> eventGalleryList = null;



    @SerializedName("Booth")
    @Expose
    private String booth;
    @SerializedName("Booth_Name")
    @Expose
    private String boothName;
    @SerializedName("No")
    @Expose
    private String no;
    @SerializedName("Price")
    @Expose
    private String price;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getBooth() {
        return booth;
    }

    public void setBooth(String booth) {
        this.booth = booth;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSr() {
        return sr;
    }

    public void setSr(Integer sr) {
        this.sr = sr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getMemSrNo() {
        return memSrNo;
    }

    public void setMemSrNo(String memSrNo) {
        this.memSrNo = memSrNo;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemColor() {
        return memColor;
    }

    public void setMemColor(String memColor) {
        this.memColor = memColor;
    }

    public String getMemBackgroundColor() {
        return memBackgroundColor;
    }

    public void setMemBackgroundColor(String memBackgroundColor) {
        this.memBackgroundColor = memBackgroundColor;
    }

    public String getLogoColor() {
        return logoColor;
    }

    public void setLogoColor(String logoColor) {
        this.logoColor = logoColor;
    }

    public String getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
    }

    public String getxCode() {
        return xCode;
    }

    public void setxCode(String xCode) {
        this.xCode = xCode;
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
