package com.skew.swad;
import java.io.InputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.skew.swad.R;

public class MainActivity extends ActionBarActivity {
	Button foodmenu;
	static boolean loadmenu = true;
	Button feedback;
	Button registerMe;
//	Button todayspecialbtn;
	Button yourorders;
//	Button closeapp;
//	Button tablebooking;

	Button contactus;
	Button sharewithfriends;
	InputStream is=null;
	final Context context = this;
	private TextView dname;
	private SharedPreferences sharedPrefs;
	static String sharedPrefsName = "swd";
	boolean isValidated;
	Intent intent;
	static String deviceID;
	static String restaurantID = "swd";
	static String tableno = "";
	static String orderType = "";
	Button discountstab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ParseAnalytics.trackAppOpenedInBackground(getIntent());
		// Save the current Installation to Parse.
		ParseInstallation.getCurrentInstallation().saveInBackground();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		deviceID = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
		isValidated = false;
		// check for the network and then procees for the feedback and other option
		WebView mWebView = null;
	    mWebView = (WebView) findViewById(R.id.webView1);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.loadUrl("file:///android_asset/slide.html");
	    String fontPath = "fonts/ChampagneLimousines.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

/*	 // Font path
        String fontPath = "fonts/McFoodPoisoning1.ttf";
     // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);*/

    /*    todayspecialbtn = (Button) findViewById(R.id.Todayspecial);
	    todayspecialbtn.setTypeface(null,Typeface.BOLD);
	*/
		discountstab = (Button) findViewById(R.id.discountstab);
		discountstab.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.discount, 0);
//		discountstab.setGravity(Gravity.CENTER_VERTICAL);
		discountstab.setTypeface(tf,Typeface.BOLD);

		feedback = (Button) findViewById(R.id.feedback);
		feedback.setTypeface(tf,Typeface.BOLD);
		feedback.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.feedback, 0);

		foodmenu = (Button)findViewById(R.id.FoodMenu);
		foodmenu.setTypeface(tf,Typeface.BOLD);
		foodmenu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.foodmenu, 0);

	    registerMe = (Button) findViewById(R.id.Registerme);
	    registerMe.setTypeface(tf,Typeface.BOLD);
	    registerMe.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.registertoorder, 0);

	    yourorders = (Button) findViewById(R.id.yourorders);
	    yourorders.setTypeface(tf,Typeface.BOLD);
	    yourorders.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.trackorder, 0);

/*	    closeapp = (Button) findViewById(R.id.closeapp);
	    closeapp.setTypeface(tf,Typeface.BOLD);
*/
	    contactus = (Button) findViewById(R.id.contactus);
	    contactus.setTypeface(tf,Typeface.BOLD);
	    contactus.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.contactus, 0);

	    sharewithfriends = (Button) findViewById(R.id.sharewithfriends);
//		sharewithfriends.setTextColor(getResources().getColor(R.color.brown));
		sharewithfriends.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.share, 0);
//		sharewithfriends.setGravity(Gravity.CENTER_VERTICAL);
		sharewithfriends.setTypeface(tf,Typeface.BOLD);

	 /*   tablebooking.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  finish();
				  Intent intent = new Intent(getApplicationContext(),BookatableActivity.class);
				  startActivity(intent);
			}
		});*/
	    contactus.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  finish();
				  Intent intent = new Intent(getApplicationContext(),ContactActivity.class);
				  overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				  startActivity(intent);
			}
		});
	    
/*	    closeapp.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

	            alertDialogBuilder.setTitle("Alert");
	            alertDialogBuilder.setMessage("Do you really want to exit?");

	            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
	            ad.setCancelable(false);
	    //        ad.setTitle("EXIT CONFIRMATION");
	            ad.setMessage("Are you sure you want to exit?");
	            ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                	FoodMenuActivity.order.clear();
	    				FoodMenuActivity.Bill = (float) 0.0;
	                	finish();
	                }
	            });
	            ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
	                @Override
	                public void onClick(DialogInterface dialog, int which) {
	                    // TODO Auto-generated method stub
	                     dialog.cancel();
	                }
	            }); 

	            ad.show();

	          }
	        });*/
	    
	    yourorders.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
		/*		 final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
				 arg0.startAnimation(buttonClick);  
		*/		Intent intent = new Intent(getApplicationContext(),YourOrdersActivity.class);
				  startActivity(intent);
				  overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				  finish();
			}
		});
	    
		foodmenu.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				/*   final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
				   arg0.startAnimation(buttonClick);
				*/
				finish();
				Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				startActivity(intent);
			}
		});
		
		/*todayspecialbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  finish();
				  Intent intent = new Intent(getApplicationContext(),TodaySpecialActivity.class);
				  startActivity(intent);
			}
		});*/

		discountstab.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				/*finish();
				Intent intent = new Intent(getApplicationContext(),DiscountsActivity.class);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				startActivity(intent);*/
				// get Connectivity Manager object to check connection
				ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
				if ( connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
						connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ) {
					finish();
					Intent intent = new Intent(getApplicationContext(),DiscountsActivity.class);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		feedback.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  finish();
				  Intent intent = new Intent(getApplicationContext(),FeedbackActivity.class);
				  overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				  startActivity(intent);
			}
		});
		
		registerMe.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
				  startActivity(intent);
				  overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				  finish();
			}
		});
		sharewithfriends.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
				sharingIntent.setType("text/plain");
				String shareBody = "Swad Restaurant"+
									"\nAddress: Plot no.35, infront of Sharvin Hotel,\nBelow Bansod Classes,\nSutgirni Chowk Road, Aurangabad\n"+
											"\n https://play.google.com/store/apps/details?id=com.skew.swad";
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Swad");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
				startActivity(Intent.createChooser(sharingIntent, "Share via"));
			}
		});
		
	}

	public void onBackPressed() {

        // TODO Auto-generated method stub
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

       alertDialogBuilder.setTitle("Alert");
       alertDialogBuilder.setMessage("Do you really want to exit?");

       AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
       ad.setCancelable(false);
//        ad.setTitle("EXIT CONFIRMATION");
       ad.setMessage("Are you sure you want to exit?");
       ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int whichButton) {
        	   FoodMenuActivity.order.clear();
        	   FoodMenuActivity.Bill = (float) 0.0;
        	   overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        	   finish();
           }
       });
       ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               // TODO Auto-generated method stub
                dialog.cancel();
           }
       }); 

       ad.show();

     }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
