package com.example.barayihsalem.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {
    @SerializedName("SrNo")
    @Expose
    public String srNo;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNoOf() {
        return noOf;
    }
    @SerializedName("Count")
    @Expose
    private Integer count;
    public void setNoOf(Integer noOf) {
        this.noOf = noOf;
    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public Integer getBoothNo() {
        return boothNo;
    }

    public void setBoothNo(Integer boothNo) {
        this.boothNo = boothNo;
    }

    @SerializedName("BoothNo")
    @Expose
    private Integer boothNo;





    @SerializedName("Person_Name")
    @Expose
    private String personName;
    @SerializedName("Mobile")
    @Expose
    private String mobile;





    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("NoOf")
    @Expose
    private Integer noOf;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    @SerializedName("Event_Name")
    @Expose
    private String eventName;


    @SerializedName("Store_type")
    @Expose
    private String storeType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("URL")
    @Expose
    private String url;

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getItemExtraDetails() {
        return itemExtraDetails;
    }

    public void setItemExtraDetails(String itemExtraDetails) {
        this.itemExtraDetails = itemExtraDetails;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @SerializedName("CartId")
    @Expose
    private Integer cartId;

    @SerializedName("Sr")
    @Expose
    private Integer sr;
    @SerializedName("Event_SrNo")
    @Expose
    private String eventSrNo;

    @SerializedName("CommunityName")
    @Expose
    private String communityName;
    @SerializedName("Date")
    @Expose
    private String date;

    @SerializedName("ItemXCode")
    @Expose
    private String itemXCode;




    @SerializedName("Mem_SrNo")
    @Expose
    private String memSrNo;
    @SerializedName("Mem_Name")
    @Expose
    private String memName;

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

    public String getMemTypeName() {
        return memTypeName;
    }

    public void setMemTypeName(String memTypeName) {
        this.memTypeName = memTypeName;
    }

    public String getMemFinishDate() {
        return memFinishDate;
    }

    public void setMemFinishDate(String memFinishDate) {
        this.memFinishDate = memFinishDate;
    }

    public String getMembershipTermPaid() {
        return membershipTermPaid;
    }

    public void setMembershipTermPaid(String membershipTermPaid) {
        this.membershipTermPaid = membershipTermPaid;
    }

    public String getMembershipTermRemaining() {
        return membershipTermRemaining;
    }

    public void setMembershipTermRemaining(String membershipTermRemaining) {
        this.membershipTermRemaining = membershipTermRemaining;
    }

    public Integer getIsShowBtn() {
        return isShowBtn;
    }

    public void setIsShowBtn(Integer isShowBtn) {
        this.isShowBtn = isShowBtn;
    }

    @SerializedName("Mem_Type_Name")
    @Expose
    private String memTypeName;
    @SerializedName("Mem_FinishDate")
    @Expose
    private String memFinishDate;
    @SerializedName("MembershipTerm_Paid")
    @Expose
    private String membershipTermPaid;
    @SerializedName("MembershipTerm_Remaining")
    @Expose
    private String membershipTermRemaining;
    @SerializedName("IsShowBtn")
    @Expose
    private Integer isShowBtn;


    public String getItemXCode() {
        return itemXCode;
    }

    public void setItemXCode(String itemXCode) {
        this.itemXCode = itemXCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRatingSrNo() {
        return ratingSrNo;
    }

    public void setRatingSrNo(String ratingSrNo) {
        this.ratingSrNo = ratingSrNo;
    }

    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("Item_Extra_Details")
    @Expose
    private String itemExtraDetails;
/*    @SerializedName("Price")
    @Expose
    private Double price;*/
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Image1")
    @Expose
    private String image1;
    @SerializedName("Store_Name")
    @Expose
    private String storeName;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("RatingSrNo")
    @Expose
    private String ratingSrNo;
    @SerializedName("Rating_Star")
    @Expose
    private Double ratingStar;
    @SerializedName("Ratings")
    @Expose
    private Integer ratings;


    @SerializedName("Quantity")
    @Expose
    private Integer quantity;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @SerializedName("MenuName")
    @Expose
    private String menuName;
    @SerializedName("Content")
    @Expose
    private String content;



    @SerializedName("EventType")
    @Expose
    private String eventType;
    @SerializedName("Extra_Name")
    @Expose
    private String extraName;
    public String getStoreSrNo() {
        return storeSrNo;
    }

    public void setStoreSrNo(String storeSrNo) {
        this.storeSrNo = storeSrNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreDeliveryTime() {
        return storeDeliveryTime;
    }

    public void setStoreDeliveryTime(Integer storeDeliveryTime) {
        this.storeDeliveryTime = storeDeliveryTime;
    }

    public String getTimeInWords() {
        return timeInWords;
    }

    public void setTimeInWords(String timeInWords) {
        this.timeInWords = timeInWords;
    }

    public Double getStoreMinOrder() {
        return storeMinOrder;
    }

    public void setStoreMinOrder(Double storeMinOrder) {
        this.storeMinOrder = storeMinOrder;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public Double getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(Double ratingStar) {
        this.ratingStar = ratingStar;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    @SerializedName("EventDate")
    @Expose
    private String eventDate;


    @SerializedName("Store_SrNo")
    @Expose
    private String storeSrNo;

    @SerializedName("Store_Delivery_Time")
    @Expose
    private Integer storeDeliveryTime;
    @SerializedName("Time_In_Words")
    @Expose
    private String timeInWords;
    @SerializedName("Store_MinOrder")
    @Expose
    private Double storeMinOrder;

    @SerializedName("Store_Logo")
    @Expose
    private String storeLogo;

    @SerializedName("OldPrice")
    @Expose
    private Double oldPrice;
    @SerializedName("NewPrice")
    @Expose
    private Double newPrice;

    @SerializedName("Ingredients")
    @Expose
    private String ingredients;



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @SerializedName("Color")
    @Expose
    public String color;

    public String getxCode1() {
        return xCode1;
    }

    public void setxCode1(String xCode1) {
        this.xCode1 = xCode1;
    }

    @SerializedName("XCode1")
    @Expose
    private String xCode1;



    @SerializedName("Headline")
    @Expose
    public String headline;
    @SerializedName("IsConfirmed")
    @Expose
    public Boolean isConfirmed;
    @SerializedName("IsConfirmed_Status")
    @Expose
    public String isConfirmedStatus;

    @SerializedName("IsApproval")
    @Expose
    public Boolean isApproval;
    @SerializedName("IsApproval_Status")
    @Expose
    public String isApprovalStatus;
    @SerializedName("XCode")
    @Expose
    public String xCode;
    @SerializedName("XName")
    @Expose
    public String xName;
    @SerializedName("Benefits")
    @Expose
    public String benefits;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @SerializedName("Price")
    @Expose
    public Float price;

    public String getEventSrNo() {
        return eventSrNo;
    }

    public void setEventSrNo(String eventSrNo) {
        this.eventSrNo = eventSrNo;
    }

    @SerializedName("Header")
    @Expose
    public String header;
    @SerializedName("Description_Short")
    @Expose
    public String descriptionShort;
    @SerializedName("Author")
    @Expose
    public String author;
    @SerializedName("News_Date")
    @Expose
    public String newsDate;
    @SerializedName("Image")
    @Expose
    public String image;



    public String getBannerCategoryImg() {
        return bannerCategoryImg;
    }

    public void setBannerCategoryImg(String bannerCategoryImg) {
        this.bannerCategoryImg = bannerCategoryImg;
    }

    public String getxLink() {
        return xLink;
    }

    public void setxLink(String xLink) {
        this.xLink = xLink;
    }

    @SerializedName("XLink")
    @Expose
    private String xLink;





    boolean isSelected=false;

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @SerializedName("Banner_Category_Img")
    @Expose
    private String bannerCategoryImg;

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getIsConfirmedStatus() {
        return isConfirmedStatus;
    }

    public void setIsConfirmedStatus(String isConfirmedStatus) {
        this.isConfirmedStatus = isConfirmedStatus;
    }

    public Boolean getApproval() {
        return isApproval;
    }

    public void setApproval(Boolean approval) {
        isApproval = approval;
    }

    public String getIsApprovalStatus() {
        return isApprovalStatus;
    }

    public void setIsApprovalStatus(String isApprovalStatus) {
        this.isApprovalStatus = isApprovalStatus;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getSr() {
        return sr;
    }

    public void setSr(Integer sr) {
        this.sr = sr;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }




    @SerializedName("Ordering")
    @Expose
    private Integer ordering;
    @SerializedName("EventBoothTrx_SrNo")
    @Expose
    private String eventBoothTrxSrNo;
    @SerializedName("EventBoothTrx_Sr")
    @Expose
    private Integer eventBoothTrxSr;

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getEventBoothTrxSrNo() {
        return eventBoothTrxSrNo;
    }

    public void setEventBoothTrxSrNo(String eventBoothTrxSrNo) {
        this.eventBoothTrxSrNo = eventBoothTrxSrNo;
    }

    public Integer getEventBoothTrxSr() {
        return eventBoothTrxSr;
    }

    public void setEventBoothTrxSr(Integer eventBoothTrxSr) {
        this.eventBoothTrxSr = eventBoothTrxSr;
    }

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

    public String getBoothType() {
        return boothType;
    }

    public void setBoothType(String boothType) {
        this.boothType = boothType;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsBook() {
        return isBook;
    }

    public void setIsBook(Integer isBook) {
        this.isBook = isBook;
    }

    @SerializedName("Zone")
    @Expose
    private String zone;
    @SerializedName("Zone_Name")
    @Expose
    private String zoneName;
    @SerializedName("BoothType")
    @Expose
    private String boothType;
    @SerializedName("BoothName")
    @Expose
    private String boothName;

    @SerializedName("Commission")
    @Expose
    private Double commission;
    @SerializedName("TotalPrice")
    @Expose
    private Double totalPrice;
    @SerializedName("IsBook")
    @Expose
    private Integer isBook;





}
