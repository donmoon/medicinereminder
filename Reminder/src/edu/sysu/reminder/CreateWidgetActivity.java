package edu.sysu.reminder;

import android.app.Activity;
import android.os.Bundle;



public class CreateWidgetActivity extends Activity {
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.main);
     }


	 @Override
	 public void onDestroy() {
	     super.onDestroy();  // Always call the superclass
	     
	     // Stop method tracing that the activity started during onCreate()
	     android.os.Debug.stopMethodTracing();
	 }
}
