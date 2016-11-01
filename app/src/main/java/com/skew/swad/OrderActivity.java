package com.skew.swad;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



import com.skew.swad.R;

public class OrderActivity extends ActionBarActivity {
	Button button11;
	TableLayout orderLayoutTable;
	Context context;
	String tempDish;
	float tempRate;
	int id;
	int dishcount;
	String dishcountT;
	RadioGroup radioGroup;
	RadioButton radioButton;
	RadioGroup radioGroupAdd;
	RadioButton radioButtonAdd;
	EditText couponCodeTxt;
	TextView discountedBillInfo;
	String rateT;
	ArrayList<Object> selectedDishList = new ArrayList<>();
	Button clearOrderBtn ;
	Button Submit;
	String deviceid;
	String orderDesc;
	String billamt;
	InputStream is=null;
	String result=null;
	String line=null;
	String name;
	String deltype;
	String deladdress;
	EditText nonregisteredAddress;
	String selectedAddress;
	//float OriginalBill;
	float displaybill = (float) 0.0;
	float taxrate = (float)0;
	int baseamount;
	String disctype;
	int discount;
	String cpnstatus;
	String cpntype;
	int ordercount;
	static Boolean couponApplied = false;
	static String couponcode;
	static float billAfterDiscount;
	private SharedPreferences sharedPrefs;
	private String sharedPrefsName = MainActivity.sharedPrefsName;
//	TextView totalbill;
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
			couponApplied = false;
			billAfterDiscount = FoodMenuActivity.Bill;
			Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			finish();
	}
	// ArrayList<String> selectedDishList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		String fontPath = "fonts/ChampagneLimousines.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		clearOrderBtn = (Button) findViewById(R.id.clearOrder);
		clearOrderBtn.setTypeface(tf, Typeface.BOLD);
		
		clearOrderBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// Clear Order
				Toast toast = new Toast(getApplicationContext());
			    ImageView view1  = new ImageView(getApplicationContext()); 	 
			    view1.setBackgroundResource(R.drawable.removed); 
			    toast.setDuration(Toast.LENGTH_SHORT);
			    toast.setGravity(Gravity.CENTER, 0, 0);
			    toast.setView(view1); 
			    toast.show();
				FoodMenuActivity.order.clear();
				FoodMenuActivity.Bill = (float) 0.0;
				finish();
				Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				startActivity(intent);
			}
		});
		
		Iterator<Map.Entry<String, String>> entries = FoodMenuActivity.order
				.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			String key = entry.getKey();
			String value = entry.getValue();
			selectedDishList.add(key + "#" + value);
		}

		for (int i = 0; i < selectedDishList.size(); i++) {
			// get a reference for the TableLayout
			orderLayoutTable = (TableLayout) findViewById(R.id.odertable);
			TableRow row = new TableRow(this);
			ImageView icon = new ImageButton(this);
	//		icon.setBackgroundResource(R.drawable.veg);
			String[] temp1 = selectedDishList.get(i).toString().split("#");
			tempDish = temp1[0];
			rateT = temp1[1];
			dishcountT = temp1[2];

		/*	if(tempDish.contains("Chi")){
		    	icon.setBackgroundResource(R.drawable.chickenleg);}
		    else{
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }*/
			
			TextView dishtext = new TextView(this);
			dishtext.setText("\t" + tempDish + " ");
			dishtext.setTextSize(16);
			dishtext.setWidth(350);
			dishtext.setGravity(Gravity.LEFT);
			dishtext.setTypeface(tf, Typeface.BOLD);
			dishtext.setTextColor(getResources().getColor(R.color.White));
			
			TextView rate = new TextView(this);
			rate.setText(rateT+" ");
			rate.setTypeface(tf, Typeface.BOLD);
			rate.setTextColor(getResources().getColor(R.color.White));
			 
			TextView qty = new TextView(this);
			qty.setText("("+dishcountT + ") ");
			qty.setTypeface(tf, Typeface.BOLD);
			qty.setGravity(Gravity.CENTER_HORIZONTAL);
			qty.setTextColor(getResources().getColor(R.color.White));
//			qty.setBackgroundColor(getResources().getColor(R.color.green12));
			
			
			ImageButton ad = new ImageButton(this);
			ad.setBackgroundResource(R.drawable.removedish);

			ad.setId(i);
//			code to add the image icons			
	//		row.addView(icon);
			row.addView(dishtext);
			row.addView(rate);
			row.addView(qty);
			row.addView(ad);
			row.setGravity(Gravity.LEFT);
			
			if(i%2==0){
			    row.setBackgroundColor(getResources().getColor(R.color.brown));	
			    dishtext.setTextColor(getResources().getColor(R.color.golden));
			    rate.setTextColor(getResources().getColor(R.color.golden));
			    
			    }else{
			    	row.setBackgroundColor(getResources().getColor(R.color.brown));	
			    	dishtext.setTextColor(getResources().getColor(R.color.golden));
			    	rate.setTextColor(getResources().getColor(R.color.golden));
				    
			    }

			// add the TableRow to the TableLayout
			orderLayoutTable.addView(row, new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

			ad.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					
					Toast toast = new Toast(getApplicationContext());
				    ImageView view1  = new ImageView(getApplicationContext()); 	 
				    view1.setBackgroundResource(R.drawable.removed); 
				    toast.setDuration(Toast.LENGTH_SHORT);
				    toast.setGravity(Gravity.CENTER, 0, 0);
				    toast.setView(view1); 
				    toast.show();
					id = view.getId();
					String[] temp = selectedDishList.get(id).toString()
							.split("#");
										tempDish = temp[0];
					tempRate = Float.parseFloat(temp[1]);
					dishcount = Integer.parseInt(temp[2]) - 1;
					if (dishcount <= 0) {
						FoodMenuActivity.order.remove(tempDish);
					} else {
						
						FoodMenuActivity.order.put(tempDish, tempRate + "#"+ dishcount);
					}
					billAfterDiscount = FoodMenuActivity.Bill;
					couponApplied = false;
					finish();
					Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					startActivity(intent);
					FoodMenuActivity.Bill = FoodMenuActivity.Bill - tempRate;
				}
			});
		}
		
		if(FoodMenuActivity.Bill>0.00){
//			OriginalBill = FoodMenuActivity.Bill;
		TextView gap = new TextView(this);
		gap.setGravity(Gravity.RIGHT);
		orderLayoutTable.addView(gap, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		TextView totalbill = new TextView(this);
		TextView billNote = new TextView(this);
		TextView originalBillNote = new TextView(this);
//		TextView discountNote = new TextView(this);
		
//		discountNote.setText("10% discount on Bill more than Rs.500");
		displaybill = FoodMenuActivity.Bill;
		displaybill = displaybill + displaybill*taxrate/100;
/*		if(FoodMenuActivity.Bill>=500.00){
//			displaybill = FoodMenuActivity.Bill - (FoodMenuActivity.Bill)/10;
			displaybill = displaybill - displaybill/10;
			discountNote.setText("Congrats! you got 10% discount: ");
			discountNote.setTextColor(getResources().getColor(R.color.green));
			discountNote.setBackgroundColor(getResources().getColor(R.color.White));
			discountNote.setTypeface(null, Typeface.BOLD);
			discountNote.setGravity(Gravity.LEFT);
			
		}else{
			discountNote.setText("10% discount on Bill more than Rs.500");
			discountNote.setTextColor(getResources().getColor(R.color.maroon));
			discountNote.setBackgroundColor(getResources().getColor(R.color.White));
			discountNote.setTypeface(null, Typeface.BOLD);
			discountNote.setGravity(Gravity.LEFT);
		}*/
		/*discountNote.setTextColor(getResources().getColor(R.color.green));
		discountNote.setBackgroundColor(getResources().getColor(R.color.White));
		discountNote.setTypeface(null, Typeface.BOLD);
		discountNote.setGravity(Gravity.LEFT);*/
		
/*		orderLayoutTable.addView(discountNote, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
*/		
		billNote.setText("Bill:");
		billNote.setTextSize(20); 
		billNote.setTextColor(getResources().getColor(R.color.black));
		billNote.setBackgroundColor(getResources().getColor(R.color.White));
		billNote.setTypeface(tf, Typeface.BOLD);
		billNote.setGravity(Gravity.LEFT);
		
		totalbill.setText("Rs. "+Math.round(displaybill)+"\t\t");
		totalbill.setTextSize(22); 
		totalbill.setTextColor(getResources().getColor(R.color.maroon));
		totalbill.setBackgroundColor(getResources().getColor(R.color.White));
		totalbill.setTypeface(tf, Typeface.BOLD);
		totalbill.setGravity(Gravity.RIGHT);
		
		/*originalBillNote.setText("Original Bill: Rs. "+Math.round(OriginalBill)+"\t\t");
		originalBillNote.setTextSize(15); 
		originalBillNote.setTextColor(getResources().getColor(R.color.maroon));
		originalBillNote.setBackgroundColor(getResources().getColor(R.color.White));
		originalBillNote.setTypeface(null, Typeface.NORMAL);
		originalBillNote.setGravity(Gravity.RIGHT);*/
		
		orderLayoutTable.addView(billNote, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		orderLayoutTable.addView(totalbill, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		/*orderLayoutTable.addView(originalBillNote, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		*/
		
		Button addmoreButton = new Button(this);
		addmoreButton.setText("Add more dishes to Order");
		addmoreButton.setTypeface(tf, Typeface.BOLD);
		addmoreButton.setTextColor(getResources().getColor(R.color.black));
		orderLayoutTable.addView(addmoreButton, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addmoreButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				billAfterDiscount = FoodMenuActivity.Bill;
				couponApplied = false;
				Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				finish();
			}
		});

 		// coupon code
 		couponCodeTxt = new EditText(this);
		couponCodeTxt.setTypeface(tf, Typeface.BOLD);
		couponCodeTxt.setHint("Enter Coupon Code");
		couponCodeTxt.setTextColor(getResources().getColor(R.color.blue));
		orderLayoutTable.addView(couponCodeTxt, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		Button applyCouponCode = new Button(this);
		applyCouponCode.setText("Apply Coupon");
		applyCouponCode.setTypeface(tf, Typeface.BOLD);
		applyCouponCode.setTextColor(getResources().getColor(R.color.blue));
		orderLayoutTable.addView(applyCouponCode, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		applyCouponCode.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				
				ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
	            if ( connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
	                 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ) {
				// Send coupon code ,restoid
				String cpnckeck = couponCodeTxt.getText().toString().trim();
			if(!cpnckeck.isEmpty()){
				Map couponCodeDetailsMap = getCouponDetails(cpnckeck);
				baseamount = Integer.parseInt(couponCodeDetailsMap.get("baseamount").toString());
				disctype = couponCodeDetailsMap.get("disctype").toString();
				discount = Integer.parseInt(couponCodeDetailsMap.get("discount").toString());;
				cpnstatus = couponCodeDetailsMap.get("cpnstatus").toString();
				cpntype = couponCodeDetailsMap.get("cpntype").toString();
				ordercount = Integer.parseInt(couponCodeDetailsMap.get("ordercount").toString());
				
				// Check the condition
				if(cpnstatus.equalsIgnoreCase("Active")){
					if(cpntype.equalsIgnoreCase("REG")){
						if(FoodMenuActivity.Bill>baseamount){
							if(disctype.equalsIgnoreCase("RUP")){
						//		billAfterDiscount = FoodMenuActivity.Bill; 
								billAfterDiscount = FoodMenuActivity.Bill-discount;
								Toast.makeText(getApplicationContext(),"Congrats! Discount Added", Toast.LENGTH_SHORT).show();
								discountedBillInfo.setText("Discounted Bill: "+billAfterDiscount);
								couponApplied = true;
								couponcode = cpnckeck;
								/*Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
								startActivity(intent);
								overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
								finish();*/
							}else if(disctype.equalsIgnoreCase("PER")){
						//		billAfterDiscount = FoodMenuActivity.Bill;
								billAfterDiscount = FoodMenuActivity.Bill-(FoodMenuActivity.Bill*discount/100);
								discountedBillInfo.setText("Discounted Bill: "+billAfterDiscount);
								couponApplied = true;
								couponcode = cpnckeck;
								Toast.makeText(getApplicationContext(),"Congrats! Discount Added", Toast.LENGTH_SHORT).show();
								/*Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
								startActivity(intent);
								overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
								finish();*/
							}						
							}else{
								discountedBillInfo.setText("Bill Insufficient for this CouponCode");
								couponApplied = false;
								couponcode = "-";
								Toast.makeText(getApplicationContext(), "Bill Insufficient for this CouponCode", Toast.LENGTH_SHORT).show();
						}
					}else{
						if(FoodMenuActivity.Bill>baseamount){
							if(ordercount==0){
							if(disctype.equalsIgnoreCase("RUP")){
						//		billAfterDiscount = FoodMenuActivity.Bill; 
								billAfterDiscount = FoodMenuActivity.Bill-discount;
								Toast.makeText(getApplicationContext(),"Congrats! Discount Added", Toast.LENGTH_SHORT).show();
								discountedBillInfo.setText("Discounted Bill: "+billAfterDiscount);
								couponApplied = true;
								couponcode = cpnckeck;
								/*Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
								startActivity(intent);
								overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
								finish();*/
							}else if(disctype.equalsIgnoreCase("PER")){
						//		billAfterDiscount = FoodMenuActivity.Bill;
								billAfterDiscount = FoodMenuActivity.Bill-(FoodMenuActivity.Bill*discount/100);
								discountedBillInfo.setText("Discounted Bill: "+billAfterDiscount);
								couponApplied = true;
								couponcode = cpnckeck;
								Toast.makeText(getApplicationContext(),"Congrats! Discount Added", Toast.LENGTH_SHORT).show();
								/*Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
								startActivity(intent);
								overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
								finish();*/
							}						
							}else{
								discountedBillInfo.setText("Not eligible for coupon Code");
							}
					}else{
								discountedBillInfo.setText("Bill Insufficient for this CouponCode");
								couponApplied = false;
								couponcode = "-";
								Toast.makeText(getApplicationContext(), "Bill Insufficient for this CouponCode", Toast.LENGTH_SHORT).show();
						}
					
					}
					
				}else if(cpnstatus.equalsIgnoreCase("InActive")){
					discountedBillInfo.setText("");
					couponApplied = false;
					Toast.makeText(getApplicationContext(), "Coupon InActive", Toast.LENGTH_SHORT).show();
				}else{
					discountedBillInfo.setText("");
					couponApplied = false;
					Toast.makeText(getApplicationContext(), "Coupon Invalid", Toast.LENGTH_SHORT).show();
				}
				
			}else{
				discountedBillInfo.setText("");
				couponApplied = false;
				Toast.makeText(getApplicationContext(), "Please Enter Valid CouponCode", Toast.LENGTH_SHORT).show();
			}
				
/*				FoodMenuActivity.Bill = displaybill;
				Intent intent = new Intent(getApplicationContext(),OrderTypeActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				finish();*/
			}else{
            	Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
			}
		});
		
		// if coupon is applied then show the discounted bill
	//	if(couponApplied){
		discountedBillInfo = new TextView(this);
		discountedBillInfo.setTypeface(tf, Typeface.BOLD);
		discountedBillInfo.setTextSize(22);
		discountedBillInfo.setGravity(Gravity.RIGHT);
//		discountedBillInfo.setHint("Discounted Bill");
		discountedBillInfo.setTextColor(getResources().getColor(R.color.green));
		orderLayoutTable.addView(discountedBillInfo, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	//	}
		
		Button proceedbtn = new Button(this);
	//	proceedbtn.setText("Proceed To Order >>>");
		proceedbtn.setText("SUBMIT ORDER");
		proceedbtn.setTypeface(tf, Typeface.BOLD);
		proceedbtn.setTextColor(getResources().getColor(R.color.maroon));
		orderLayoutTable.addView(proceedbtn, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		proceedbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				FoodMenuActivity.Bill = displaybill;
				Intent intent = new Intent(getApplicationContext(),OrderTypeActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				finish();
			}
		});

		
		
		
	//	Button orderButton = new Button(this);
/*		orderButton.setText("Submit Order");
		orderButton.setTypeface(null, Typeface.BOLD);
		orderButton.setTextColor(getResources().getColor(R.color.black));
		orderLayoutTable.addView(orderButton, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		orderButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
	
				selectedAddress = "REGISTERED ADD"; 
				deltype = "HOME DELIVERY";
				
//Check if Registered Address or New Address
				if(deltype.equalsIgnoreCase("HOME DELIVERY")){
					if(selectedAddress.equalsIgnoreCase("REGISTERED ADD")){
						deladdress = "Registered Address";
					}else{
						deladdress = nonregisteredAddress.getText().toString();	
					}
				}
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				deviceid = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
				orderDesc = FoodMenuActivity.order.toString();
//		        billamt = String.valueOf(FoodMenuActivity.Bill);
		        billamt = String.valueOf(displaybill);
				// check if client is registered
//		        InputStream input = null;
		        try{
		        	sharedPrefs = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);
					boolean isRegistered = false;
					sharedPrefs = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);
					isRegistered = sharedPrefs.getBoolean("isRegistered", false);
		        if(isRegistered){
		        	ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
			           // Check for network connections
			            if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
				                 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ) {
			            	insert();
							FoodMenuActivity.order.clear();
							FoodMenuActivity.Bill = (float) 0.00;
							Intent intent = new Intent(getApplicationContext(),MainActivity.class);
							startActivity(intent);
							finish();
			            }else if ( 
			  	              connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
				              connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
			            	  Toast.makeText(getApplicationContext(),"No Internet Connection!!!" ,Toast.LENGTH_LONG).show();
				              Intent intent = new Intent(getApplicationContext(),MainActivity.class);
							  startActivity(intent);
							  finish();
				            }
		        	}else{
		        		Toast.makeText(getApplicationContext(),"Please register to order!!!" ,Toast.LENGTH_LONG).show();
		        		Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
						startActivity(intent);
						finish();
		        	}
				}catch(Exception e){
		        	Log.e("oops Something went wrong", e.toString());
		        	Toast.makeText(getApplicationContext(),"oops Something went wrong!!!" ,Toast.LENGTH_LONG).show();
		        }
			}
		});*/
		
		}else{
			Toast.makeText(getApplicationContext(), "Bill Not sufficient", Toast.LENGTH_LONG).show();
			finish();
			Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			startActivity(intent);

			
		}
	}
	public void insert()
    {
		Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
		try{
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	// Add resturant name here 
//    	nameValuePairs.add(new BasicNameValuePair("resturant","skytouch"));
    	nameValuePairs.add(new BasicNameValuePair("userid",deviceid));
    	nameValuePairs.add(new BasicNameValuePair("orderdesc",orderDesc));
    	nameValuePairs.add(new BasicNameValuePair("billamount",billamt));
    	nameValuePairs.add(new BasicNameValuePair("restoid",MainActivity.restaurantID));
    	nameValuePairs.add(new BasicNameValuePair("deltype",deltype));
    	nameValuePairs.add(new BasicNameValuePair("deladdress",deladdress));

    	/*try
    	{*/


    		HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://skewtech.com/Restro/admin/order.php");
	        // 192.186.194.131 
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	//        Toast.makeText(getApplicationContext(),deviceid+orderDesc+billamt, Toast.LENGTH_LONG).show();
	        Toast.makeText(getApplicationContext(), "Order Submitted", Toast.LENGTH_LONG).show();
	        Toast.makeText(getApplicationContext(), deltype, Toast.LENGTH_LONG).show();
	//        Toast.makeText(getApplicationContext(), deladdress, Toast.LENGTH_LONG).show();
    	}
		catch(NullPointerException ne){
			Log.e("Null Pointer", ne.toString());
        	Toast.makeText(getApplicationContext(),"Please get Registered" ,Toast.LENGTH_LONG).show();	
        	finish();
		//	Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
			startActivity(intent);
		}
        catch(UnknownHostException ue){
        	Log.e("No Internet", ue.toString());
        	Toast.makeText(getApplicationContext(),"No Internet Connection" ,Toast.LENGTH_LONG).show();
        }   
    	catch(Exception e){
        //	Log.e("oops Something went wrong", e.toString());
        	Toast.makeText(getApplicationContext(),"oops Something went wrong!!!" ,Toast.LENGTH_LONG).show();
        }
        
    }

	// code to fetch the coupons
	public Map getCouponDetails(String couponcode){
				Map couponDetailsMap = new HashMap();
				String serverURL = "http://restro.skewtech.com/admin/cpncheck.php?+cpnckeck="+couponcode+"&restoid="+MainActivity.restaurantID+"&deviceid="+MainActivity.deviceID;
				final HttpClient Client = new DefaultHttpClient();
		        String Content;
		         try {
					    HttpGet httpget = new HttpGet(serverURL);
		                ResponseHandler<String> responseHandler = new BasicResponseHandler();
		                Content = Client.execute(httpget, responseHandler);
		                String couponString = Content.trim();
		        		String[] coupArray = couponString.split("#");
		        		String couponkey,couponvalue;
		        		for(int i=0;i<coupArray.length;i++){
		        			couponkey = coupArray[i].split("=")[0].trim().replace(" ", "");
		        			couponvalue = coupArray[i].split("=")[1].trim();
		        			couponDetailsMap.put(couponkey, couponvalue);
		        		}
		                 
		            }catch(UnknownHostException ue){
		            	Log.e("No Internet", ue.toString());
		            	Toast.makeText(getApplicationContext(),"No Internet Connection" ,Toast.LENGTH_LONG).show();
		            	Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
						startActivity(intent);
						finish();
		            
		            }
				 	catch (ClientProtocolException e) {
				 		Toast.makeText(getApplicationContext(),"Not able to connect" ,Toast.LENGTH_LONG).show();
		            } catch (IOException e) {
		            	Toast.makeText(getApplicationContext(),"Not able to connect" ,Toast.LENGTH_LONG).show();
		            }
		
		
		return couponDetailsMap;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
