package edu.sysu.reminder.service;

import edu.sysu.reminder.R;

public class TimesImage {
	private static final int[] widgets = {R.drawable.widget0, R.drawable.widget1, R.drawable.widget2, R.drawable.widget3};

	public int update() {
		if (Status.sta == 3 || Status.sta == 2 || Status.sta == 1)
			Status.sta -= 1;
		else
			Status.sta = Status.init;
		return widgets[Status.sta];
	}


}
