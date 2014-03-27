package com.example.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DeleteSmsService  extends Service{


	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i("ta", "...................");
		super.onStart(intent, startId);
		deleteSMS();
		Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();
	}
	/*
	 * Delete all SMS one by one
	 */
	public void deleteSMS() {
		try {
			ContentResolver CR = getContentResolver();
			// Query SMS
			/**	content://sms/inbox        收件箱 
				content://sms/sent        已发送 
				content://sms/draft        草稿 
				content://sms/outbox        发件箱 
				content://sms/failed        发送失败 
				content://sms/queued        待发送列表
			 */
			Uri uriSms = Uri.parse("content://sms/inbox");
			Cursor c = CR.query(uriSms,
					new String[] { "_id", "thread_id" }, null, null, null);
			if (null != c && c.moveToFirst()) {
				do {
					// Delete SMS
					long threadId = c.getLong(1);
					CR.delete(Uri.parse("content://sms/conversations/" + threadId),
							null, null);
					Log.d("deleteSMS", "threadId:: "+threadId);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("deleteSMS", "Exception:: " + e);
		}
	}
}
