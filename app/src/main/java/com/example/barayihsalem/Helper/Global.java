package com.example.barayihsalem.Helper;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

//import com.bumptech.glide.Glide;

//import de.mateware.snacky.Snacky;


public class Global {

	private Context context;
	//public String fontDefaultEn_heding="PlayfairDisplay-Bold.ttf";
	public String fontDefaultEn="OpenSans-Regular.ttf";
	public String fontRegularEn="OpenSans-Light.ttf";
	public String fontLightEn="OpenSans-Light.ttf";
	public String fontDefaultEn_heding="OpenSans-Bold.ttf";
	public String fontBoldEn="OpenSans-Bold.ttf";
	public String fontSemiBoldEn="OpenSans-SemiBold.ttf";
	public String fontLightAr="FrutigerLTArabic-45Light.ttf";

	//Helper helper;
//	public String fontDefaultAr="TAHOMAB0.TTF";



	public String fontDefaultAr="FrutigerLTArabic-65Bold.ttf";
	public String fontDefaultAr_new="FrutigerLTArabic-75Black.ttf";
	public String fontRegularAr="FrutigerLTArabic-55Roman.ttf";
	public String fontBoldAr="FrutigerLTArabic-65Bold.ttf";
	public String fontSemiBoldAr="FrutigerLTArabic-45Light.ttf";
	public String fontNavNormalEn="OpenSans-SemiBold.ttf";
	public String fontNavNormalAr="FrutigerLTArabic-75Black.ttf";

/*	public String fontDefaultAr_new="Tajawal-Regular.ttf";
	public String fontRegularAr="Tajawal-Light.ttf";
	public String fontLightAr="Tajawal-Light.ttf";
	public String fontDefaultAr="Tajawal-Bold.ttf";
	public String fontBoldAr="Tajawal-Bold.ttf";

	public String fontSemiBoldAr="Tajawal-Medium.ttf";
	public String fontNavNormalEn="OpenSans-Regular.ttf";*/
	//public String fontNavNormalAr="FrutigerLTArabic-75Black.ttf";


	public Global(Context context){
		this.context=context;

	}







	public Boolean checkForApi14(){
		Boolean boolStatus=false;
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH){
			boolStatus=true;
		} else{
			boolStatus=false;
		}
		return boolStatus;
	}


	public String removeLastCharacter(String str) {
		if (str.length() > 0 && str.charAt(str.length()-1)==',') {
			str = str.substring(0, str.length()-1);
		}
		return str;
	}


	public String getFormattedDateTime(String inputFormat, String outputFormat, String value) {
		// TODO Auto-generated method stub
		SimpleDateFormat curFormater = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
		curFormater.setTimeZone(TimeZone.getTimeZone("CST"));
		Date dateObj = null;
		try {
			dateObj = curFormater.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat postFormater = new SimpleDateFormat(outputFormat);
		postFormater.setTimeZone(TimeZone.getDefault());
		String newDateStr = postFormater.format(dateObj);
		newDateStr=EnglishToArabicConvertor(newDateStr);
		return newDateStr;
	}

	public String fontSetter(String f) {

		//locale = Locale.getDefault();
		String font;

		if (f.equals("en")) {

			font = fontBoldEn;


		} else {

			font = fontBoldAr;

		}

		return font;
	}

	public String NavbaFont(String f) {

		//locale = Locale.getDefault();
		String font;

		if (f.equals("en")) {

			font = fontNavNormalEn;


		} else {

			font = fontDefaultAr_new;

		}

		return font;
	}


	public String LightFont(String f) {

		//locale = Locale.getDefault();
		String font;

		if (f.equals("en")) {

			font = fontLightEn;


		} else {

			font = fontLightAr;

		}

		return font;
	}

	public String heding1(String f) {

		//locale = Locale.getDefault();
		String font;

		if (f.equals("en")) {

			font = fontDefaultEn_heding;


		} else {

			font = fontDefaultAr;

		}

		return font;
	}
	public String fontNormal(String f) {

		//locale = Locale.getDefault();
		String font;

		if (f.equals("en")) {

			font = fontDefaultEn;


		} else {

			font = fontDefaultAr_new;

		}

		return font;
	}


	public String fontRev(String f) {

		//locale = Locale.getDefault();
		String font;

		if (f.equals("en")) {

			font = fontDefaultAr;


		} else {

			font = fontDefaultEn;

		}

		return font;
	}

	public String fontMedium(String f)
	{
		String font;

		if (f.equals("en")) {

			font = fontSemiBoldEn;


		} else {

			font = fontSemiBoldAr;

		}
		return font;

	}

	public String fontRegular(String f)
	{
		String font;

		if (f.equals("en")) {

			font = fontRegularEn;


		} else {

			font = fontLightAr;

		}
		return font;

	}
	public String fontBold(String f)
	{
		String font;

		if (f.equals("en")) {

			font = fontBoldEn;


		} else {

			font = fontBoldAr;

		}
		return font;

	}

	public String EnglishToArabicConvertor(String strNumber) {


		if (strNumber.contains("١"))
			strNumber = strNumber.replace("١", "1");
		if (strNumber.contains("٢"))
			strNumber = strNumber.replace("٢", "2");
		if (strNumber.contains("٣"))
			strNumber = strNumber.replace("٣", "3");
		if (strNumber.contains("٤"))
			strNumber = strNumber.replace("٤", "4");
		if (strNumber.contains("٥"))
			strNumber = strNumber.replace("٥", "5");
		if (strNumber.contains("٦"))
			strNumber = strNumber.replace("٦", "6");
		if (strNumber.contains("٧"))
			strNumber = strNumber.replace("٧", "7");
		if (strNumber.contains("٨"))
			strNumber = strNumber.replace("٨", "8");
		if (strNumber.contains("٩"))
			strNumber = strNumber.replace("٩", "9");
		if (strNumber.contains("٠"))
			strNumber = strNumber.replace("٠", "0");


		//  //System.out.println("Number  : "+strNumber);

		return strNumber;
	}

/*	public int updateBadgeToCart(Activity activity) {
		helper=new Helper(activity);
		int value=helper.getCount(cartTable);
		return value;
	}
	public int updateBadgeToWishlist(Activity activity) {
		helper=new Helper(activity);
		int value=helper.getCount(wishlistTable);
		return value;
	}*/
	public boolean checkIfCoordinate(String value){
		boolean isValid=false;
		boolean isDouble=false;
		try {
			Double.parseDouble(value);
			isDouble=true;
		} catch (NumberFormatException e) {
			isDouble=false;
		}
		if (isDouble){
			if (Double.parseDouble(value) > -180.00000000&& Double.parseDouble(value)<180.0000000) {
				isValid=true;
			}
		}

		return isValid;
	}

}
