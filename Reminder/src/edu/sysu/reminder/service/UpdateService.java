package edu.sysu.reminder.service;

import edu.sysu.reminder.R;
import edu.sysu.reminder.WidgetConfigure;
import edu.sysu.reminder.WidgetProvider;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateService extends Service
{
	
    @Override
    public void onStart(Intent intent, int startId) 
    {
        super.onStart(intent, startId);
        updateWidget(this);
    }

    private void updateWidget(Context context) 
    {    
        Time time = new Time();
        time.setToNow();
        int hour = time.hour;
        int min = time.minute;

        RemoteViews updateView = new RemoteViews(context.getPackageName(), R.layout.widget);
        
        if (hour == 0 && min == 0)                                          //update the widget to initial when a new day comes
    	{
    		TimesImage ti = new TimesImage();
    		updateView.setImageViewResource(R.id.Button, ti.setToInit());
    	}     

        AppWidgetManager awg = AppWidgetManager.getInstance(context);
        awg.updateAppWidget(new ComponentName(context, WidgetProvider.class), updateView);
        
        Log.v("notice", Boolean.toString(Status.notice));
        Log.v("times", Integer.toString(Status.init));
        
        if (Status.notice && (hour == 9 || hour == 13 || hour == 15) && min == 8)    //notice in the status bar after three meals
        {
        	NotificationManager notificationManager = (NotificationManager)this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);  
            Notification notification =new Notification(R.drawable.ic_launcher,  "notification", System.currentTimeMillis());
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            Intent intent = new Intent(this, WidgetConfigure.class);
            PendingIntent pi= PendingIntent.getActivity(this, 0, intent, 0);
            notification.setLatestEventInfo(this, "reminder", "time to have medicine", pi);
            notificationManager.notify(0, notification);
        }
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}