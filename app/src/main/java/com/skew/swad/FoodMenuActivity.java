package com.skew.swad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.skew.swad.R;

public class FoodMenuActivity extends ActionBarActivity {
// 	change the backgroud of activity after time interval
	TableLayout menu;
	LinearLayout menu1;
	int id;
	static ArrayList mainmenuList= new ArrayList();
//	Set mainmenuList = new HashSet();
	static Map order = new HashMap();
	static float Bill = 0;
	static String mainmenu = "";
	static ArrayList dishlist = new ArrayList();
	static String deltype = "";
	static String delAddress = "";
	Button reviewOrder;

//	LayoutParams buttonparam = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
		finish();
		Intent intent = new Intent(getApplicationContext(), com.skew.swad.MainActivity.class);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		startActivity(intent);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_menu);
		// Font path
/*	    String fontPath = "fonts/McFoodPoisoning1.ttf";
	 // Loading Font Face
	    Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);*/
		/*Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);*/
		String fontPath = "fonts/ChampagneLimousines.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		
		reviewOrder = (Button) findViewById(R.id.ReviewOrder1);
		reviewOrder.setTypeface(tf, Typeface.BOLD);
		reviewOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
				Intent intent = new Intent(getApplicationContext(), com.skew.swad.OrderActivity.class);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				startActivity(intent);

			}
		});


		if(MainActivity.loadmenu){
		setMainMenu();}
		for(int i=0;i<mainmenuList.size();i++){
		    // get a reference for the TableLayout
//			menu = (TableLayout) findViewById(R.id.menutable);	
			menu1 = (LinearLayout) findViewById(R.id.linearlayout1);	
//		    TableRow row = new TableRow(this);
		    ImageView icon = new ImageButton(this);
			if(mainmenuList.get(i).toString().contains("PARTY MENU")){
				icon.setBackgroundResource(R.drawable.partyicon);}
			else if(mainmenuList.get(i).toString().contains("SANDWICH")){
		    	icon.setBackgroundResource(R.drawable.partyicon);}
		    else if(mainmenuList.get(i).toString().contains("BURGER")){
		    	icon.setBackgroundResource(R.drawable.partyicon);
		    }else if(mainmenuList.get(i).toString().contains("PIZZA")){
		    	icon.setBackgroundResource(R.drawable.partyicon);
		    }else if(mainmenuList.get(i).toString().contains("PULAV")){
		    	icon.setBackgroundResource(R.drawable.partyicon);
		    }else if(mainmenuList.get(i).toString().contains("PAV-BHAJI")){
		    }/*else if(mainmenuList.get(i).toString().contains("INDIAN BREAD")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("BASMATI-KA-KAMAL")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("MAIN COURSE NON VEGETARIAN")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("SALAD, FISH, EGGS")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else{
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }*/
		    LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		    
	        Button mainmenulist = new Button(this);

			if(mainmenuList.get(i).toString().contains("PARTY MENU")){
				mainmenulist.setText("\t"+mainmenuList.get(i).toString()+" (पार्टी के मेनू)"+"\t");
				mainmenulist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.partyicon, 0);
			}else if(mainmenuList.get(i).toString().contains("SANDWICH")){
				mainmenulist.setText("\t"+mainmenuList.get(i).toString()+" (सँडविच)"+"\t");
				mainmenulist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sandwich, 0);
			}else if(mainmenuList.get(i).toString().contains("BURGER")){
				mainmenulist.setText("\t"+mainmenuList.get(i).toString()+" (बर्गर)"+"\t");
				mainmenulist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.burger, 0);
			}else if(mainmenuList.get(i).toString().contains("PIZZA")){
				mainmenulist.setText("\t"+mainmenuList.get(i).toString()+" (पिझ्झा)"+"\t");
				mainmenulist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pizza, 0);
			}else if(mainmenuList.get(i).toString().contains("PULAV")){
				mainmenulist.setText("\t"+mainmenuList.get(i).toString()+" (पुलाव)"+"\t");
				mainmenulist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pullav, 0);
			}else if(mainmenuList.get(i).toString().contains("PAV-BHAJI")){
				mainmenulist.setText("\t"+mainmenuList.get(i).toString()+" (पाव भाजी)"+"\t");
				mainmenulist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pavbhaji, 0);
			}

//			mainmenulist.setText("\t"+mainmenuList.get(i).toString()+"\t");
	        mainmenulist.setId(i);
	        mainmenulist.getBackground().setAlpha(45);
	        mainmenulist.setGravity(Gravity.CENTER);
	        mainmenulist.setTypeface(tf, Typeface.BOLD);

	        mainmenulist.setTypeface(tf,Typeface.BOLD);
//	        mainmenulist.setBackgroundColor(getResources().getColor(R.color.transparent));
	        TextView blankrow = new TextView(this);
	        blankrow.setHeight(10);
		    mainmenulist.setTextColor(getResources().getColor(R.color.brown));
			mainmenulist.setLayoutParams(lp);

	        // Set width	
			
//		    row.addView(icon);
//			row.setLayoutParams(lp);
//			row.addView(mainmenulist);
		    menu1.addView(mainmenulist);
//		    menu1.addView(blankrow);
		    // add the TableRow to the TableLayout
//			menu.addView(row);
//		    menu.addView(row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		    mainmenulist.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					/*final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
					view.startAnimation(buttonClick);*/

					id = view.getId();
					mainmenu = mainmenuList.get(id).toString();

					if(mainmenu.equalsIgnoreCase("PARTY MENU")){
						finish();
						Intent intent = new Intent(getApplicationContext(), PartyMenu.class);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
						startActivity(intent);
					}else {

						finish();
						Intent intent = new Intent(getApplicationContext(), DishesActivity.class);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
						startActivity(intent);
					}
/*
					  id = view.getId();
					  mainmenu = mainmenuList.get(id).toString();
*/
//					  Toast.makeText(getApplicationContext(), "clicked: "+mainmenuList.get(id), Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
/*	public void HandleClick(View view) {
		Toast.makeText(getApplicationContext(), view.getId(), Toast.LENGTH_LONG).show();
    }
*/
	public void setMainMenu(){
		InputStream inputStream = getResources().openRawResource(R.raw.dishtable);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 	try {
	 		br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
	 		        // use comma as separator
				String[] dishes = line.split(cvsSplitBy);
				if(!mainmenuList.contains(dishes[0])){
				mainmenuList.add(dishes[0]);
				}
//				dishlist.add(dishes[0]+","+dishes[1]+","+dishes[2]);
				dishlist.add(dishes[0]+","+dishes[1]+","+dishes[2]+","+dishes[3]);
	 		}
	 	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		MainActivity.loadmenu = false;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_menu, menu);
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
