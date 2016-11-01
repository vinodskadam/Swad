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
import android.widget.EditText;
import android.widget.Toast;

public class HDActivity extends ActionBarActivity {
	Button toRegAddress;
	Button toNonRegAddress;
	public void onBackPressed() {
			Intent intent = new Intent(getApplicationContext(),OrderTypeActivity.class);
			startActivity(intent);
			finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hd);
		
		String fontPath = "fonts/ChampagneLimousines.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		
		toRegAddress = (Button) findViewById(R.id.radd);
		toRegAddress.setTypeface(tf,Typeface.BOLD);
		toRegAddress.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {

					FoodMenuActivity.deltype = "HOME DELIVERY";
					FoodMenuActivity.delAddress = "REGISTERED ADDRESS";
					// This is the additional content added
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HDActivity.this);
				       alertDialogBuilder.setTitle("Alert");
				       alertDialogBuilder.setMessage("Do you want to Submit Order?");

				       AlertDialog.Builder ad = new AlertDialog.Builder(HDActivity.this);
				       ad.setCancelable(false);
				       ad.setMessage("Do you want to Submit Order?");
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
		toNonRegAddress = (Button) findViewById(R.id.nradd);
		toNonRegAddress.setTypeface(tf,Typeface.BOLD);
		toNonRegAddress.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
						EditText nadd = (EditText) findViewById(R.id.deliveryadd);
						String  stringDeliveryAdd = nadd.getText().toString(); 
						if(stringDeliveryAdd.trim().isEmpty()){
							Toast.makeText(getApplicationContext(), "Please enter delivery Address", Toast.LENGTH_LONG).show();	
						}else{
							FoodMenuActivity.deltype = "HOME DELIVERY";
							FoodMenuActivity.delAddress = stringDeliveryAdd;
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HDActivity.this);
						       alertDialogBuilder.setTitle("Alert");
						       alertDialogBuilder.setMessage("Do you want to Submit Order?");
						       AlertDialog.Builder ad = new AlertDialog.Builder(HDActivity.this);
						       ad.setCancelable(false);
						       ad.setMessage("Do you want to Submit Order?");
						       ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int whichButton) {
										Toast.makeText(getApplicationContext(),FoodMenuActivity.deltype ,Toast.LENGTH_LONG).show();					
										Toast.makeText(getApplicationContext(),FoodMenuActivity.delAddress ,Toast.LENGTH_LONG).show();
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
						}	
				});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hd, menu);
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
