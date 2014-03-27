package com.example.engine;


import com.example.sms_app.R;
import com.example.sms_app.home;

import android.app.Activity;  
import android.app.AlertDialog;  
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;  
import android.content.DialogInterface.OnClickListener;  
import android.content.Intent;
import android.os.Bundle;  


public class AlarmActivity extends Activity {  

	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState); 
	

        //显示对话框  
        new AlertDialog.Builder(AlarmActivity.this).  
            setTitle("定时短信").//设置标题  
            setMessage("短信发送成功！").//设置内容  
            setPositiveButton("知道了", new OnClickListener(){//设置按钮  
                public void onClick(DialogInterface dialog, int which) {  
                    AlarmActivity.this.finish();//关闭Activity  
                }  
            }).create().show();  
 
	
	}


	
}  









