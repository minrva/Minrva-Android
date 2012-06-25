package edu.illinois.ugl.minrva.core.adapter;

import java.util.ArrayList;

import edu.illinois.ugl.minrva.core.cache.ImageLoader;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MinrvaAdapter extends BaseAdapter {
    
    private Activity activity;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    private ArrayList<ListItem> data;
   
    public MinrvaAdapter(Activity a, ArrayList<ListItem> d) {
    	activity = a;
    	data = d;
    	inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity);
    }
    
    public int getCount() {
        return data.size();
    }
    
    public Object getItem(int position) {
        return data.get(position).data;
    }

    public long getItemId(int position) {
        return position;
    }
    
    
    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
    	return data.get(position).enabled;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	// Get list item data
    	ListItem li = data.get(position);
    	
    	// Inflate list item XML if need be
    	View v = convertView;      
        if (convertView == null || convertView.getId() != li.id) 
        	v = inflater.inflate(li.id, null);
        
        // Change values in views in list item
        for(ItemView view:li)
        {
        	View item = v.findViewById(view.getId());
        	String value = view.getValue();
        		
        	if(item instanceof ImageView)
        	{
        		ImageView iv = (ImageView) item;
        		imageLoader.DisplayImage(value, iv);
        	}
        	else if(item instanceof TextView)
        	{
        		TextView tv = (TextView) item;
        		tv.setText(value);
        	}
        }
        
        return v;
    }
}

	