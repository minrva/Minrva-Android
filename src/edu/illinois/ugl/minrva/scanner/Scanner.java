package edu.illinois.ugl.minrva.scanner;

import edu.illinois.ugl.minrva.R;
import edu.illinois.ugl.minrva.core.Tabs;
import edu.illinois.ugl.minrva.core.connections.HTTP;
import edu.illinois.ugl.minrva.scanner.zxing.IntentIntegrator;
import edu.illinois.ugl.minrva.scanner.zxing.IntentResult;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Scanner extends Activity 
{	
	
	private Activity thisActivity = this;
	
    /** Launch Barcode Scanner */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
    	setContentView(R.layout.scanner);


    	final Button button = (Button) findViewById(R.id.begin_scan);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(thisActivity);
                integrator.initiateScan();
            }
        });
    }
    
    /**
     *  Retrieve results from web service and  store in static Marc objects. 
     *  */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) 
    {
    	IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	    	
    	if (scanResult != null) 
    	{	
    		try
    		{
    			Log.d("test",  getString(R.string.scanner_service) + scanResult.getContents());
    			Model[] bibid = null;
    			bibid = HTTP.downloadObjects(getString(R.string.scanner_service) + scanResult.getContents(), Model[].class); 
    			Tabs.id = bibid[0].getBib() != null?bibid[0].getBib():"";
    		}
    		catch(Exception e)
    		{
    			Tabs.id = "";
    		}
        }
    	
    	Tabs tabs = (Tabs) this.getParent();
    	tabs.switchTab(0);
    }
    
   
}