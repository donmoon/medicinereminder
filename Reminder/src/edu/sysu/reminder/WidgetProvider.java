package edu.sysu.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.*;
import android.content.*;
import android.text.format.Time;
import android.util.Log;
import android.widget.*;
import edu.sysu.reminder.service.*;

public class WidgetProvider extends AppWidgetProvider {
	private static final String CLICK_ACTION = "edu.sysu.reminder.click";
	private static final String LOG_TAG = "WidgetProvider";
	private TimesImage ti = new TimesImage();

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(LOG_TAG, "onReceive(): ");
		RemoteViews  views = new RemoteViews(context.getPackageName(), R.layout.widget);
		AppWidgetManager mgr = AppWidgetManager.getInstance(context);
	    if (intent.getAction().equals(CLICK_ACTION)) {            //update the widget when receive a broadcast 
	    	int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
	    	views.setImageViewResource(R.id.Button, ti.update());
	        mgr.updateAppWidget(appWidgetId, views);
	    }
	    super.onReceive(context, intent);
	}
	    
	@Override 
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Perform this loop procedure for each App Widget that belongs to this provider
		Log.d(LOG_TAG, "onUpdate(): ");
        for (int appWidgetId : appWidgetIds) {
	    	Time time = new Time();
	    	time.setToNow();

	        Intent intent = new Intent(context, UpdateService.class);   //start a service to check the time 
	        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

	        AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);  //start an alarm to call service per minute
	        alarm.setRepeating(AlarmManager.RTC, time.toMillis(true), 60*1000, pendingIntent);
		}
    }


    
}