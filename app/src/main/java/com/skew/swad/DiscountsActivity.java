package com.skew.swad;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;
import com.skew.swad.R;

public class DiscountsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discounts);

		// get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
            if ( connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
                 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ) {
                // if connected with internet
            	WebView mWebView = null;
            	mWebView = (WebView) findViewById(R.id.webViewDiscounts);
            	mWebView.getSettings().setJavaScriptEnabled(true);
//            	mWebView.loadUrl("http://skewtech.com/Restro/admin/todayspecial.php?restoid="+MainActivity.restaurantID);
            	mWebView.loadUrl("http://restro.skewtech.com/admin/offers.php?restoid="+MainActivity.restaurantID);
            	
          } else if ( 
            connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " No Internet Connection", Toast.LENGTH_LONG).show();
            
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			  startActivity(intent);
			  finish();
          }
		
	}

	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.discounts, menu);
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
