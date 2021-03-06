package com.eleonorvinicius.androidexamples.um;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;

import com.eleonorvinicius.androidexamples.iAES;
import com.eleonorvinicius.framentsexample.R;

public class MainActivity extends Activity implements iAES {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void change(MenuItem menuItem) {
		cancelImpl();
		LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOCAL_CHANGE_COLOR));
	}

	public void scheduled(MenuItem menuItem) {
		cancelImpl();
		Intent intent = new Intent(CHANGE_COLOR);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 5);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}

	public void repet(MenuItem menuItem) {
		cancelImpl();
		Intent intent = new Intent(CHANGE_COLOR);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 5);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5 * 1000, pendingIntent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		cancelImpl();
	}

	private void cancelImpl() {
		Intent intent = new Intent(CHANGE_COLOR);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
	}
}