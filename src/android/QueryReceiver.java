package com.flyingsoftgames.googleplayquery;

import com.flyingsoftgames.googleplayquery.GooglePlayQuery;
import org.apache.cordova.PluginResult;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;

import android.util.Log;

public class QueryReceiver extends BroadcastReceiver {

 public static Intent cachedIntent = null;
 
 @Override public void onReceive (Context context, Intent intent) {
  // If the onReceive occurred before the GooglePlayQuery initialize function ran: cache the intent. Otherwise, run as intended.
  if (GooglePlayQuery.cordova == null) {cachedIntent = intent;} else {runCachedOnReceive (intent);}
 }
 
 public static void runCachedOnReceive (Intent intent) {
  if (cachedIntent != null) cachedIntent = null;
  Log.e ("QueryReceiver", intent.toURI());
  if (GooglePlayQuery.queryCallback != null) {
   GooglePlayQuery.queryCallback.sendPluginResult (new PluginResult (PluginResult.Status.OK, intent.toURI()));
  } else {
   GooglePlayQuery.referrer_uri = intent.toURI();
  }
  
  // Now disable the broadcast receiver since we don't need it anymore.
  Activity activity = GooglePlayQuery.cordova.getActivity ();
  ComponentName receiver = new ComponentName (activity, QueryReceiver.class);
  PackageManager pm = activity.getPackageManager ();
  pm.setComponentEnabledSetting (receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
 }
}