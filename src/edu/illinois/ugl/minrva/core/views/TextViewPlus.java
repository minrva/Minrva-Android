package edu.illinois.ugl.minrva.core.views;

import java.util.Hashtable;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TextViewPlus extends TextView {
    private static final String TAG = "TextViewPlus";

	private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();
	
    public TextViewPlus(Context context) {
        super(context);
    }

    public TextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setTypeface(Typeface tf, int style) {
    	
    	// Roboto
    	/*
    	Typeface Black = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-Black.ttf");
    	Typeface BlackItalic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-BlackItalic.ttf");
    	Typeface BoldCondensed = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-BoldCondensed.ttf");
    	Typeface BoldCondensedItalic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-BoldCondensedItalic.ttf");
    	Typeface BoldItalic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-BoldItalic.ttf");
    	Typeface Condensed = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-Condensed.ttf");
    	Typeface CondensedItalic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-CondensedItalic.ttf");
    	Typeface Italic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-Italic.ttf");
    	Typeface Light = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-Light.ttf");
    	Typeface LightItalic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-LightItalic.ttf");
    	Typeface Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-Medium.ttf");
    	Typeface MediumItalic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-MediumItalic.ttf");
    	Typeface Regular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-Regular.ttf");
    	Typeface Thin = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-Thin.ttf");
    	Typeface ThinItalic = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto/Roboto-ThinItalic.ttf");
    	*/
     
    	String path = "";
    	switch(style)
    	{
    		case Typeface.BOLD: 
    			path = "fonts/Roboto/Roboto-Bold.ttf";
    			break;
    		case Typeface.BOLD_ITALIC: 
    			path = "fonts/Roboto/Roboto-BoldItalic.ttf";
    			break;
    		case Typeface.ITALIC: 
    			path = "fonts/Roboto/Roboto-Italic.ttf";
    			break;
    		case Typeface.NORMAL: 
    			path = "fonts/Roboto/Roboto-Regular.ttf";
    			break;    	
    	}
    	
    	
    	synchronized (cache) 
    	{
    		if (!cache.containsKey(path)) 
    		{
    			try 
 				{
    				tf = Typeface.createFromAsset(getContext().getAssets(), path);  
    				cache.put(path, tf);
 				} 
 				catch (Exception e) 
 				{ 
 					Log.e(TAG, "Could not get typeface: "+e.getMessage()); 
 				}
 			}
 			
    		super.setTypeface(cache.get(path));
    	}
    }
}