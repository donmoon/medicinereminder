package edu.sysu.reminder.service;

import edu.sysu.reminder.R;

public class TimesImage {
	private static final int[] widgets = {R.drawable.widget0, R.drawable.widget1, R.drawable.widget2, R.drawable.widget3};

	public int update() {          //times decrease by 1
		if (Status.times > 0)
			Status.times--;
		else
			Status.times = Status.init;
		return widgets[Status.times];
	}


	public int setToInit() {    //set times to initial
		Status.times = Status.init;
		return widgets[Status.times];
	}
}
