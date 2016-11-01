package com.skew.swad;

import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.skew.swad.R;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SubmitOrderActivity extends ActionBarActivity {
	String deviceid;
	String orderDesc;
	String billamt;
	String deltype;
	String deladdress;
	InputStream is=null;
	private SharedPreferences sharedPrefs;
	private String sharedPrefsName = "swd";
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
			Intent intent = new Intent(getApplicationContext(),OrderTypeActivity.class);
			startActivity(intent);
			finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_order);
		
//Check if Registered Address or New Address
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		deviceid = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
		orderDesc = FoodMenuActivity.order.toString();
		if(OrderActivity.couponApplied){
			billamt = String.valueOf(OrderActivity.billAfterDiscount);
		}else{
			billamt = String.valueOf(FoodMenuActivity.Bill);
		}
        
//        billamt = String.valueOf(displaybill);
		// check if client is registered
//        InputStream input = null;
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
//	            	Toast.makeText(getApplicationContext(),FoodMenuActivity.deltype ,Toast.LENGTH_LONG).show();					
//					Toast.makeText(getApplicationContext(),FoodMenuActivity.delAddress ,Toast.LENGTH_LONG).show();
					FoodMenuActivity.order.clear();
					FoodMenuActivity.Bill = (float) 0.00;
					OrderActivity.couponApplied = false;
					Intent intent = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(intent);
					finish();
	            }else if ( 
	  	              connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
		              connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
	            	  Toast.makeText(getApplicationContext(),"No Internet Connection!!!" ,Toast.LENGTH_LONG).show();
		              Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
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
        //	Log.e("oops Something went wrong", e.toString());
        	Toast.makeText(getApplicationContext(),"oops Something went wrong!!!" ,Toast.LENGTH_LONG).show();
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
    	nameValuePairs.add(new BasicNameValuePair("coupon",OrderActivity.couponcode));
    	nameValuePairs.add(new BasicNameValuePair("restoid",MainActivity.restaurantID));
    	nameValuePairs.add(new BasicNameValuePair("deltype",FoodMenuActivity.deltype));
    	nameValuePairs.add(new BasicNameValuePair("deladdress",FoodMenuActivity.delAddress));
    	
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
	        Toast.makeText(getApplicationContext(), "Order Submitted Successfully", Toast.LENGTH_LONG).show();
//	        Toast.makeText(getApplicationContext(), FoodMenuActivity.deltype, Toast.LENGTH_LONG).show();
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
        	//Log.e("oops Something went wrong", e.toString());
        	Toast.makeText(getApplicationContext(),"oops Something went wrong!!!" ,Toast.LENGTH_LONG).show();
        }
        
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit_order, menu);
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
