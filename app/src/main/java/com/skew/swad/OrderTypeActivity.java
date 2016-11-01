package com.skew.swad;

import com.skew.swad.R;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OrderTypeActivity extends ActionBarActivity {

	Button homedelivery;
	Button takeaway;
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
		startActivity(intent);
		finish();
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_type);
		String fontPath = "fonts/ChampagneLimousines.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		homedelivery = (Button) findViewById(R.id.homedelivery);
		takeaway = (Button) findViewById(R.id.takeaway);
		homedelivery.setTypeface(tf,Typeface.BOLD);
		takeaway.setTypeface(tf,Typeface.BOLD);
	    
		homedelivery.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  Intent intent = new Intent(getApplicationContext(),HDActivity.class);
				  startActivity(intent);
				  finish();
			}
		});
		takeaway.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				FoodMenuActivity.deltype = "TAKEAWAY / PARCEL";
				FoodMenuActivity.delAddress = "HOTEL ADDRESS";
//				Toast.makeText(getApplicationContext(),FoodMenuActivity.deltype ,Toast.LENGTH_LONG).show();					
//				Toast.makeText(getApplicationContext(),FoodMenuActivity.delAddress ,Toast.LENGTH_LONG).show();
			 
				// This is the additional content added
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderTypeActivity.this);
			       alertDialogBuilder.setTitle("Alert");
			       alertDialogBuilder.setMessage("Do you want to Submit Order?");
			       AlertDialog.Builder ad = new AlertDialog.Builder(OrderTypeActivity.this);
			       ad.setCancelable(false);
			       ad.setMessage("Do you want to Submit Order?");
/*			       ad.setMessage(FoodMenuActivity.deltype);
			       ad.setMessage(FoodMenuActivity.delAddress);*/
			       ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int whichButton) {
			        	   Intent intent = new Intent(getApplicationContext(),SubmitOrderActivity.class);
							startActivity(intent);
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
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_type, menu);
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
