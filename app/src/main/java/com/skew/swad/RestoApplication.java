package com.skew.swad;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class RestoApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "VDm4z05SiBNuk0JlhKnBiKfvMkmunBa9Cy2y8tP7", "KpOMiwlyF6CCqXB5qcxpufxbqjLXqOCemKmvMOqx");
//    Application ID = 1MVsHgvgrgYilipBjz73nnlkunGmQg6Xq3sPuMsw
//	  Client ID = vF5YMkM4PM8SU6N6IT5RUfCEf296M1XNaAeO8i1b

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
