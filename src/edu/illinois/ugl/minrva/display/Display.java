package edu.illinois.ugl.minrva.display;

import android.app.Activity;
import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import edu.illinois.ugl.minrva.R;
import edu.illinois.ugl.minrva.core.Tabs;
import edu.illinois.ugl.minrva.core.cache.ImageLoader;
import edu.illinois.ugl.minrva.core.connections.HTTP;
import edu.illinois.ugl.minrva.core.views.TextViewPlus;

public class Display extends Activity
{
 
	/**
	 * Refreshes data using BibId
	 */
    @Override
    protected void onResume() 
    {
        super.onResume();

        // Refresh views
    	setContentView(R.layout.display);
   	
        // Empty data
        Model item = null;

        // Download data   
        if(!Tabs.id.equalsIgnoreCase(""))
        	if(!HTTP.isNetworkAvailable())	
        	{	HTTP.alert(this); }
        	else
        	{	item = HTTP.downloadObject(getString(R.string.display_service) + Tabs.id, Model.class); }
       
        // Fill text views
        if(item != null)
        {	fillViews(item); }
        else
        {	fillViews(new Model("", "", "", "", new String[0], new String[0], "", new String[0], "", "", "")); }
    }
    
    /**
     * Fill views with data from item.
     * @param item
     */
    public void fillViews(Model item)
    {
    	// Get data
    	String[] locations = item.getLocations();
    	String[] statuses = item.getStatuses();
      	String[] callnums = item.getCallnums();
      	
    	String library = item.getLibrary();
    	String thumbnail = item.getThumbnail();
      	String author = item.getAuthor();
      	String format = item.getFormat();
      	String publisher = item.getPublisher();
      	String pubyear = item.getPubyear();
      	String summary = item.getSummary();
      	String title = item.getTitle();

        // Thumbnail
      	Log.d("Test", thumbnail);
    	ImageView iv = (ImageView) findViewById(R.id.thumbnail);
    	ImageLoader imageLoader = new ImageLoader(this);
    	imageLoader.DisplayImage(thumbnail, iv);
      	
        // Title  
        TextView tv= (TextView) findViewById(R.id.title);
        if(!title.equalsIgnoreCase(""))
        	tv.setText(title);
        else
        	tv.setText("No Title Found");
        
        
        // Author
        tv= (TextView) findViewById(R.id.author);
        if(!author.equalsIgnoreCase(""))
        	tv.setText("by " + author);
        else
        	tv.setVisibility(View.GONE);
        
        // Library
        tv=(TextView) findViewById(R.id.library);
        if(!library.equalsIgnoreCase(""))
        	tv.setText(Html.fromHtml("<b>Library:</b> " + library), TextView.BufferType.SPANNABLE);
        else
        	tv.setVisibility(View.GONE);
        
        
        // Publisher
        tv=(TextView) findViewById(R.id.publisher);
        if(!publisher.equalsIgnoreCase(""))
        	tv.setText(Html.fromHtml("<b>Publisher:</b> " + publisher), TextView.BufferType.SPANNABLE);
        else
        	tv.setVisibility(View.GONE);
        
        // Pubyear
        tv=(TextView) findViewById(R.id.pubyear);        
        if(!pubyear.equalsIgnoreCase(""))
        	tv.setText(Html.fromHtml("<b>Publication Date:</b> " + pubyear), TextView.BufferType.SPANNABLE);
        else
        	tv.setVisibility(View.GONE);
        
        // Format
        tv=(TextView) findViewById(R.id.format);
        if(!format.equalsIgnoreCase(""))
        	tv.setText(Html.fromHtml("<b>Format:</b> " + format), TextView.BufferType.SPANNABLE);
        else
        	tv.setVisibility(View.GONE);
        
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.holdings);
        for(int i = 0, size = locations.length; i < size; i++)
        {
        	
        	// Divider
        	  if(		!locations[i].equalsIgnoreCase("") ||
        			  	!statuses[i].equalsIgnoreCase("") ||
        			  	!callnums[i].equalsIgnoreCase("")
        	  )
        	  {
        		  View view = new View(this);
        		  
        		  LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, Math.round(convertToPx(TypedValue.COMPLEX_UNIT_DIP, 1)));
                  layoutParams.setMargins(0, 0, 0, Math.round(convertToPx(TypedValue.COMPLEX_UNIT_DIP, 5)));
                  view.setLayoutParams(layoutParams);
                  view.setBackgroundColor(getResources().getColor(R.color.black));
                  
              	linearLayout.addView(view);
        	  }
        	
       
        
            // Location
            if(!locations[i].equalsIgnoreCase(""))
            {
                tv = new TextViewPlus(this); 
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, Math.round(convertToPx(TypedValue.COMPLEX_UNIT_DIP, 5)));
                tv.setLayoutParams(layoutParams);            
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.small_font_size));
           	tv.setText(Html.fromHtml("<b>Location:</b> " + locations[i]), TextView.BufferType.SPANNABLE);
            	linearLayout.addView(tv);
            }

            // Status
            if(!statuses[i].equalsIgnoreCase(""))
            {
                tv = new TextViewPlus(this); 
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, Math.round(convertToPx(TypedValue.COMPLEX_UNIT_DIP, 5)));
                tv.setLayoutParams(layoutParams);            
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.small_font_size));
            	tv.setText(Html.fromHtml("<b>Status:</b> " + statuses[i]), TextView.BufferType.SPANNABLE);
            	linearLayout.addView(tv);
            }

            // Call numbers
            if(!callnums[i].equalsIgnoreCase(""))
            {
                tv = new TextViewPlus(this); 
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, Math.round(convertToPx(TypedValue.COMPLEX_UNIT_DIP, 5)));
                tv.setLayoutParams(layoutParams);            
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.small_font_size));
            	tv.setText(Html.fromHtml("<b>Call No.:</b> " + callnums[i]), TextView.BufferType.SPANNABLE);
            	linearLayout.addView(tv);
            }
        }

        // Summary
        tv=(TextView) findViewById(R.id.summary);
        if(!summary.equalsIgnoreCase(""))
        {
        	tv.setText(summary);
        }
        else
        {
        	tv.setVisibility(View.GONE);
        
        	tv=(TextView) findViewById(R.id.descr);
        	tv.setVisibility(View.GONE);
        }
    }
    
    public float convertToPx(int unit, int value)
    {
    	Resources r = getResources();
    	float px = TypedValue.applyDimension(unit, value, r.getDisplayMetrics());
    	
    	return px;
    }
}
