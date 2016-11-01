package com.skew.swad;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;


public class PartyMenu extends AppCompatActivity {

    EditText inputQty1;
    EditText inputQty2;
    EditText inputQty3;
    Button submitPartyOrder;
    TextView totalbillText;
    String billtext;
    Button qtybtn1;
    Button qtybtn2;
    Button qtybtn3;
    static EditText party_date;
    static EditText party_time;
    EditText party_address;
    int totalbill;

    String inputVal1Text ;
    String inputVal2Text ;
    String inputVal3Text ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_menu);

        party_date=(EditText) findViewById(R.id.party_date_in);
        party_time=(EditText) findViewById(R.id.partytime);
        party_address = (EditText) findViewById(R.id.party_add);
        inputQty1 = (EditText) findViewById(R.id.qtyone);
        inputQty2 = (EditText) findViewById(R.id.qtytwo);
        inputQty3 = (EditText) findViewById(R.id.qtythree);
        submitPartyOrder = (Button) findViewById(R.id.submitpartyorder);
        totalbillText  = (TextView) findViewById(R.id.totalbill);

        qtybtn1 = (Button) findViewById(R.id.t1);
        qtybtn2 = (Button) findViewById(R.id.t2);
        qtybtn3 = (Button) findViewById(R.id.t3);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        party_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });
        party_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        // on focus and action of time
        party_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "timePicker");
                }
            }
        });
        party_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
        /*party_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    qtybtn1.setText(inputVal1Text);
                }
            }
        });*/


        submitPartyOrder.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                inputVal1Text = inputQty1.getText().toString();
                inputVal2Text = inputQty2.getText().toString();
                inputVal3Text = inputQty3.getText().toString();
                int inputVal1;
                int inputVal2;
                int inputVal3;
                billtext = "";
                if (inputVal1Text.trim().isEmpty()) {
                    inputVal1 = 0;
                    qtybtn1.setText("Rs." + inputVal1);
                } else {
                    inputVal1 = Integer.parseInt(inputVal1Text);
                    inputVal1 = inputVal1 * 80;
                    qtybtn1.setText("Rs." + inputVal1);
//                  billtext = "UNLIMITED PAV BHAJI (पाव भाजी) [" + inputVal1 / 80 + "] = Rs." + inputVal1 + "\n";
                    billtext = "UNLIMITED PAV BHAJI [" + inputVal1 / 80 + "] = Rs." + inputVal1 + "\n";
                    FoodMenuActivity.order.put("U" +
                            "" +
                            "NLIMITED PAV BHAJI", "80" + "#" + "" + inputVal1 / 80);
                }
                if (inputVal2Text.trim().isEmpty()) {
                    inputVal2 = 0;
                    qtybtn2.setText("Rs." + inputVal2);
                } else {
                    inputVal2 = Integer.parseInt(inputVal2Text);
                    inputVal2 = inputVal2 * 60;
                    qtybtn2.setText("Rs." + inputVal2);
//                    billtext = billtext + "UNLIMITED PULAV (पुलाव) [" + inputVal2 / 60 + "]  = Rs." + inputVal2 + "\n";
                    billtext = billtext + "UNLIMITED PULAV [" + inputVal2 / 60 + "]  = Rs." + inputVal2 + "\n";
                    FoodMenuActivity.order.put("UNLIMITED PULAV", "60" + "#" + "" + inputVal2 / 60);
                }
                if (inputVal3Text.trim().isEmpty()) {
                    inputVal3 = 0;
                    qtybtn3.setText("Rs." + inputVal3);
                } else {
                    inputVal3 = Integer.parseInt(inputVal3Text);
                    inputVal3 = inputVal3 * 120;
                    qtybtn3.setText("Rs." + inputVal3);
//                  billtext = billtext + "UNLIMITED (PAV BHAJI + PULAV)(वपाव भाजी + पुलाव)[" + inputVal3 / 120 + "]  = Rs." + inputVal3 + "\n";
                    billtext = billtext + "UNLIMITED (PAV BHAJI + PULAV)[" + inputVal3 / 120 + "]  = Rs." + inputVal3 + "\n";
                    FoodMenuActivity.order.put("UNLIMITED (PAV BHAJI + PULAV)", "120" + "#" + "" + inputVal3 / 120);
                }

              totalbill = inputVal1 + inputVal2 + inputVal3;

                billtext = billtext + "\n"+"Date: "+party_date.getText().toString().trim()+"\n"+"Time: "+party_time.getText().toString().trim()+"\n"+"Address: "+party_address.getText().toString().trim()+"\n"+"TotalBill: Rs."+totalbill;
                String PartyOrderDetails = "Date: "+party_date.getText().toString().trim()+"\n"+"Time: "+party_time.getText().toString().trim()+"\n"+"Address: "+party_address.getText().toString().trim()+"\n"+"TotalBill: Rs."+totalbill;

                if (totalbill == 0) {
                    //  Toast.makeText(PartyMenu.this, "Please Enter Quantity", Toast.LENGTH_SHORT).show();
                } else {
               //     Toast.makeText(PartyMenu.this, "Bill: " + totalbill, Toast.LENGTH_SHORT).show();
                    totalbillText.setText("Rs. " + totalbill);
                    FoodMenuActivity.Bill = totalbill;
//                    FoodMenuActivity.delAddress = party_address.getText().toString().trim();
                    FoodMenuActivity.delAddress = PartyOrderDetails;
                    FoodMenuActivity.deltype = "HOME DELIVERY";
//                    FoodMenuActivity.deltype = "PARTY ORDER";
                }
                /*finish();
                Intent intent = new Intent(getApplicationContext(), com.skew.swad.OrderActivity.class);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                startActivity(intent);*/

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PartyMenu.this);

              /*  alertDialogBuilder.setTitle("Order Details");
                alertDialogBuilder.setMessage("Do you really want to exit?");*/

                AlertDialog.Builder ad = new AlertDialog.Builder(PartyMenu.this);
                ad.setCancelable(false);
//        ad.setTitle("EXIT CONFIRMATION");

                ad.setMessage(billtext + "\n\n" + "Are you sure you want to Submit Order ?");
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(getApplicationContext(), SubmitOrderActivity.class);
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        startActivity(intent);

                        /*FoodMenuActivity.order.clear();
                        FoodMenuActivity.Bill = (float) 0.0;*/
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        totalbill = 0;
                        dialog.cancel();
                    }
                });
                ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
                if (billtext.trim().isEmpty()||totalbill==0) {
                    totalbillText.setText(""+totalbill);
                    Toast.makeText(PartyMenu.this, "Please add order ", Toast.LENGTH_SHORT).show();
                } else if (party_date.getText().toString().trim().isEmpty() || party_time.getText().toString().trim().isEmpty() || party_address.getText().toString().trim().isEmpty()) {

                    Toast.makeText(PartyMenu.this, "Please complete details PARTY DATE/TIME/ADDRESS", Toast.LENGTH_SHORT).show();
                } else if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
                        connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED) {
                    ad.show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection!!!", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String mo = null,da = null;
            month++;
            if(month < 10)
                mo = "0" + month;
            else
                mo = "" + month;
            if(day < 10)
                da  = "0" + day ;
            else
                da  = "" + day ;

            party_date.setText(da+"-"+mo+"-"+year);

        }
    }
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            party_time.setText(hourOfDay+":"+minute);
        }
    }



    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        finish();
        Intent intent = new Intent(getApplicationContext(), FoodMenuActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        startActivity(intent);

    }

}
