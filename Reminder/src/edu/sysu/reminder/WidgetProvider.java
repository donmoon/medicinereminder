package edu.sysu.reminder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.appwidget.*;
import android.content.*;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.*;

public class WidgetProvider extends AppWidgetProvider {
	private AppWidgetManager appWidgetManager;
	private Context context;
	private static final String CLICK_ACTION = "edu.sysu.reminder.click";
	private static final int[] widgets = {R.drawable.widget3, R.drawable.widget2, R.drawable.widget1, R.drawable.widget0};
	private static int frequency = 0;
	private static final String LOG_TAG = "ImagesWidgetProvider";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(LOG_TAG, "onReceive(): ");
		RemoteViews  views = new RemoteViews(context.getPackageName(), R.layout.widget);
		AppWidgetManager mgr = AppWidgetManager.getInstance(context);
	    if (intent.getAction().equals(CLICK_ACTION)) {
	    	int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
	    	views.setImageViewResource(R.id.Button, widgets[frequency+1]);
	        mgr.updateAppWidget(appWidgetId, views);
	    }
	    super.onReceive(context, intent);
	}
	    
	@Override 
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Perform this loop procedure for each App Widget that belongs to this provider
		Log.d(LOG_TAG, "onUpdate(): ");
        for (int appWidgetId : appWidgetIds) {
	    	RemoteViews  views = new RemoteViews(context.getPackageName(), R.layout.widget);  
	    	
	    	Intent intentClick = new Intent(CLICK_ACTION);
	    	intentClick.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
	        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, intentClick, 0);
	        views.setOnClickPendingIntent(R.id.Button, pendingIntent);
 	        
	        SharedPreferences config = context.getSharedPreferences("widgetconfig", 0);
	        String name = config.getString(String.format("name", appWidgetId), "widget");
	        frequency = config.getInt(String.format("frequency", appWidgetId),0);
	        views.setTextViewText(R.id.widget_name, name);
	        views.setImageViewResource(R.id.Button, widgets[frequency]);
	        appWidgetManager.updateAppWidget(appWidgetId, views); 
		}
    }


    
}