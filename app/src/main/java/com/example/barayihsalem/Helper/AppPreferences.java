package com.example.barayihsalem.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private Context context;

    public AppPreferences(Context context) {
        this.context = context;
    }


    public void save_lang(String lang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", lang);
        editor.commit();
    }


    public String get_lang() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("lang", "");
    }

    public void savePaymethod(String Paymethod) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Paymethod", Paymethod);
        editor.commit();
    }

    public String getPaymethod() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Paymethod", "");
    }

    public void saveCulturemode(String Culturemode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Culturemode", Culturemode);
        editor.commit();
    }

    public String getCulturemode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Culturemode", "1");

    }

    public void save_Attribute1(String Attribute1) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Attribute1", Attribute1);
        editor.commit();
    }

    public String get_Attribute1() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Attribute1", "");

    }

    public void save_TrackEvent(String TrackEvent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TrackEvent", TrackEvent);
        editor.commit();
    }

    public String get_TrackEvent() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("TrackEvent", "");

    }
  public void save_Attribute_Book(String Book) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Book", Book);
        editor.commit();
    }

    public String get_Attribute_Book() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Book", "");

    }



    public void save_trackid_Book(String trackid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("trackid", trackid);
        editor.commit();
    }

    public String get_trackid_Book() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("trackid", "");

    }


    public void save_Attribute_Supp(String Attribute_Supp) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Attribute_Supp", Attribute_Supp);
        editor.commit();
    }

    public String get_Attribute_Supp() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Attribute_Supp", "");

    }

    public void save_Attribute_mem(String Attribute_mem) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Attribute_mem", Attribute_mem);
        editor.commit();
    }

    public String get_Attribute_mem() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Attribute_mem", "");

    }

    public void saveComingEventSRNO(String ComingEventSRNO) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ComingEventSRNO", ComingEventSRNO);
        editor.commit();
    }

    public String get_ComingEventSRNO() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("ComingEventSRNO", "");

    }


    public void save_track_id(String track_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("track_id", track_id);
        editor.commit();
    }

    public String get_track_id() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("track_id", "");

    }
 public void save_track_idBO(String idBO) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idBO", idBO);
        editor.commit();
    }

    public String get_track_idBO() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("idBO", "");

    }


    public void save_checkout(String checkout) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("checkout", checkout);
        editor.commit();
    }

    public String get_checkout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("checkout", "");

    }

    public void save_track_id_address(String track_id_address) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("track_id_address", track_id_address);
        editor.commit();
    }

    public String get_track_id_address() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("track_id_address", "");

    }

    public void savecartcnt(int cartcnt) {
        SharedPreferences sp = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("cartcnt", cartcnt);
        editor.commit();
    }

    public int getcartcnt() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getInt("cartcnt", 0);
    }

    public void saveshipcnt(float cartcnt1) {
        SharedPreferences sp = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("shpcnt", cartcnt1);
        editor.commit();
    }

    public float getshipcnt() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getFloat("shpcnt", 0);
    }


    public void save_conpany_id(String company_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("company_id", company_id);
        editor.commit();
    }

    public String get_company_id() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("company_id", "");
    }


    public void saveCategory(String category) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("category", category);
        editor.commit();
    }

    public String getCategory() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("category", "");
    }


    public void SaveNewsSrno(String NewsSrno) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NewsSrno", NewsSrno);
        editor.commit();
    }

    public String getNewsSrno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("NewsSrno", "");
    }

    public void SaveShoppingSrno(String Shopping) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Shopping", Shopping);
        editor.commit();
    }

    public String geShoppingSrno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Shopping", "");
    }


    public void SaveRetailSrno(String RetailSrno) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("RetailSrno", RetailSrno);
        editor.commit();
    }

    public String geRetailSrno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("RetailSrno", "");
    }


    public void SaveRetailname(String Retailname) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Retailname", Retailname);
        editor.commit();
    }

    public String geRetailname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Retailname", "");
    }


    public void SaveStore_SrNo(String Store_SrNo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Store_SrNo", Store_SrNo);
        editor.commit();
    }

    public String geStore_SrNo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Store_SrNo", "");
    }

    public void SaveBrand_SrNo(String Brand_SrNo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Brand_SrNo", Brand_SrNo);
        editor.commit();
    }

    public String getBrand_SrNo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Brand_SrNo", "");
    }

 public void SaveBrand_Name(String Brand_Name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Brand_Name", Brand_Name);
        editor.commit();
    }

    public String getBrand_Name() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Brand_Name", "");
    }

    public void SaveMenu_xcode(String Menu_Sxcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Menu_Sxcode", Menu_Sxcode);
        editor.commit();
    }

    public String getMenu_Sxcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Menu_Sxcode", "");
    }

    public void SaveItem_xcode(String Item_xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Item_xcode", Item_xcode);
        editor.commit();
    }

    public String getItem_xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Item_xcode", "");
    }

    public void SaveMenu_xName(String Menu_xName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Menu_xName", Menu_xName);
        editor.commit();
    }

    public String getMenu_xName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Menu_xName", "");
    }

    public void SaveCuisines_Xcode(String Cuisines_Xcod) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Cuisines_Xcod", Cuisines_Xcod);
        editor.commit();
    }

    public String geCuisines_Xcod() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Cuisines_Xcod" +
                "", "");
    }


    public void saveServiceXcode(String ServiceXcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ServiceXcode", ServiceXcode);
        editor.commit();
    }

    public String getServiceXcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("ServiceXcode", "");
    }


    public void saveDepartment(String Department) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Department", Department);
        editor.commit();
    }

    public String getDepartment() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Department", "");
    }


    public void saveDepartment_name(String Department_name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Department_name", Department_name);
        editor.commit();
    }

    public String get_Department_name() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Department_name", "");
    }


    public void saveDepartmentTitle(String title) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("title", title);
        editor.commit();
    }

    public String getDepartmentTitle() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("title", "");
    }


    public void saveSubDepartment(String SubDepartment) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SubDepartment", SubDepartment);
        editor.commit();
    }


    public String getSubDepartment() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("SubDepartment", "");
    }

    public void saveCategoryTitle(String category_title) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("category_title", category_title);
        editor.commit();
    }

    public String getCategoryTitle() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("category_title", "");
    }


    public void savewishcnt(int wishcnt) {
        SharedPreferences sp = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("wishcnt", wishcnt);
        editor.commit();
    }

    public int getwishcnt() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getInt("wishcnt", 0);
    }

    public void savefirsttime(String firsttime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firsttime", firsttime);
        editor.commit();
    }

    public void save_sinup_xcode(String sinup_xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sinup_xcode", sinup_xcode);
        editor.commit();
    }

    public String get_sinup_xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("sinup_xcode", "");
    }

    public void saveIsAddressExist(String Address) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Address", Address);
        editor.commit();
    }

    public String getIsAddressExist() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Address", "");
    }

    public void save_Fname(String firstname) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstname", firstname);
        editor.commit();
    }

    public void savesuggetion(String lnamee) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastnameupdate", lnamee);
        editor.commit();
    }

    public String getsuggetion() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("lastnameupdate", "");
    }


    public String getfirstname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("firstname", "");
    }

    public void savesubxcode(String subxcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("subxcode", subxcode);
        editor.commit();
    }

    public String getsubxcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("subxcode", "");
    }

    public void saveheadername(String headername) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("headername", headername);
        editor.commit();
    }

    public String getheadername() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("headername", "");
    }

    public void saveitemtype(String itemtype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("itemtype", itemtype);
        editor.commit();
    }

    public String getitemtype() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("itemtype", "");
    }


    public void savehompage_xcode(String hompage_xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hompage_xcode", hompage_xcode);
        editor.commit();
    }

    public String get_hompage_xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("hompage_xcode", "");
    }


    public void saveprodtype(String prodtype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("prodtype", prodtype);
        editor.commit();
    }

    public String getprodtype() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("prodtype", "");
    }

    public void savexname(String xname) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("xname", xname);
        editor.commit();
    }

    public String getxname1() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("xname", "");
    }

    public void savexcode(String xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("xcode", xcode);
        editor.commit();
    }

    public String getxcode1() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("xcode", "");
    }

    public void savefromgcheckout(String fromgcheckout) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fromgcheckout", fromgcheckout);
        editor.commit();
    }

    public String getfromgcheckout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("fromgcheckout", "");
    }

   /* public void saveTrackid(String Trackid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Trackid", Trackid);
        editor.commit();
    }

    public String getTrackid() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Trackid", "");
    }*/

    public void savefAddress(String fAddress) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fAddress", fAddress);
        editor.commit();
    }

    public String getfAddress() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("fAddress", "");
    }

    public void savefday(String fday) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fday", fday);
        editor.commit();
    }

    public String getfday() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("fday", "");
    }

    public void saveftime(String ftime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ftime", ftime);
        editor.commit();
    }

    public String getftime() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("ftime", "");
    }

    public void saveCountryid(String Countryid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Countryid", Countryid);
        editor.commit();
    }

    public String getCountryid() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Countryid", "");
    }

    public void savepass(String pass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pass", pass);
        editor.commit();
    }

    public String getpass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("pass", "");
    }

    public void saveuserid(String userid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userid", userid);
        editor.commit();
    }

    public String getuserid() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("userid", "0");
    }

    public void savelastname(String lastname) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastname", lastname);
        editor.commit();
    }

    public String getlastname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("lastname", "");
    }


    public void save_mob(String mobe) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mobe", mobe);
        editor.commit();
    }

    public String get_mobe() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("mobe", "");
    }


    public void save_Currency_code(String Currency_code) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Currency_code", Currency_code);
        editor.commit();
    }

    public String get_Currency_code() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Currency_code", "");
    }


    public void Save_cellby(String cellby) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cellby", cellby);
        editor.commit();
    }

    public String get_cellby() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("cellby", "");
    }


    public void save_Currency_Amount(String Currency_Amount) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Currency_Amount", Currency_Amount);
        editor.commit();
    }

    public String get_Currency_Amount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Currency_Amount", "");
    }

    public void save_citiname(String citiname) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("citiname", citiname);
        editor.commit();
    }

    public String get_citiname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("citiname", "");
    }

    public void save_Exchangecode(String Exchangecode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Exchangecode", Exchangecode);
        editor.commit();
    }

    public String get_exchangecode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Exchangecode", "");
    }

    public void save_ExchangeRate(String ExchangeRate) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ExchangeRate", ExchangeRate);
        editor.commit();
    }

    public String get_exchangeRate() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("ExchangeRate", "");
    }

    public void save_Commission(String Commission) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Commission", Commission);
        editor.commit();
    }

    public String get_commission() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Commission", "");
    }

    public void save_AmountKWD(String AmountKWD) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AmountKWD", AmountKWD);
        editor.commit();
    }

    public String get_amountKWD() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("AmountKWD", "");
    }

    public void save_Color(String Color) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Color", Color);
        editor.commit();
    }

    public String get_Color() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Color", "");
    }

    public void save_commu_color(String commu_color) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("commu_color", commu_color);
        editor.commit();
    }

    public String get_commu_color() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("commu_color", "");
    }


    public void save_Bs_newsBack(String Bs_newsBack) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Bs_newsBack", Bs_newsBack);
        editor.commit();
    }

    public String get_Bs_newsBack() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Bs_newsBack", "");
    }

    public void save_Civil_id(String appartment) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("appartment", appartment);
        editor.commit();
    }

    public String get_getcivilid() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("appartment", "");
    }


    public void save_showhompage(String showhompage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("showhompage", showhompage);
        editor.commit();
    }

    public String get_showhompage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("showhompage", "");
    }

    public void save_membership_srno(String membership_srno) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("membership_srno", membership_srno);
        editor.commit();
    }

    public String get_membership_srno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("membership_srno", "");
    }


    public void save_Community_srno(String Community) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Community", Community);
        editor.commit();
    }

    public String get_Community_srno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Community", "");
    }


    public void save_Commu_Xcode(String Commu_Xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Commu_Xcode", Commu_Xcode);
        editor.commit();
    }

    public String get_Commu_Xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Commu_Xcode", "");
    }

    public void save_Nationality(String Nationality) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Nationality", Nationality);
        editor.commit();
    }

    public String get_Nationality() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Nationality", "");
    }

    public void save_DOB(String DOB) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("DOB", DOB);
        editor.commit();
    }

    public String get_DOB() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("DOB", "");
    }

    public void save_UserImage(String UserImage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserImage", UserImage);
        editor.commit();
    }

    public String get_UserImage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("UserImage", "");
    }

    public void save_CivilIdFrontImage(String CivilIdFrontImage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CivilIdFrontImage", CivilIdFrontImage);
        editor.commit();
    }

    public String get_CivilIdFrontImage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("CivilIdFrontImage", "");
    }

    public void save_CivilIdBackImage(String CivilIdBackImage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CivilIdBackImage", CivilIdBackImage);
        editor.commit();
    }

    public String get_CivilIdBackImage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("CivilIdBackImage", "");
    }


    public void savetimesrno(String timesrno) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("timesrno", timesrno);
        editor.commit();
    }

    public String gettimesrno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("timesrno", "");
    }

    public void saveshippingdate(String shippingdate) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("shippingdate", shippingdate);
        editor.commit();
    }

    public String getshippingdate() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("shippingdate", "");
    }

    public void savetimeslot(String timeslot) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("timeslot", timeslot);
        editor.commit();
    }

    public String gettimeslot() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("timeslot", "");
    }

    public void savefromtime(String fromtime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fromtime", fromtime);
        editor.commit();
    }

    public String getfromtime() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("fromtime", "");
    }

    public void savefromtime24(String fromtime24) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fromtime24", fromtime24);
        editor.commit();
    }

    public String getfromtime24() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("fromtime24", "");
    }

    public void savetotime(String totime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("totime", totime);
        editor.commit();
    }

    public String gettotime() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("totime", "");
    }

    public void savetotime24(String totime24) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("totime24", totime24);
        editor.commit();
    }

    public String gettotime24() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("totime24", "");
    }

    public void savecontry(String contry) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("contry", contry);
        editor.commit();
    }

    public String getcontry() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("contry", "");
    }


    public void saveDiscount(String discount) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("discount", discount);
        editor.commit();
    }

    public String getDiscount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("discount", "");
    }


    public void savenamefb_int(String namefb) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("namefb", namefb);
        editor.commit();
    }

    public String getnamefb_int() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("namefb", "");
    }


    public void save_email(String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.commit();
    }

    public String get_email() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("email", "");
    }

    public void saveTwitter_username(String tw_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tw_id", tw_id);
        editor.commit();
    }

    public String GetTwitter_username() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("tw_id", "");
    }


    public void savecate_xcode(String tw_id1) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tw_id1", tw_id1);
        editor.commit();
    }

    public String Getcate_xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("tw_id1", "");
    }


    public void saveadpro_image(String image) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", image);
        editor.commit();
    }

    public String Getadpro_image() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("image", "");
    }


    public void seve_Filter_Minvalue(String min) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("min", min);
        editor.commit();
    }

    public String Get_Filter_Minvalue() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("min", "");
    }

    public void seve_Filter_Maxvalue(String max) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("max", max);
        editor.commit();
    }

    public String Get_Filter_Maxvalue() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("max", "");
    }


    public void seve_Uniq_id(String Uniq_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Uniq_id", Uniq_id);
        editor.commit();
    }

    public void save_cposi(int cposi) {
        SharedPreferences sp = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("cposi", cposi);
        editor.commit();
    }

    public int get_cposi() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getInt("cposi", 0);
    }


    public void seve_brand_Xcode(String brand_Xcod) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("brand_Xcod", brand_Xcod);
        editor.commit();
    }

    public String Get_brand_Xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("brand_Xcod", "");
    }


    public void seve_brand_XName(String brand_XName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("brand_XName", brand_XName);
        editor.commit();
    }

    public String Get_brand_XName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("brand_XName", "");
    }


    public void seve_baseContrycode(String baseContrycode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("baseContrycode", baseContrycode);
        editor.commit();
    }

    public String Get_baseContrycode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("baseContrycode", "");
    }


    public void saveproduct_Detail_xocde(String product_Detail_xocde) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("product_Detail_xocde", product_Detail_xocde);
        editor.commit();
    }

    public String Get_product_Detail_xocde() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("product_Detail_xocde", "");
    }


    public void save_returnxoce(String returnxoce) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("returnxoce", returnxoce);
        editor.commit();
    }

    public String Get_returnxoce() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("returnxoce", "");
    }


    public void save_heder_Name(String heder_Name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("heder_Name", heder_Name);
        editor.commit();
    }

    public String Get_heder_Name() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("heder_Name", "");
    }


    public void save_All_Homepage_xcode(String All_Homepage_xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("All_Homepage_xcode", All_Homepage_xcode);
        editor.commit();
    }

    public String Get_All_Homepage_xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("All_Homepage_xcode", "");
    }


    public void save_AddBack(String AddBack) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AddBack", AddBack);
        editor.commit();
    }

    public String Get_AddBack() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("AddBack", "");
    }


    public void save_DrawerBack(String Get_DrawerBack) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Get_DrawerBack", Get_DrawerBack);
        editor.commit();
    }

    public String Get_DrawerBack() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Get_DrawerBack", "");
    }


    public void save_AddBack_pay(String ck_pay) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ck_pay", ck_pay);
        editor.commit();
    }

    public String Get_AddBack_pay() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("ck_pay", "");
    }

    public void seve_shopby_Xcode(String shopby_Xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("shopby_Xcode", shopby_Xcode);
        editor.commit();
    }

    public String Get_shopby_Xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("shopby_Xcode", "");
    }


    public void set_address_id(String address_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("address_id", address_id);
        editor.commit();
    }

    public String Get_address_id() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("address_id", "");
    }


    public void save_Add_return(String Add_return) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Add_return", Add_return);
        editor.commit();
    }

    public String Get_aAdd_return() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Add_return", "");
    }


    public void save_check_Attribute1(String check_Attribute1) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check_Attribute1", check_Attribute1);
        editor.commit();
    }

    public String Get_check_Attribute1() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("check_Attribute1", "");
    }


    public void save_Expert_Xcode(String Expert_Xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Expert_Xcode", Expert_Xcode);
        editor.commit();
    }

    public String Get_Expert_Xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Expert_Xcode", "");
    }


    public void save_Expert_Back(String Expert_Back) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Expert_Back", Expert_Back);
        editor.commit();
    }

    public String Get_Expert_Back() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Expert_Back", "");
    }


    public void save_projectIcon_xcode(String projectIcon_xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("projectIcon_xcode", projectIcon_xcode);
        editor.commit();
    }

    public String Get_projectIcon_xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("projectIcon_xcode", "");
    }


    public void save_AllExpert_Xcode(String AllExpert_Xcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AllExpert_Xcode", AllExpert_Xcode);
        editor.commit();
    }

    public String Get_AllExpert_Xcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("AllExpert_Xcode", "");
    }

    public void save_Homepage_Filter(String Homepage_Filter) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Homepage_Filter", Homepage_Filter);
        editor.commit();
    }

    public String Get_Homepage_Filter() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Homepage_Filter", "");
    }

    public void save_filter_sort(String brandxcodesort) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("brandxcodesort", brandxcodesort);
        editor.commit();
    }

    public String getfilter_sort() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("brandxcodesort", "");
    }


    public void saveFilterValueMin(Float filter) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("filter", filter);
        editor.commit();
    }

    public Float getFilterValueMin() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getFloat("filter", 0.0f);
    }


    public void saveFilterValueMax(Float max) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("max", max);
        editor.commit();
    }

    public Float getFilterValueMax() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getFloat("max", 0.0f);
    }


    public void saveBSprice(String BSprice) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("BSprice", BSprice);
        editor.commit();
    }

    public String getBSprice() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("BSprice", "");
    }

    public void savememtype(String memtype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("memtype", memtype);
        editor.commit();
    }

    public String getmemtype() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("memtype", "");
    }

//    public void save_lang_true(String project) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("project", project);
//        editor.commit();
//    }
//
//    public String Get_lang_true() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        return sharedPreferences.getString("project", "");
//    }


    public void saveLanguage(boolean more) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("more", more);
        editor.commit();
    }

    public boolean getLanguage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getBoolean("more", false);
    }

    public void saveHomepage(boolean Homepage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Homepage", Homepage);
        editor.commit();
    }

    public boolean getHomepage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getBoolean("Homepage", false);
    }


    public void save_search(String search) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("search", search);
        editor.commit();
    }

    public String Get_search() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("search", "");
    }


    public void savesocial_login(String social_login) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("social_login", social_login);
        editor.commit();
    }

    public String get_social_login() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("social_login", "");
    }

    public void SaveTotal(String Total) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Total", Total);
        editor.commit();
    }

    public String get_Total() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Total", "");
    }




    public void saveBafilte(String saveBafilte) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("saveBafilte", saveBafilte);
        editor.commit();
    }

    public String get_Bafilte() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("saveBafilte", "");
    }
 public void save_AddBackSeach(String AddBackSeach) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AddBackSeach", AddBackSeach);
        editor.commit();
    }

    public String get_AddBackSeach() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("AddBackSeach", "");
    }




    public void SaveDrawerXcode(String DrawerXcode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("DrawerXcode", DrawerXcode);
        editor.commit();
    }

    public String get_DrawerXcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("DrawerXcode", "");
    }



    public void SaveDrawerXcode_Sub(String Xcode_Sub) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Xcode_Sub", Xcode_Sub);
        editor.commit();
    }

    public String get_Xcode_Sub() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Xcode_Sub", "");
    }

     public void SaveAddressXcode(String getxCode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("getxCode", getxCode);
        editor.commit();
    }

    public String get_AddressXcode() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("getxCode", "");
    }

 public void AddressBack(String AddressBack) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AddressBack", AddressBack);
        editor.commit();
    }

    public String get_AddressBack() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("AddressBack", "");
    }

 public void SaveEvent_SRNo(String Event_SRNo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Event_SRNo", Event_SRNo);
        editor.commit();
    }

    public String get_Event_SRNo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("Event_SRNo", "");
    }


public void Savedetail_SRNo(String detail_) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("detail_", detail_);
        editor.commit();
    }

    public String get_detail__SRNo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString("detail_", "");
    }



}
