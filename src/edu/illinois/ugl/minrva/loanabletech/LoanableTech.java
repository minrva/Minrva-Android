package edu.illinois.ugl.minrva.loanabletech;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import com.google.gson.Gson;

import edu.illinois.ugl.minrva.R;
import edu.illinois.ugl.minrva.core.Tabs;
import edu.illinois.ugl.minrva.core.adapter.ItemView;
import edu.illinois.ugl.minrva.core.adapter.MinrvaAdapter;
import edu.illinois.ugl.minrva.core.connections.HTTP;
import edu.illinois.ugl.minrva.core.adapter.ListItem;
import edu.illinois.ugl.minrva.vufind.Book;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class LoanableTech extends ListActivity 
{
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        // Initialize
		super.onCreate(savedInstanceState);
        setContentView(edu.illinois.ugl.minrva.R.layout.loanabletech);
        	
        // Download data
        String techloans_uri = this.getString(R.string.loanabletech_service);
        TechLoan[] techLoans = downloadTechLoans(techloans_uri);
       
        // Add data to MinrvaAdapter
        MinrvaAdapter ma = bindData(techLoans);  
        
        // Bind adapter to list
        setListAdapter(ma);
    }
	
	public MinrvaAdapter bindData(TechLoan[] techLoans)
	{
		 ArrayList<ListItem> lis = new ArrayList<ListItem>();  
		 ArrayList<Boolean> enabled = new ArrayList<Boolean>(); 
		 for(int i = 0, size = techLoans.length; i < size; i++)
		 {        
			 // Add header
			 ListItem li1 = new ListItem(null, R.layout.c_text_item, false);
			 li1.add(new ItemView(techLoans[i].getName().toUpperCase(),R.id.text));
			 lis.add(li1);
			 
			 // Add divider
			 ListItem li2 = new ListItem(null, R.layout.core_divider_light_blue, false);
			 li2.add(new ItemView("",R.id.light_blue));
			 lis.add(li2);
	        	
			 // Add text and image
			 ListItem li3 = new ListItem(techLoans[i], R.layout.core_item_text_image, true);
			 li3.add(new ItemView(techLoans[i].getCount() + " available", R.id.text));
			 li3.add(new ItemView(getString(R.string.jpg_service) + "TechLoan/" + techLoans[i].getThumbnail(), R.id.image));
			 
			 lis.add(li3);
		 }
	        
		 return new MinrvaAdapter(this, lis);
	}
	
    public TechLoan[] downloadTechLoans(String uri)
	{
		InputStream source = HTTP.retrieveStream(uri);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    TechLoan[] techloans = gson.fromJson(reader, TechLoan[].class);
		
		return techloans;
	}
    
    /** Display which item was clicked */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	TechLoan techloan = (TechLoan) getListView().getAdapter().getItem(position);
    	//Toast.makeText(this, book.getTitle() + " selected", Toast.LENGTH_LONG).show();
    	Tabs.id = techloan.getBibId();
    	
    	Tabs tabs = (Tabs) this.getParent();
    	tabs.switchTab(0);
    }	
}
