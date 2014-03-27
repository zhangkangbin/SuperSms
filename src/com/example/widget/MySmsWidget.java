package com.example.widget;






import com.example.sms_app.R;
import com.example.sms_app.SmsShow;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class MySmsWidget extends AppWidgetProvider{

	private static RemoteViews rv;
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub

		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}


	}


	public static void updateAppWidget(Context context,
			AppWidgetManager appWidgeManger, int appWidgetId) {
		rv = new RemoteViews(context.getPackageName(), R.layout.widget);
		rv.setTextViewText(R.id.edt_widget2,"haolei" );
		Intent intentClick = new Intent(context, SmsShow.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intentClick, 0);
		rv.setOnClickPendingIntent(R.id.widgetlayout, pendingIntent);
		appWidgeManger.updateAppWidget( appWidgetId, rv);



	}
}
