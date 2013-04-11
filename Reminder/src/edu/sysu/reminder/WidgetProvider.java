package edu.sysu.reminder;

import android.appwidget.*;
import android.content.*;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews  views = new RemoteViews(context.getPackageName(), R.layout.widget);  
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);  
        }
    }
}