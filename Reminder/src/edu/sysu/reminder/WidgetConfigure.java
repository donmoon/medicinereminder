package edu.sysu.reminder;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;



public class WidgetConfigure extends Activity {
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.main);
	     
	     //First, get the App Widget ID from the Intent that launched the Activity:
	     Intent intent = getIntent();
	     Bundle extras = intent.getExtras();
	     if (extras != null) {
	         mAppWidgetId = extras.getInt(
	                 AppWidgetManager.EXTRA_APPWIDGET_ID, 
	                 AppWidgetManager.INVALID_APPWIDGET_ID);
	     }
	     
	     //Perform your App Widget configuration.
	     
	     
	     //When the configuration is complete, get an instance of the AppWidgetManager by calling getInstance(Context):
	     final Context context = WidgetConfigure.this;
	     AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
	     
	    // Update the App Widget with a RemoteViews layout by calling updateAppWidget(int, RemoteViews):
	     RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);
	    appWidgetManager.updateAppWidget(mAppWidgetId, views);
	    		 
	    //Finally, create the return Intent, set it with the Activity result, and finish the Activity:
	    Intent resultValue = new Intent();
	    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
	    setResult(RESULT_OK, resultValue);
	    finish();
     }


	 @Override
	 public void onDestroy() {
	     super.onDestroy();  // Always call the superclass
	     
	     // Stop method tracing that the activity started during onCreate()
	     android.os.Debug.stopMethodTracing();
	 }
}
