package edu.illinois.ugl.minrva.vufind;

import java.net.URLEncoder;
import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import edu.illinois.ugl.minrva.R;
import edu.illinois.ugl.minrva.R.array;
import edu.illinois.ugl.minrva.core.Tabs;
import edu.illinois.ugl.minrva.core.adapter.ItemView;
import edu.illinois.ugl.minrva.core.adapter.ListItem;
import edu.illinois.ugl.minrva.core.adapter.MinrvaAdapter;
import edu.illinois.ugl.minrva.core.connections.HTTP;

public class VuFind extends ListActivity implements OnEditorActionListener, OnScrollListener, OnCheckedChangeListener, OnItemSelectedListener 
{
  	// ListActivity
  	private ListActivity activity = this;
	private AutoCompleteTextView edittext;
	private Spinner filter;
	private RadioGroup rgroup;
  	private MinrvaAdapter ma;	
  	private ArrayList<ListItem> lis;   	

	// State
	private static String lookfor = "";
	private static String type = "all";
	private static String domain = "uiu";
  	private static int page = 0;	
  	private static boolean loadingMore = false;	
	
  	// Array values
  	private static String[] filter_values;
  	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {    
        // Initialize
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.vufind);     
                	
        // Bind list data
        lis =  new ArrayList<ListItem>();
        ma = new MinrvaAdapter(this, lis);
        setListAdapter(ma); 

        // Add event listener to ListView
        getListView().setOnScrollListener(this);
        
        // Add event listener to EditText
        edittext = (AutoCompleteTextView) findViewById(R.id.edittext);
    	edittext.setOnEditorActionListener(this);   
    	
    	 // Add event listener to RadioGroup
        filter = (Spinner) findViewById(R.id.filter);
        filter.setOnItemSelectedListener(this);   
        
    	 // Add event listener to RadioGroup
        rgroup = (RadioGroup) findViewById(R.id.radio_group);
        rgroup.setOnCheckedChangeListener(this);   
        
        // Get rgroups corresponding values
        filter_values = getResources().getStringArray(R.array.vufind_filter_values);
    }
      
   
    public ArrayList<ListItem> getBooks(Book[] books)
    {
    	ArrayList<ListItem> additional = new ArrayList<ListItem>();   	
    	 
    	// Parse each book into list item
        for(int i = 0, size = books.length; i < size; i++)	
		{   
			// Get raw data
			String title = books[i].getTitle().toUpperCase();
			String thumbnail = books[i].getThumbnail(); 		
			String author = books[i].getAuthor();
    		String pubYear = books[i].getPubYear();
    		String location = books[i].getLocation();
    		String format = books[i].getFormat();
		
    		// Format raw data
    		String info = "";
    		if(!location.equalsIgnoreCase(""))
    			info +="Location: " + location + "\n";
    		if(!author.equalsIgnoreCase(""))
    			info +="Author: " + author + "\n";
    		if(!pubYear.equalsIgnoreCase(""))
    			info += "Pub. Date: " + pubYear + "\n";
    		if(!format.equalsIgnoreCase(""))
    			info += "Format: " + format + "\n";

			// Create views
    		ItemView titleView = new ItemView(title, R.id.text);
    		ItemView imageView = new ItemView(thumbnail, R.id.image);
    		ItemView infoView = new ItemView(info, R.id.text);
    		
    		// Create list items
    		ListItem titleItem = new ListItem(null, R.layout.c_text_header, false);
    		ListItem infoItem = new ListItem(books[i], R.layout.core_item_text_image, true);
    		
    		// Add views to list items
    		titleItem.add(titleView);
    		infoItem.add(infoView);
    		infoItem.add(imageView);
    		
    		// Add list items to list
    		additional.add(titleItem);
    		additional.add(infoItem);
     	}
        
        return additional;
    }
    
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) 
    {	
    	if(actionId==EditorInfo.IME_ACTION_DONE)
    	{
    		// Close the soft keyboard
    		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    		imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
    		
    		// Clear data
			while(loadingMore){}
    		lis.clear();
    		ma.notifyDataSetChanged();
		    					
    		// Get lookfor
    		String strTopic = edittext.getText().toString();
    		try { lookfor = URLEncoder.encode(strTopic.toString(), "utf-8"); }
			catch(Exception e) { lookfor = ""; Log.d("URL", e.toString()); }
  	
			// Get page
			page = 0;
			
			// Add data
			addMore();
        }
    	
    	return false;
	}
    
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) 
    {
		int lastInScreen = firstVisibleItem + visibleItemCount;				
		
		if((lastInScreen >= totalItemCount - 60) && lis.size() != 0)	
			addMore();
    }
     
    public void onScrollStateChanged(AbsListView view, int scrollState) {}
 
    /**
     * Calls asynch task to download more books
     */
    public void addMore()
    {
    	if(!loadingMore && !lookfor.equalsIgnoreCase(""))
    		new DownloadResults().execute();    		
    }
    
    public void onCheckedChanged(RadioGroup group, int checkedId) 
    {    	    	
		// Clear data
		while(loadingMore){}
		lis.clear();
		ma.notifyDataSetChanged();
	    						
    	switch(checkedId) 
    	{
    		case R.id.ishare_button:
    			domain = "all";
    			break;
         	case R.id.ugl_button:
         		domain = "uiu";
         		break;
            default:
            	domain = "all";
            	break;
    	}
    	
    	// Get page
		page = 0;
		
		// Add data
		addMore();
	}
    
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) 
    {
    	if(parent.getId() == R.id.filter)
    	{
    		// Clear data
    		while(loadingMore){}
    		lis.clear();
    		ma.notifyDataSetChanged();
    		
    		type = filter_values[pos];
    		
    		// Get page
    		page = 0;
    		
    		// Add data
    		addMore();
    	}
    	
    }
    

    
        
	/** Display which item was clicked */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Book book = (Book) getListView().getAdapter().getItem(position);
    	//Toast.makeText(this, book.getTitle() + " selected", Toast.LENGTH_LONG).show();
    	Tabs.id = book.getBibId();
    	
    	Tabs tabs = (Tabs) this.getParent();
    	tabs.switchTab(0);
    }	
    
   
    private class DownloadResults extends AsyncTask<Void, Void, ArrayList<ListItem>> 
    {    
		protected ArrayList<ListItem> doInBackground(Void... v) 
    	{
			// Lock the gate
    		loadingMore = true;			
    		
    		
    		ArrayList<ListItem> additional = new ArrayList<ListItem>(); 
    		
    		
    		for(int i = 0; i < 2; i++)
    		{
    			// Empty data
    			Book[] books = null;

    			 // Download data   
    			if(!lookfor.equalsIgnoreCase(""))
    				if(!HTTP.isNetworkAvailable()) 
    				{ return null; }
    		    	else
    		    	{ books = HTTP.downloadObjects(getString(R.string.vufind_service) + "?lookfor=" + lookfor + "&page=" + ++page + "&domain=" + domain + "&type=" + type, Book[].class); }   	        

    			 // Fill text views
    	        if(books != null && books.length > 0)
    	        {	
    	        	ArrayList<ListItem> tmp = getBooks(books);   
    	        	additional.addAll(tmp);
    	        }
    		}
    		
    		return additional;   		
        }

        protected void onPostExecute(ArrayList<ListItem> additional) 
        {
        	// Alert network availability changes
        	if(additional == null)
        	{	HTTP.alert(activity); } 	
        	else if( additional.size() == 0 && (activity.getListView().getLastVisiblePosition() == lis.size() - 1))
        	{	HTTP.alert(activity, "No more results found."); }
        	else
        	{
        		lis.addAll(additional);
        		ma.notifyDataSetChanged();

        		if((activity.getListView().getLastVisiblePosition() == lis.size() - 1))
            		HTTP.alert(activity, "No more results found.");
        	}

        	// Unlock the gate
        	loadingMore = false;
        }
    }


	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
