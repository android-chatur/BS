package com.example.barayihsalem.Helper;


import com.example.barayihsalem.UI.Create_Booth_SlotAPIPojo;
import com.example.barayihsalem.UI.Model.Accommodation_Seeking_Pojo;
import com.example.barayihsalem.UI.Model.Add_to_Cart_Pojo;
import com.example.barayihsalem.UI.Model.Additional_Servicesojo;
import com.example.barayihsalem.UI.Model.Attribute_Pojo;
import com.example.barayihsalem.UI.Model.Boost_Your_Sales;
import com.example.barayihsalem.UI.Model.Bussi_OwnerPojo;
import com.example.barayihsalem.UI.Model.Checkout_Pojo;
import com.example.barayihsalem.UI.Model.CommunityPojo;
import com.example.barayihsalem.UI.Model.Confirm_Booth_Booking_Pojo;
import com.example.barayihsalem.UI.Model.Confirm_BusinessPojo;
import com.example.barayihsalem.UI.Model.Confirm_Create_Pojo;
import com.example.barayihsalem.UI.Model.Confirm_Membership_Community_Flow_Pojo;
import com.example.barayihsalem.UI.Model.Create_Event_Pojo;
import com.example.barayihsalem.UI.Model.DeleteAddressPojo;
import com.example.barayihsalem.UI.Model.Donation_Pojo;
import com.example.barayihsalem.UI.Model.Events_PlannerPojo;
import com.example.barayihsalem.UI.Model.ExcelPojo;
import com.example.barayihsalem.UI.Model.ForgotPasswordPojo;
import com.example.barayihsalem.UI.Model.Gallery_CommunityPojo;
import com.example.barayihsalem.UI.Model.Get_BoothSlot_list_Pojo;
import com.example.barayihsalem.UI.Model.Get_Membership_DashboardPojo;
import com.example.barayihsalem.UI.Model.Get_Store_Details_Pojo;
import com.example.barayihsalem.UI.Model.InitiativesPojo;
import com.example.barayihsalem.UI.Model.LoginPojo;
import com.example.barayihsalem.UI.Model.MembershiPojo;
import com.example.barayihsalem.UI.Model.PDFPojo;
import com.example.barayihsalem.UI.Model.Pay_Remaining_BusinessPojo;
import com.example.barayihsalem.UI.Model.PromoCode_Pojo;
import com.example.barayihsalem.UI.Model.RegistrationPojo;
import com.example.barayihsalem.UI.Model.ResidentrPojo;
import com.example.barayihsalem.UI.Model.Save_Booth_BookingPojo;
import com.example.barayihsalem.UI.Model.Save_Community_Pojo;
import com.example.barayihsalem.UI.Model.Save_Confirm_Boost_Sales_Pojo;
import com.example.barayihsalem.UI.Model.Save_Create_Booth_SlotPojo;
import com.example.barayihsalem.UI.Model.Save_Req_Initial_Approval_pojo;
import com.example.barayihsalem.UI.Model.Save_Req_Support;
import com.example.barayihsalem.UI.Model.Save_User_Rating_Pojo;
import com.example.barayihsalem.UI.Model.Seek_Job_Pojo;
import com.example.barayihsalem.UI.Model.SocialPojo;
import com.example.barayihsalem.UI.Model.SuggestionsPojo;
import com.example.barayihsalem.UI.Model.SupplierPojo;
import com.example.barayihsalem.UI.Model.Update_Checkout_Pojo;
import com.example.barayihsalem.UI.Model.Update_Or_Delete_Cart_All_Pojo;
import com.example.barayihsalem.UI.Model.UserLoginpojo;
import com.example.barayihsalem.UI.Model.UserShippingAddressPojo;
import com.example.barayihsalem.UI.Model.Valunteering_Pojo;
import com.example.barayihsalem.UI.Model.VisionPartnerGovernmentOnlyPojo;
import com.example.barayihsalem.UI.Model.VisionPartnerPublicPojo;
import com.example.barayihsalem.UI.Model.VisionPartnerSponserPojo;
import com.example.barayihsalem.UI.Model.VisionPartnerland_loadsPojo;
import com.example.barayihsalem.UI.Model.WellcomePojo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {


    @POST("SaveUserRegistration/")
    Call<RegistrationPojo> Save_UserRegistration(@Body RegistrationPojo categoryDTO);


    @POST("Save_Booth_Booking/")
    Call<Save_Booth_BookingPojo> Save_Booth_Booking(@Body Save_Booth_BookingPojo categoryDTO);


    @POST("ForgotPassword/")
    Call<ForgotPasswordPojo> ForgotPassword(@Body ForgotPasswordPojo categoryDTO);


    @POST("UserLogin/")
    Call<LoginPojo> Save_UserLogin(@Body LoginPojo categoryDTO);


    @POST("UserLoginFromFacebook/")
    Call<SocialPojo> UserLoginFromFacebook(@Body SocialPojo loginPojo);


    @POST("UserLogin_Social/")
    Call<UserLoginpojo> UserLogin_Social(@Body UserLoginpojo loginPojo);


    @POST("Confirm_Business_Owner/")
    Call<Confirm_BusinessPojo> Confirm_Business_Owner(@Body Confirm_BusinessPojo categoryDTO);

     @POST("Save_Events_Planner/")
    Call<Events_PlannerPojo> Save_Events_Planner(@Body Events_PlannerPojo categoryDTO);


    @POST("Save_VisionPartner/")
    Call<VisionPartnerGovernmentOnlyPojo> Save_VisionPartner_govement(@Body VisionPartnerGovernmentOnlyPojo categoryDTO);

    @POST("Save_VisionPartner/")
    Call<VisionPartnerSponserPojo> Save_VisionPartner_sponser(@Body VisionPartnerSponserPojo categoryDTO);

    @POST("Save_VisionPartner/")
    Call<VisionPartnerPublicPojo> Save_VisionPartner_publc(@Body VisionPartnerPublicPojo categoryDTO);


    @POST("Create_Booth_Slot/")
    Call<Create_Booth_SlotAPIPojo> Create_Booth_Slot(@Body Create_Booth_SlotAPIPojo categoryDTO);


    @POST("Save_Create_Booth_Slot/")
    Call<Save_Create_Booth_SlotPojo> Save_Create_Booth_Slot(@Body Save_Create_Booth_SlotPojo categoryDTO);


    @POST("Save_VisionPartner/")
    Call<VisionPartnerland_loadsPojo> Save_VisionPartner_landload(@Body VisionPartnerland_loadsPojo categoryDTO);


    @POST("Pay_Remaining_Business_Owner_Amount/")
    Call<Pay_Remaining_BusinessPojo> Pay_Remaining_Business_Owner_Amount(@Body Pay_Remaining_BusinessPojo categoryDTO);


    @POST("Save_Resident/")
    Call<ResidentrPojo> Save_Resident(@Body ResidentrPojo categoryDTO);

    @POST("Save_UserShippingAddress/")
    Call<UserShippingAddressPojo> Save_UserShippingAddress(@Body UserShippingAddressPojo categoryDTO);


  @POST("Save_UserShippingAddress/")
    Call<DeleteAddressPojo> Save_UserShippingAddress(@Body DeleteAddressPojo categoryDTO);


    @POST("Save_Passionate/")
    Call<ResidentrPojo> Save_Passionate(@Body ResidentrPojo categoryDTO);


    @POST("Save_Boost_Your_Sales/")
    Call<Boost_Your_Sales> Save_Boost_Your_Sales(@Body Boost_Your_Sales categoryDTO);


    @POST("Get_BoothSlot_list_ByBooth/")
    Call<Get_BoothSlot_list_Pojo> Get_BoothSlot_list_ByBooth(@Body Get_BoothSlot_list_Pojo categoryDTO);


    @POST("Save_Supplier/")
    Call<SupplierPojo> Save_Supplier(@Body SupplierPojo categoryDTO);


    @POST("Save_Suggestions/")
    Call<SuggestionsPojo> Save_Suggestions(@Body SuggestionsPojo categoryDTO);


    @POST("Save_Initiatives_From_You/")
    Call<InitiativesPojo> Save_Initiatives_From_You(@Body InitiativesPojo categoryDTO);


    @POST("Get_Event_Gallery_Community_wise_API/")
    Call<Gallery_CommunityPojo> Get_Event_Gallery_Community_wise_API(@Body Gallery_CommunityPojo categoryDTO);

 @POST("Get_Event_Older_Gallery_Community_wise_API/")
    Call<Gallery_CommunityPojo> Get_Event_Older_Gallery_Community_wise_API(@Body Gallery_CommunityPojo categoryDTO);


    @POST("Save_Complain/")
    Call<SuggestionsPojo> Save_Complain(@Body SuggestionsPojo categoryDTO);


    @POST("Save_Question/")
    Call<SuggestionsPojo> Save_Question(@Body SuggestionsPojo categoryDTO);


    @POST("Save_Opinion/")
    Call<SuggestionsPojo> Save_Opinion(@Body SuggestionsPojo categoryDTO);


    @POST("Save_Business_Owner/")
    Call<Bussi_OwnerPojo> Save_Business_Owner(@Body Bussi_OwnerPojo categoryDTO);


    @POST("Save_Community/")
    Call<Save_Community_Pojo> Save_Community(@Body Save_Community_Pojo categoryDTO);

    @POST("Get_Store_Details/")
    Call<Get_Store_Details_Pojo> Get_Store_Details(@Body Get_Store_Details_Pojo categoryDTO);

    @POST("Add_to_Cart_All/")
    Call<Add_to_Cart_Pojo> Add_to_Cart_All(@Body Add_to_Cart_Pojo categoryDTO);

    /*@POST("Get_Attribute_List_New/")
    Call<Attribute_Pojo> Get_Attribute_List(@Body Attribute_Pojo categoryDTO);
*/
 @POST("Get_Attribute_List/")
    Call<Attribute_Pojo> Get_Attribute_List(@Body Attribute_Pojo categoryDTO);


    @POST("Apply_PromoCode/")
    Call<PromoCode_Pojo> Apply_PromoCode(@Body PromoCode_Pojo categoryDTO);


    @POST("Update_Or_Delete_Cart_All/")
    Call<Update_Or_Delete_Cart_All_Pojo> Update_Or_Delete_Cart_All(@Body Update_Or_Delete_Cart_All_Pojo categoryDTO);


    @POST("Confirm_Membership_Community_Flow/")
    Call<Confirm_Membership_Community_Flow_Pojo> Confirm_Membership_Community_Flow(@Body Confirm_Membership_Community_Flow_Pojo categoryDTO);

  @POST("Confirm_Booth_Booking/")
    Call<Confirm_Booth_Booking_Pojo> Confirm_Booth_Booking(@Body Confirm_Booth_Booking_Pojo categoryDTO);


    @POST("Save_Volunteering/")
    Call<Valunteering_Pojo> Save_Volunteering(@Body Valunteering_Pojo categoryDTO);


    @POST("Save_Seek_Job/")
    Call<Seek_Job_Pojo> Save_Seek_Job(@Body Seek_Job_Pojo categoryDTO);


    @POST("Save_Create_Event/")
    Call<Create_Event_Pojo> Save_Create_Event(@Body Create_Event_Pojo categoryDTO);


    @POST("Confirm_Create_Event/")
    Call<Confirm_Create_Pojo> Confirm_Create_Event(@Body Confirm_Create_Pojo categoryDTO);


    @POST("Save_User_Rating/")
    Call<Save_User_Rating_Pojo> Save_User_Rating(@Body Save_User_Rating_Pojo categoryDTO);


    @POST("Save_Checkout/")
    Call<Checkout_Pojo> Save_Checkout(@Body Checkout_Pojo categoryDTO);


    @POST("Update_Checkout/")
    Call<Update_Checkout_Pojo> Update_Checkout(@Body Update_Checkout_Pojo categoryDTO);


    @POST("Save_Confirm_Boost_Sales/")
    Call<Save_Confirm_Boost_Sales_Pojo> Save_Confirm_Boost_Sales(@Body Save_Confirm_Boost_Sales_Pojo categoryDTO);


    @POST("Save_Accommodation_Seeking/")
    Call<Accommodation_Seeking_Pojo> Save_Accommodation_Seeking(@Body Accommodation_Seeking_Pojo categoryDTO);

    @POST("Save_Accommodation_Offering/")
    Call<Accommodation_Seeking_Pojo> Save_Accommodation_Offering(@Body Accommodation_Seeking_Pojo categoryDTO);


    @POST("Save_Donation/")
    Call<Donation_Pojo> Save_Donation(@Body Donation_Pojo categoryDTO);


    @POST("Save_Req_Initial_Approval/")
    Call<Save_Req_Initial_Approval_pojo> Save_Req_Initial_Approval(@Body Save_Req_Initial_Approval_pojo categoryDTO);

    @POST("Save_Req_Support/")
    Call<Save_Req_Support> Save_Req_Support(@Body Save_Req_Support categoryDTO);


    @POST("Save_User_Additional_Services/")
    Call<Additional_Servicesojo> Save_User_Additional_Services(@Body Additional_Servicesojo categoryDTO);


    @GET("Get_Welcome_API/")
    Call<WellcomePojo> Get_Welcome_API(
            @Query("UserId") String user_id,
            @Query("UniqueId") String UniqueId,
            @Query("CultureId") String CultureId,
            @Query("CorpcentreBy") String CorpcentreBy);


    @GET("Event_Details_Byte_Array_Excel_Downlaod/")
    Call<PDFPojo> Event_Details_Byte_Array_PDF_Downlaod(
            @Query("Value") String user_id,
            @Query("UserId") String UniqueId,
            @Query("CultureId") String CultureId,
            @Query("CorpcentreBy") String CorpcentreBy);


    @GET("Event_Details_Excel_Downlaod/")
    Call<ExcelPojo> Event_Details_Excel_Downlaod(
            @Query("Value") String user_id,
            @Query("UserId") String UniqueId,
            @Query("CultureId") String CultureId,
            @Query("CorpcentreBy") String CorpcentreBy);



    @GET("Get_Membership_Details_API/")
    Call<MembershiPojo> Get_Membership_Details_API(
            @Query("Value") String membership_srno,
            @Query("UserId") String getuserid,
            @Query("UniqueId") String getUniqueId,
            @Query("CultureId") String culturemode,
            @Query("CorpcentreBy") String company_id);


    @GET("Get_Community_Details_API/")
    Call<CommunityPojo> Get_Community_Details_API(
            @Query("Value") String membership_srno,
            @Query("UserId") String getuserid,
            @Query("UniqueId") String getUniqueId,
            @Query("CultureId") String culturemode,
            @Query("CorpcentreBy") String company_id);


    @GET("Get_Membership_Dashboard_API/")
    Call<Get_Membership_DashboardPojo> Get_Membership_Dashboard_API(
            @Query("UserId") String user_id,
            @Query("UniqueId") String UniqueId,
            @Query("CultureId") String CultureId,
            @Query("CorpcentreBy") String CorpcentreBy);


    @GET("Get_Community_Dashboard_API/")
    Call<Get_Membership_DashboardPojo> Get_Community_Dashboard_API(
            @Query("UserId") String user_id,
            @Query("UniqueId") String UniqueId,
            @Query("CultureId") String CultureId,
            @Query("CorpcentreBy") String CorpcentreBy);


}
