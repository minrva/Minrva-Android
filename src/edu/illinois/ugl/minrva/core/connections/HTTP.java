package edu.illinois.ugl.minrva.core.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import edu.illinois.ugl.minrva.core.Tabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class HTTP {
	
	
	public static void alert(Activity activity, String msg)
	{
    	// prepare the alert box
        AlertDialog.Builder alertbox = new AlertDialog.Builder(activity);

        // set the message to display
        alertbox.setMessage(msg);

        // add a neutral button to the alert box and assign a click listener
        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            // click listener on the alert box
            public void onClick(DialogInterface arg0, int arg1) {
                
                
            }
        });

        // show it
        alertbox.show();
	}
	
	public static void alert(Activity activity)
	{
    	// prepare the alert box
        AlertDialog.Builder alertbox = new AlertDialog.Builder(activity);

        // set the message to display
        alertbox.setMessage("No internet connection is detected.");

        // add a neutral button to the alert box and assign a click listener
        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            // click listener on the alert box
            public void onClick(DialogInterface arg0, int arg1) {
                
                
            }
        });

        // show it
        alertbox.show();
	}
	
	public static boolean isNetworkAvailable() 
	{
		ConnectivityManager cm = (ConnectivityManager) Tabs.context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni =  cm == null?null:cm.getActiveNetworkInfo();
		
		if (ni == null)
			return false;
		if (!ni.isConnected())
			return false;
		if (!ni.isAvailable())
			return false;
		
		return true;
	}

	public static <T> T[] downloadObjects(String uri, Class<T[]> type)
	{
		T[] objects = null;
		
	
		if(HTTP.isNetworkAvailable())
		{
			InputStream source = HTTP.retrieveStream(uri);		

			if(source != null)
			{
				Reader reader = new InputStreamReader(source);
				
				if(reader != null)
				{
					Gson gson = new Gson();
					objects = gson.fromJson(reader, type);
				}
			}
		}
		
		return objects;
	}

	public static <T> T downloadObject(String uri, Class<T> type)
	{
		T object = null;
		
	
		if(HTTP.isNetworkAvailable())
		{
			InputStream source = HTTP.retrieveStream(uri);		

			if(source != null)
			{
				Reader reader = new InputStreamReader(source);
				
				if(reader != null)
				{
					Gson gson = new Gson();
					object = gson.fromJson(reader, type);
				}
			}
		}
		
		return object;
	}
	
	

	

	public static InputStream retrieveStream(String url) 
	{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("Accept", "application/json");    
		
		try 
		{
			HttpResponse getResponse = client.execute(getRequest);
			
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			
			if (statusCode != HttpStatus.SC_OK) 
			{
				Log.w("HTTP", "Error " + statusCode + " for URL " + url);
				return null;
			}
			 
			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();	            
		}
	     
		catch (IOException e) 
		{
			getRequest.abort();
			Log.w("HTTP", "Error for URL " + url, e);
		}
			         
		return null;		         
	}

	
}
