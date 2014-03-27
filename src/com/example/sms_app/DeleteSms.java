package com.example.sms_app;

import java.util.List;

import com.example.engine.ContactInfoService;
import com.example.service.DeleteSmsService;
import com.example.service.backupSmsService;
import com.example.smsinfo.ContactInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DeleteSms extends Activity{

	Button delete;
	List<ContactInfo> infos;
	ContactInfoService c;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
	    c =new ContactInfoService(this);
		delete=(Button) findViewById(R.id.delete_btn);
		delete.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			delect();
				
			}
		});
	}
public void delect(){
	new Thread(){
		
		public void run(){
			
			Intent i=new Intent(DeleteSms.this,DeleteSmsService.class);
			
			

			startService(i);
		}
		
	}.start();
	
	
}

}
