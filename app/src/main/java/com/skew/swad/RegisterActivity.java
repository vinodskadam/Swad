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

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.skew.swad.R;

public class RegisterActivity extends ActionBarActivity {
	EditText fname;
	EditText lname;
	EditText mobileno;
	EditText emailid;
	EditText address;
	String deviceID;
	Button registerbtn;
	String fname_val;
	String lname_val;
	String mobileno_val;
	String email_val;
	String add_val;
	String ifRegistered;
	private String sharedPrefsName = "swd";
	private SharedPreferences sharedPrefs;
	
	InputStream is=null;
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		String fontPath = "fonts/ChampagneLimousines.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		fname = (EditText) findViewById(R.id.fname);
		fname.setTypeface(tf, Typeface.BOLD);

		lname = (EditText) findViewById(R.id.lname);
		lname.setTypeface(tf, Typeface.BOLD);

		mobileno = (EditText) findViewById(R.id.mobileno);
		mobileno.setTypeface(tf, Typeface.BOLD);

		emailid = (EditText) findViewById(R.id.email);
		emailid.setTypeface(tf, Typeface.BOLD);

		address = (EditText) findViewById(R.id.address);
		address.setTypeface(tf, Typeface.BOLD);
	

	
	registerbtn = (Button) findViewById(R.id.registerbtn);
	registerbtn.setTypeface(tf,Typeface.BOLD);
	// click button action		
	registerbtn.setOnClickListener(new OnClickListener() {
		@SuppressLint("NewApi")
		public void onClick(View arg0) {
	//		if(clientDetails()){
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				// COde to get phone IDs
//				TelephonyManager TM = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

				// Android Unique ID
				 deviceID = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
				 fname_val = fname.getText().toString();
				 lname_val = lname.getText().toString();
		         mobileno_val = mobileno.getText().toString();
		         email_val = emailid.getText().toString();
		         add_val = address.getText().toString();
		         if(fname_val.trim().equals("")||lname_val.trim().equals("")||mobileno_val.trim().equals("")||email_val.trim().equals("")||email_val.trim().equals("")){
		        	 Toast.makeText(getApplicationContext(), "Please Fill the details", Toast.LENGTH_LONG).show();
		         }else{
		        	 insert();
					 finish();
					 Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
					 startActivity(intent);	 
		         }
				
		}
	});
//	Toast.makeText(getApplicationContext(), fname.toString()+lname.toString()+mobileno.toString()+email.toString()+address.toString(), Toast.LENGTH_LONG).show();	
	}
	public void insert()
    {
		try
    	{
		// Values sent to server
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	nameValuePairs.add(new BasicNameValuePair("fname",fname_val));
    	nameValuePairs.add(new BasicNameValuePair("lname",lname_val));
    	nameValuePairs.add(new BasicNameValuePair("mobileno",mobileno_val));
    	nameValuePairs.add(new BasicNameValuePair("emailid",email_val));
    	nameValuePairs.add(new BasicNameValuePair("address",add_val));
    	nameValuePairs.add(new BasicNameValuePair("deviceid",deviceID));
    	nameValuePairs.add(new BasicNameValuePair("restoid","swd"));
    	
    		HttpClient httpclient = new DefaultHttpClient();
//	        HttpPost httppost = new HttpPost("http://10.0.2.2/leadgen/submitlead.php");
	        HttpPost httppost = new HttpPost("http://skewtech.com/Restro/admin/userreg.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	        
	        // Entries in sharedpreference
	        sharedPrefs = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);
			final SharedPreferences.Editor editor = sharedPrefs.edit();
			editor.putString("fname",fname_val);
			editor.putString("lname",lname_val);
			editor.putString("dname",fname_val+","+lname_val);
			editor.putString("mobiles",mobileno_val);
			editor.putString("address",add_val);
			editor.putBoolean("isRegistered",true);
				//---saves the values---
			editor.commit();
	        Toast.makeText(getApplicationContext(),"Thanks for Registration!!!" ,Toast.LENGTH_LONG).show();
	       /* if(clientDetails()){
	        	Toast.makeText(getApplicationContext(),"Registered Device" ,Toast.LENGTH_LONG).show();	
	        }*/
	}catch(UnknownHostException ue){
    	Log.e("No Internet", ue.toString());
    	Toast.makeText(getApplicationContext(),"No Internet Connection" ,Toast.LENGTH_LONG).show();
    }
        catch(Exception e)
	{
        	Log.e("Fail 1", e.toString());
//	    	Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        	Toast.makeText(getApplicationContext(),"Oops!!! Something went wrong" ,Toast.LENGTH_LONG).show();
	}     
        
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
