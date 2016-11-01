package com.skew.swad;

import com.skew.swad.R;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ZeroActivity extends ActionBarActivity {

	Button enterfoodzone;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zero);
		
		enterfoodzone = (Button)findViewById(R.id.foodzone);
		enterfoodzone.setTypeface(null,Typeface.BOLD);
		
		enterfoodzone.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				/*   final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
				   arg0.startAnimation(buttonClick);
				*/  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				  startActivity(intent);
				  finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zero, menu);
		return true;
	}
	
	public void onBackPressed() {

        // TODO Auto-generated method stub
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ZeroActivity.this);

       alertDialogBuilder.setTitle("Alert");
       alertDialogBuilder.setMessage("Do you really want to exit?");

       AlertDialog.Builder ad = new AlertDialog.Builder(ZeroActivity.this);
       ad.setCancelable(false);
       ad.setMessage("Are you sure you want to exit?");
       ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int whichButton) {
        	 /*  FoodMenuActivity.order.clear();
        	   FoodMenuActivity.Bill = (float) 0.0;
        	 */  finish();
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
