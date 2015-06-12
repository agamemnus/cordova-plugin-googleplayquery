package com.flyingsoftgames.googleplayquery;

import com.flyingsoftgames.googleplayquery.QueryReceiver;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import android.content.Intent;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;

import org.json.JSONArray;
import org.json.JSONException;

public class GooglePlayQuery extends CordovaPlugin {
 public static CallbackContext queryCallback = null;
 public static CordovaInterface cordova = null;
 public static String referrer_uri = "";
 
 public static Intent QueryReceiverCachedIntent = null;
 
 @Override public void initialize (CordovaInterface initCordova, CordovaWebView webView) {
  // Create a static cordova reference so that QueryReceiver can access it.
  cordova = initCordova;
  
  // Enable the broadcast receiver in case it isn't enabled.
  Activity activity = cordova.getActivity ();
  ComponentName receiver = new ComponentName (activity, QueryReceiver.class);
  PackageManager pm = activity.getPackageManager ();
  pm.setComponentEnabledSetting (receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
  
  // If the QueryReceiver's onReceive already ran, run the cached data.
  if (QueryReceiver.cachedIntent != null) {QueryReceiver.runCachedOnReceive (QueryReceiver.cachedIntent);}
  
  super.initialize (cordova, webView);
 }
 
 public boolean execute (String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
  if ("getURI".equals(action)) {
   if (referrer_uri != "") {
    callbackContext.sendPluginResult (new PluginResult (PluginResult.Status.OK, referrer_uri));
    referrer_uri = "";
    return true;
   }
   this.queryCallback = callbackContext;
  }
  return true;
 }
}