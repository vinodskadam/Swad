package com.skew.swad;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.skew.swad.R;

public class DishesActivity extends ActionBarActivity {
	LinearLayout dishlinearlayout;
	ArrayList<String> selectedDishList = new ArrayList<String>();
	String[] temp ;
	int id;
	String tempDish;
	String dishTemp;
	float tempRate;
	Context context;
	Button reviewOrder;
	int count;
	TextView menuname;
	String dishinfovalue;
	Button qtyBillBtn;
	
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
//		finish();
		finish();
		Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		startActivity(intent);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dishes);
		String fontPath = "fonts/ChampagneLimousines.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

//		LayoutParams buttonparam = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0.75f);
		context = getApplicationContext();
	/*	 String fontPath = "fonts/McFoodPoisoning1.ttf";
		  // Loading Font Face
		     Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);*/
		menuname = (TextView) findViewById(R.id.menuname);
//		menuname.setTextSize(16);
		menuname.setTypeface(tf, Typeface.BOLD); 
		/*Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);*/
//		menuname.setText("Table:"+MainActivity.tableno+MainActivity.orderType+" "+FoodMenuActivity.mainmenu);
		menuname.setText(FoodMenuActivity.mainmenu);

		if(FoodMenuActivity.mainmenu.contains("PARTY MENU")){

		}else if(FoodMenuActivity.mainmenu.contains("SANDWICH")){
			menuname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sandwich, 0);
		}else if(FoodMenuActivity.mainmenu.contains("BURGER")){
			menuname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.burger, 0);
		}else if(FoodMenuActivity.mainmenu.contains("PIZZA")){
			menuname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pizza, 0);
		}else if(FoodMenuActivity.mainmenu.contains("PULAV")){
			menuname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pullav, 0);
		}else if(FoodMenuActivity.mainmenu.contains("PAV-BHAJI")){
			menuname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pavbhaji, 0);
		}
		menuname.setTextColor(getResources().getColor(R.color.golden));
		menuname.setBackgroundColor(getResources().getColor(R.color.green));

//		menuname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sandwich, 0);
		
		qtyBillBtn = (Button) findViewById(R.id.qtybill);
//		qtyBillBtn.setTextSize(16);
		qtyBillBtn.setTypeface(tf, Typeface.BOLD); 
	
//		qtyBillBtn.setText(FoodMenuActivity.mainmenu+FoodMenuActivity.Bill);
		qtyBillBtn.setText("Bill: "+FoodMenuActivity.Bill);
		qtyBillBtn.setTextColor(getResources().getColor(R.color.maroon));
		qtyBillBtn.setBackgroundColor(getResources().getColor(R.color.golden));
		
		reviewOrder = (Button) findViewById(R.id.ReviewOrder);
		reviewOrder.setTypeface(tf, Typeface.BOLD);
		reviewOrder.setTextColor(getResources().getColor(R.color.maroon));
		reviewOrder.setTextSize(20);
		reviewOrder.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.addtocart, 0);
		reviewOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
		Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
				finish();
				intent.putStringArrayListExtra("selectedDishList", selectedDishList);

				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				startActivity(intent);
			}
		});
		
		for (Iterator<String> it = FoodMenuActivity.dishlist.iterator(); it.hasNext();) {
				temp = it.next().split(",");
				if(temp[0].contains(FoodMenuActivity.mainmenu)){
					selectedDishList.add(temp[1]+","+temp[2]+","+temp[3]);	
					
				}
			}
		for(int i=0;i<selectedDishList.size();i++){
		    // get a reference for the TableLayout
			dishlinearlayout = (LinearLayout) findViewById(R.id.dishlinearlayout);	
			tempDish = selectedDishList.get(i).toString().split(",")[0];
		    tempRate = Float.parseFloat(selectedDishList.get(i).toString().split(",")[1]);
		    dishinfovalue = selectedDishList.get(i).toString().split(",")[2];
			
//			final Button dishBtn = new Button(this);
			final TextView dishBtn = new TextView(this);
	//		final Button dishBtn = new Button(this, null, android.R.attr.buttonStyleSmall);
			dishBtn.setId(i);
//			dishBtn.getBackground().setAlpha(0);
			
			dishBtn.setTextSize(15);
			dishBtn.setTypeface(tf,Typeface.BOLD);
			dishBtn.setText(tempDish+"\t - Rs."+tempRate);
			dishBtn.setGravity(Gravity.CENTER_VERTICAL);
			dishBtn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			dishBtn.setTextColor(getResources().getColor(R.color.black));
//			dishBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.adddish, 0);
			
		    count = 0;

		    TextView dishInfo = new TextView(this);
		    dishInfo.setText(dishinfovalue);
		    dishInfo.setTextSize(15);
		    dishInfo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		//    dishlinearlayout.addView(dishBtn);
		    if(dishinfovalue.trim().equalsIgnoreCase("-")){
		    	dishlinearlayout.addView(dishBtn);
		    }else if(dishinfovalue.trim().equalsIgnoreCase("~")){
		    	final Button headerBtn = new Button(this);
				headerBtn.setId(i);
				headerBtn.setTypeface(null, Typeface.BOLD);
				headerBtn.setText(tempDish);
				headerBtn.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
				headerBtn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				headerBtn.setTextColor(getResources().getColor(R.color.maroon));
				headerBtn.setBackgroundColor(getResources().getColor(R.color.yellow));
				dishlinearlayout.addView(headerBtn);
		    }else{
		    	dishlinearlayout.addView(dishBtn);
		    	 dishlinearlayout.addView(dishInfo);
		    }
		    
		    LinearLayout plusminuslayout = new LinearLayout(getApplicationContext());
		    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    params.gravity=Gravity.RIGHT;
		    params.setMargins(0, 0, 0, 5);
		    plusminuslayout.setLayoutParams(params);
		    plusminuslayout.setOrientation(LinearLayout.HORIZONTAL);
		    
		    final ImageButton addbtn = new ImageButton(this);
		    addbtn.setBackgroundResource(R.drawable.adddish);
		    addbtn.setId(i);
		    final TextView txtcount;
		    txtcount = new TextView(getApplicationContext());
		    txtcount.setTypeface(tf, Typeface.BOLD);
		    txtcount.setTextColor(getResources().getColor(R.color.maroon));
		    txtcount.setId(i);
			
		    boolean tempCountBoolean = FoodMenuActivity.order.containsKey(tempDish);
		    if(tempCountBoolean){
		    	int tempCount = Integer.parseInt(FoodMenuActivity.order.get(tempDish).toString().split("#")[1]);
		    	txtcount.setText("  "+tempCount+"  ");	
		    }else{
		    	txtcount.setText("  "+0+"  ");
		    }
		    
	//	    txtcount.setTextColor(getResources().getColor(R.color.black));
		    
		    final ImageButton rembtn = new ImageButton(this);
		    rembtn.setBackgroundResource(R.drawable.removedish);
		    rembtn.setId(i);
		    /*if(i%2==0){
		    	plusminuslayout.setBackgroundColor(getResources().getColor(R.color.gray));
			}*/
		    final Button marking = new Button(this);
		    marking.setBackgroundColor(getResources().getColor(R.color.White));
		    marking.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1));
		    		    
		    plusminuslayout.addView(addbtn);
		    plusminuslayout.addView(txtcount);
		    plusminuslayout.addView(rembtn);
		    
		    if(dishinfovalue.trim().equalsIgnoreCase("~")){
		    
		    }else{
		    	dishlinearlayout.addView(plusminuslayout);
			    dishlinearlayout.addView(marking);	
		    }
		 // action listerner
		    addbtn.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
//					dishlinearlayout.invalidate();
	//				dishlinearlayout.refreshDrawableState();
	//				dishlinearlayout.removeAllViews();
					Toast toast = new Toast(getApplicationContext());
				    ImageView view1  = new ImageView(getApplicationContext()); 	 
				    addbtn.setBackgroundResource(R.drawable.adddisha);
				    view1.setBackgroundResource(R.drawable.addedd); 
				    toast.setGravity(Gravity.CENTER, 0, 0);
				    toast.setDuration(Toast.LENGTH_SHORT);
				    toast.setView(view1); 
				    toast.show();
					  id = view.getId();
					  dishlinearlayout = (LinearLayout) findViewById(R.id.dishlinearlayout);	
					  tempDish = selectedDishList.get(id).toString().split(",")[0];
					  tempRate = Float.parseFloat(selectedDishList.get(id).toString().split(",")[1]);
					  dishinfovalue = selectedDishList.get(id).toString().split(",")[2];

					  
/*					  tempDish = selectedDishList.get(id).toString().split("#")[0];
					  tempRate = Float.parseFloat(selectedDishList.get(id).toString().split("#")[1]);
					  dishinfovalue = selectedDishList.get(id).toString().split("#")[2];
*//*					  	dishTemp = tempDish.split("-")[0].trim().replace(" ", "").replace("/", "");
						dishTemp = dishTemp.toLowerCase();
*/						
						
					if(FoodMenuActivity.order.isEmpty()){
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
						FoodMenuActivity.Bill = tempRate;
					}else{
					if(FoodMenuActivity.order.containsKey(tempDish)){
						count = Integer.parseInt(FoodMenuActivity.order.get(tempDish).toString().split("#")[1])+1;
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+count);
						FoodMenuActivity.Bill = FoodMenuActivity.Bill + tempRate;
					}else{
					FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
					FoodMenuActivity.Bill = FoodMenuActivity.Bill + tempRate;
					}
					}
					boolean tempCountBoolean = FoodMenuActivity.order.containsKey(tempDish);
				    if(tempCountBoolean){
				    	int tempCount = Integer.parseInt(FoodMenuActivity.order.get(tempDish).toString().split("#")[1]);
				    	txtcount.setText("  "+tempCount+"  ");	
				    }else{
				    	txtcount.setText("  "+0+"  ");
				    }
				    qtyBillBtn.setText("Bill: "+FoodMenuActivity.Bill);
					/*finish();
					Intent intent = new Intent(getApplicationContext(),DishesActivity.class);
					startActivity(intent);*/
				}
			});
		    
		    
			rembtn.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					Toast toast = new Toast(getApplicationContext());
				    ImageView view1  = new ImageView(getApplicationContext()); 	 
				    rembtn.setBackgroundResource(R.drawable.removedish);
				    view1.setBackgroundResource(R.drawable.removed); 
				    toast.setGravity(Gravity.CENTER, 0, 0);
				    toast.setDuration(Toast.LENGTH_SHORT);
				    toast.setView(view1); 
				    toast.show();
					  id = view.getId();
					  dishlinearlayout = (LinearLayout) findViewById(R.id.dishlinearlayout);	
					  tempDish = selectedDishList.get(id).toString().split(",")[0];
					  tempRate = Float.parseFloat(selectedDishList.get(id).toString().split(",")[1]);
					  dishinfovalue = selectedDishList.get(id).toString().split(",")[2];
				/*	  	dishTemp = tempDish.split("-")[0].trim().replace(" ", "").replace("/", "");
						dishTemp = dishTemp.toLowerCase();*/
					if(FoodMenuActivity.order.isEmpty()){/*
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
						FoodMenuActivity.Bill = tempRate;
					*/}else{
					if(FoodMenuActivity.order.containsKey(tempDish)){
						count = Integer.parseInt(FoodMenuActivity.order.get(tempDish).toString().split("#")[1])-1;
						if(count==0){
							FoodMenuActivity.Bill = FoodMenuActivity.Bill - tempRate;
							FoodMenuActivity.order.remove(tempDish);
							}else{
								FoodMenuActivity.order.put(tempDish, tempRate+"#"+count);
								FoodMenuActivity.Bill = FoodMenuActivity.Bill - tempRate;
							}
					}else{/*
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
						FoodMenuActivity.Bill = FoodMenuActivity.Bill + tempRate;
					*/}
					}
					qtyBillBtn.setText("Bill: "+FoodMenuActivity.Bill);
					boolean tempCountBoolean = FoodMenuActivity.order.containsKey(tempDish);
				    if(tempCountBoolean){
				    	int tempCount = Integer.parseInt(FoodMenuActivity.order.get(tempDish).toString().split("#")[1]);
				    	txtcount.setText("  "+tempCount+"  ");	
				    }else{
				    	txtcount.setText("  "+0+"  ");
				    }
					
					/*finish();
					Intent intent = new Intent(getApplicationContext(),DishesActivity.class);
					startActivity(intent);*/
				}
			});
		    
		    
		    
		    /*// action listerner
		    dishBtn.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
//					dishBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.adddisha, 0);
					
//					dishBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.guava, 0, R.drawable.adddisha, 0);
					Toast toast = new Toast(getApplicationContext());
				    ImageView view1  = new ImageView(getApplicationContext()); 	 
				    view1.setBackgroundResource(R.drawable.addedd); 
				    toast.setGravity(Gravity.CENTER, 0, 0);
				    toast.setDuration(Toast.LENGTH_SHORT);
				    toast.setView(view1); 
				    toast.show();
					  id = view.getId();
					  dishlinearlayout = (LinearLayout) findViewById(R.id.dishlinearlayout);	
					  tempDish = selectedDishList.get(id).toString().split("#")[0];
					  tempRate = Float.parseFloat(selectedDishList.get(id).toString().split("#")[1]);
					  dishinfovalue = selectedDishList.get(id).toString().split("#")[2];
				  
//					  	tempDish = selectedDishList.get(id).toString().split("#")[0];
//					    dishTemp = tempDish.replace(")", "").replace("(", "#").split("#")[1].trim().split("-")[0];
//						dishTemp = dishTemp.replace(" ", "").replace("/", "");
					  	dishTemp = tempDish.split("-")[0].trim().replace(" ", "").replace("/", "");
						dishTemp = dishTemp.toLowerCase();
						String uri = "@drawable/"+dishTemp;  
						int imageResource = getResources().getIdentifier(uri, null, getPackageName());
						dishBtn.setCompoundDrawablesWithIntrinsicBounds(imageResource, 0, R.drawable.adddisha, 0);
//					    tempRate = Float.parseFloat(selectedDishList.get(i).toString().split(",")[1]);
//					    tempRate =  Float.parseFloat(MainActivity.rateMap.get(tempDish).toString());
					    try{
					    	 dishTemp = tempDish.replace(")", "").replace("(", "#").split("#")[1].trim();
					    	 tempRate = Float.parseFloat(tempDish.split("#")[1].trim());
					    	 //	 tempRate =  Float.parseFloat(MainActivity.rateMap.get(dishTemp).toString());
						    }catch(NullPointerException ne){
					        	Log.e("No Internet", ne.toString());
					
					        }catch(Exception e){
					        	Log.e("No Internet", e.toString());
					
					        }	
						
					//  tempRate = Float.parseFloat(selectedDishList.get(id).toString().split(",")[1]);
//					  tempRate =  Float.parseFloat(MainActivity.rateMap.get(tempDish).toString());
					if(FoodMenuActivity.order.isEmpty()){
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
						FoodMenuActivity.Bill = tempRate;
					}else{
					if(FoodMenuActivity.order.containsKey(tempDish)){
						count = Integer.parseInt(FoodMenuActivity.order.get(tempDish).toString().split("#")[1])+1;
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+count);
						FoodMenuActivity.Bill = FoodMenuActivity.Bill + tempRate;
					}else{
					FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
					FoodMenuActivity.Bill = FoodMenuActivity.Bill + tempRate;
					}
					}
				}
			});
*/		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dishes, menu);
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
