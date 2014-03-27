package com.example.sms_app;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddSms extends Activity {
	EditText edt_addressee;
	EditText  edt_content;
	CheckBox check;
	Button addsms;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_sms);
		 edt_addressee=(EditText) findViewById(R.id.edt_addressee);
		 edt_content=(EditText) findViewById(R.id.edt_content);
		 check=(CheckBox) findViewById(R.id.checkBox1);
		
		addsms=(Button) findViewById(R.id.btn_add);
		addsms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    /** 拿到输入的手机号码 **/
                String number =  edt_addressee.getText().toString();
                /** 拿到输入的短信内容 **/
                String text = edt_content.getText().toString();
                boolean sms_state=check.isChecked();


                if("".equals(number)){
                	
                	  Toast.makeText(getApplicationContext(), "手机号码不能为空", Toast.LENGTH_LONG).show();
                }
                else{
                	if(sms_state){
                		  
                        /**将发送的短信插入数据库**/
                        ContentValues values = new ContentValues();
                        
                        //发送时间
                        values.put("date", System.currentTimeMillis());
                        Log.i("time","时间"+System.currentTimeMillis() );
                        //阅读状态
                        values.put("read", 0);
                        //1为收 2为发
                        values.put("type", 2);
                        //送达号码
                        values.put("address", number);
                        //送达内容
                        values.put("body", text);
                        //插入短信库
                        getContentResolver().insert(Uri.parse("content://sms"),values);
                        Toast.makeText(getApplicationContext(), "成功生成已发短信", Toast.LENGTH_LONG).show();
             	
                	}
                	else {
                		   /**将发送的短信插入数据库**/
                        ContentValues values = new ContentValues();
                        //发送时间
                        values.put("date", System.currentTimeMillis());
                        Log.i("time","时间"+System.currentTimeMillis() );
                        //阅读状态
                        values.put("read", 1);
                        //1为收 2为发
                        values.put("type", 1);
                        //送达号码
                        values.put("address", number);
                        //送达内容
                        values.put("body", text);
                        //插入短信库
                        getContentResolver().insert(Uri.parse("content://sms"),values);
                        Toast.makeText(getApplicationContext(), "成功生成接收短信", Toast.LENGTH_LONG).show();
                		
                		
                	}
                	
                }

			}
		});
	}



}
