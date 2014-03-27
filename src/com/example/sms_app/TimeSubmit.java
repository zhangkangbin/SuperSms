package com.example.sms_app;

import java.util.Calendar;

import com.example.service.AlarmReceiver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimeSubmit extends Activity {

	DatePicker date;
	TimePicker time;
	private int year;  
	private int month;  
	private int day;  
	private int hour;  
	private int minute;  
	private static  AlarmManager alarmManager=null;
	Button btnsubmit;
	EditText address;
	EditText body;
	String str_address;
	String str_body;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog);


		alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
		btnsubmit=(Button) findViewById(R.id.btn_submit);
		address=(EditText) findViewById(R.id.edt_address);
		body=(EditText) findViewById(R.id.edt_content);

		date=(DatePicker) findViewById(R.id.datePicker1);
		time=(TimePicker) findViewById(R.id.timePicker1);
		Calendar c =Calendar.getInstance();  
		year =c.get(Calendar.YEAR);  
		month=c.get(Calendar.MONTH);  
		day=c.get(Calendar.DAY_OF_MONTH);  
		hour = c.get(Calendar.HOUR);  
		minute =c.get(Calendar.MINUTE);  


		date.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,  
					int dayOfMonth) {
				TimeSubmit.this.year=year;  
				TimeSubmit.this.month=monthOfYear;  
				TimeSubmit.this.day=dayOfMonth;  
			}
		} );
		time.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub  
				TimeSubmit.this.hour=hourOfDay;  
				TimeSubmit.this.minute=minute;  

			}
		});


		btnsubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str_address=address.getText().toString();
				String str_body=body.getText().toString();
				Log.i("test",str_address+"==="+str_body);
				
				if(str_address.equals("")){
					Toast.makeText(TimeSubmit.this, "input has null", Toast.LENGTH_LONG).show();//提示用户
					}
				else{
					
					submit(str_address,str_body);
					finish();
					
				}

			}
		});
	}

	private void  submit(String str_a,String str_b) {
		Calendar c=Calendar.getInstance();//获取日期对象    
		c.setTimeInMillis(System.currentTimeMillis());        //设置Calendar对象
		c.set(Calendar.HOUR_OF_DAY, hour);
		// c.set(Calendar.HOUR, hourOfDay);        //设置闹钟小时数
		c.set(Calendar.MINUTE, minute);            //设置闹钟的分钟数
		c.set(Calendar.SECOND, 0);                //设置闹钟的秒数
		c.set(Calendar.MILLISECOND, 0);  
		Intent intent = new Intent(TimeSubmit.this, AlarmReceiver.class);    //创建Intent对象
		intent.putExtra("address", str_a);
		intent.putExtra("body", str_b);
	
		//sendBroadcast(intent); 
		PendingIntent pi = PendingIntent.getBroadcast(TimeSubmit.this, 0, intent, 0);    //创建PendingIntent
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),c.getTimeInMillis(), pi);        //设置闹钟
		//	alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);        //设置闹钟，当前时间就唤醒
		Toast.makeText(TimeSubmit.this, "闹钟设置成功", Toast.LENGTH_LONG).show();//提示用户
	}


}