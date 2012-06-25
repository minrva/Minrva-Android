package edu.illinois.ugl.minrva.core;





import edu.illinois.ugl.minrva.display.Display;
import edu.illinois.ugl.minrva.loanabletech.LoanableTech;
import edu.illinois.ugl.minrva.scanner.Scanner;
import edu.illinois.ugl.minrva.vufind.VuFind;
import edu.illinois.ugl.minrva.wayfinder.Wayfinder;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class Tabs extends TabActivity {
   
	public static String id = "";
	public static Context context;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(edu.illinois.ugl.minrva.R.layout.core);
Log.d("Test", "test");
	    Resources res = getResources(); 	// Resource object to get Drawables
	    TabHost tabHost = getTabHost();  	// The activity TabHost
	    TabHost.TabSpec spec;  				// Resusable TabSpec for each tab
	    Intent intent;  					// Reusable Intent for each tab
	    context = this.getApplicationContext();
	    
	    // Home
	    intent = new Intent().setClass(this, Display.class); //.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    spec = tabHost.newTabSpec("home").setIndicator("Home", res.getDrawable(edu.illinois.ugl.minrva.R.drawable.ic_tab_home)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    // VuFind Search
	    intent = new Intent().setClass(this, VuFind.class); //.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    spec = tabHost.newTabSpec("search").setIndicator("Search", res.getDrawable(edu.illinois.ugl.minrva.R.drawable.ic_tab_search)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    // Barcode Scanner
	    intent = new Intent().setClass(this, Scanner.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    spec = tabHost.newTabSpec("scanner").setIndicator("Scan", res.getDrawable(edu.illinois.ugl.minrva.R.drawable.ic_tab_scan)).setContent(intent);
	    tabHost.addTab(spec);

	    // Wayfinder Tour
	    intent = new Intent().setClass(this, Wayfinder.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    spec = tabHost.newTabSpec("locator").setIndicator("Locate", res.getDrawable(edu.illinois.ugl.minrva.R.drawable.ic_tab_map)).setContent(intent);
	    tabHost.addTab(spec);

	    // VuFind Favorites
	    intent = new Intent().setClass(this, LoanableTech.class);//.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    spec = tabHost.newTabSpec("favorites").setIndicator("Tech", res.getDrawable(edu.illinois.ugl.minrva.R.drawable.ic_tab_tech)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
	}
	
	public void switchTab(int tab){
		getTabHost().setCurrentTab(tab);
	}

	
	
}
