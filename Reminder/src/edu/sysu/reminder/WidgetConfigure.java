package edu.sysu.reminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.sysu.reminder.service.*;



public class WidgetConfigure extends Activity {
	private EditText nameText;
	private Button okButton;
	private Button cancelButton;
	private Spinner freqSpinner;
	private ToggleButton noticeToggle;
	private Boolean isNoticeOn = false;
	private int freq = 2;
	private static final String CLICK_ACTION = "edu.sysu.reminder.click";
	private static final int[] widgets = {R.drawable.widget0, R.drawable.widget1, R.drawable.widget2, R.drawable.widget3};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.main);
	    
	    nameText=(EditText) findViewById(R.id.name);

	    freqSpinner=(Spinner)findViewById(R.id.frequency); 
	    freqSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				freq=arg2;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    noticeToggle = (ToggleButton)findViewById(R.id.notice_switch);
	    noticeToggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					isNoticeOn = true;
				else
					isNoticeOn = false;
			}
	    	
	    });
	    
	    cancelButton=(Button) findViewById(R.id.cancel_button);
	    cancelButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				setResult(RESULT_CANCELED);
				finish();
				
			}
	    	
	    });
	    
	    okButton=(Button) findViewById(R.id.ok_button);
	    okButton.setOnClickListener(new Button.OnClickListener(){
	    	public void onClick(View v)
	    	{               
	    		setInflateFinishResult(); 
	    	}
	    });        
    }
    
	private void setInflateFinishResult(){
		 
		//First, get the App Widget ID from the Intent that launched the Activity:
	    Intent intent = getIntent();
	    Bundle extras = intent.getExtras();
	    int mAppWidgetId = 0;
	    if (extras != null) {                     
	        mAppWidgetId = extras.getInt(
	        		AppWidgetManager.EXTRA_APPWIDGET_ID, 
	                AppWidgetManager.INVALID_APPWIDGET_ID);
	    }
	    // If they gave us an intent without the widget id, just bail.
	    if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
	    	finish();
	    }
	    //Perform your App Widget configuration.

		
//	     final SharedPreferences config = getSharedPreferences("widgetconfig", 0);
//	     SharedPreferences.Editor configEditor = config.edit();
//		 configEditor.putInt(String.format("frequency", mAppWidgetId), freq);
//	 	 configEditor.putString(String.format("name", mAppWidgetId), nameText.getText().toString());
//		 configEditor.commit();

	     
	    //When the configuration is complete, get an instance of the AppWidgetManager by calling getInstance(Context):
		final Context context = WidgetConfigure.this;
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

	    // Update the App Widget with a RemoteViews layout by calling updateAppWidget(int, RemoteViews):
		RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);

    	Intent intentClick = new Intent(CLICK_ACTION);
    	intentClick.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, mAppWidgetId, intentClick, 0);
        views.setOnClickPendingIntent(R.id.Button, pendingIntent);
        
//        SharedPreferences config = context.getSharedPreferences("widgetconfig", 0);
//        String name = config.getString(String.format("name", appWidgetId), "widget");
//        frequency = config.getInt(String.format("frequency", appWidgetId),0);

        views.setTextViewText(R.id.widget_name, nameText.getText().toString());
        views.setImageViewResource(R.id.Button, widgets[freq+1]);
		appWidgetManager.updateAppWidget(mAppWidgetId, views);
	    
		Status.set(freq+1, freq+1, isNoticeOn);
		
	    //Finally, create the return Intent, set it with the Activity result, and finish the Activity:
	    Intent resultValue = new Intent();
	    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
	    setResult(RESULT_OK, resultValue);
	    finish();

	 }

}
