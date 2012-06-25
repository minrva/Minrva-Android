package edu.illinois.ugl.minrva.wayfinder;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;

import edu.illinois.ugl.minrva.R;
import edu.illinois.ugl.minrva.core.Tabs;
import edu.illinois.ugl.minrva.core.connections.HTTP;
import edu.illinois.ugl.minrva.core.views.TouchImageView;
import edu.illinois.ugl.minrva.display.Model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Wayfinder extends Activity
{

	@Override
	public void onResume() 
	{
		super.onResume();
	
		
		setContentView(R.layout.wayfinder);
			
		// Get resource URI's
			String bmp_uri = getString(R.string.jpg_service) + "Wayfinder/please.jpg";
			String shelves_uri = getString(R.string.wayfinder_service) + Tabs.id;
        
			 // Download data   
			Shelf[] shelves = null;
			Bitmap bm = null;
	        if(!Tabs.id.equalsIgnoreCase(""))
	        	if(!HTTP.isNetworkAvailable())	
	        	{	HTTP.alert(this); }
	        	else
	        	{
	        		bm = downloadBmp(bmp_uri);
	        		shelves = HTTP.downloadObjects(shelves_uri, Shelf[].class); 
	        	}
	   
	        // Fill text views
	        if(shelves != null && shelves.length > 0 && bm != null)
	        {	
	        	Shelf shelf = shelves[0];
	        	String title = shelf.getTitle();
	        	String shelf_id = shelf.getShelf_id();
	        	String call_num = shelf.getCall_num();
	        			
	        	// Title
	            TextView tv = (TextView) findViewById(R.id.title);        
	            if(!title.equalsIgnoreCase(""))
	            	tv.setText(Html.fromHtml("<b>Title:</b> " + title), TextView.BufferType.SPANNABLE);
	            else
	            	tv.setVisibility(View.GONE);
	 
	           	// Shelf Id
	            tv = (TextView) findViewById(R.id.shelf_id);        
	            if(!title.equalsIgnoreCase(""))
	            	tv.setText(Html.fromHtml("<b>Shelf No.:</b> " + shelf_id), TextView.BufferType.SPANNABLE);
	            else
	            	tv.setVisibility(View.GONE);

	           	// Call number
	            tv =(TextView) findViewById(R.id.call_num);        
	            if(!title.equalsIgnoreCase(""))
	            	tv.setText(Html.fromHtml("<b>Call No.:</b> " + call_num), TextView.BufferType.SPANNABLE);
	            else
	            	tv.setVisibility(View.GONE);

	            // Draw shelf locations on bitmap
	        	bm = drawLocations(bm, shelves);
		       			
	            // Map
	            TouchImageView temp = (TouchImageView) findViewById(R.id.imageview);
	            temp.setImage(bm, 500, 500);
	        }
	        else
	        {
	        	LinearLayout ll =(LinearLayout) findViewById(R.id.bubble);  
	        	ll.setVisibility(View.GONE);
	        	HTTP.alert(this, "Sorry, map unavailable.");
	        }
	}

	
	
	
	public  Bitmap downloadBmp(String uri) 
	{
		 Bitmap bitmap = null;

		// ImageLoader.
		// Setup HTTP request
		 HttpClient client = new DefaultHttpClient();
		 HttpGet httpGet = new HttpGet(uri);
			
			try 
			{
				HttpResponse response = (HttpResponse) client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
			
				
			
				
				
				if (statusCode == 200) 
				{			
					HttpEntity entity = response.getEntity();
					  
					InputStream istr = entity.getContent();
					bitmap = BitmapFactory.decodeStream(istr);
				} 
			} 
			catch (IOException e) { e.printStackTrace(); }
	            
        return bitmap.copy(Bitmap.Config.ARGB_8888, true);
	 }

	
	public class PatchInputStream extends FilterInputStream { public PatchInputStream(InputStream input) { super(input); } public long skip(long n) throws IOException { long m = 0L; while (m < n) { long _m = in.skip(n-m); if (_m == 0L) break; m += _m; } return m; } }
	
	public Bitmap drawLocations(Bitmap bm, Shelf[] shelves)
	{
		
		// Get canvas
		Canvas cv = new Canvas(bm);
		
		// Get paint
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setARGB(255, 255, 0, 0);
		
		// Paint dots
		for(Shelf shelf: shelves)
		{
			Log.d("coords: ", "(" + shelf.getX() + ", " + shelf.getY() + ")" );
			cv.drawCircle(Integer.parseInt(shelf.getX()), Integer.parseInt(shelf.getY()), 5, paint);
		}
		
		return bm;
	}
}
