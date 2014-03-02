package edu.sysu.reminder.service;

public class Status {
	public static int times = 3;
	public static int init = 3;
	public static Boolean notice = false;
	public static void set(int i, int j, Boolean isNoticeOn) {
		// TODO Auto-generated method stub
		times = j;
		init = j;
		notice = isNoticeOn;
	}
}
