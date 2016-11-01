package com.skew.swad;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
import com.skew.swad.R;

public class TodaySpecialActivity extends ActionBarActivity {
//	Button backtomainbtn;
	WebView webView;
	String deviceid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_today_special);
//		backtomainbtn = (Button) findViewById(R.id.backtomainspcl);
		// check if internet connection is available
		
		// get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
            if ( connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
                 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ) {
                // if connected with internet
            	WebView mWebView = null;
            	mWebView = (WebView) findViewById(R.id.webViewToday);
            	mWebView.getSettings().setJavaScriptEnabled(true);
            	mWebView.loadUrl("http://skewtech.com/Restro/admin/todayspecial.php?restoid=swd");
            	
    /*        	backtomainbtn.setOnClickListener(new OnClickListener() {
    				public void onClick(View arg0) {
    					  finish();
    					  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
    					  startActivity(intent);
    				}
    			});
    */        } else if ( 
            connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " No Internet Connection", Toast.LENGTH_LONG).show();
            
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			  startActivity(intent);
			  finish();
          }
	}
	    
	public void onBackPressed() {
	    if (webView.canGoBack()) {
	    	webView.goBack();
	        return;
	    }
	    // Otherwise defer to system default behavior.
	    super.onBackPressed();
	    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.today_special, menu);
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
