package com.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.example.engine.CreateDB;
import com.example.engine.SmsInfoservice;
import com.example.smsinfo.SmsInfo;







import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;


import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

public class backupSmsService extends Service {
	private SmsInfoservice smsInfoService;
	private  List<SmsInfo>  smsinfos;
	CreateDB sqlhelper;
	SQLiteDatabase db;
	String NAME_TABLE_CREATE = "create table test("  

        + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "address TEXT,"+"body INTEGER DEFAULT 100,"+ "date INTEGER DEFAULT 100,"  

        + "type INT);"; 
	private SQLiteDatabase mDb;
	public IBinder onBind(Intent i) {
		// TODO Auto-generated method stub
		

		return null;
	}


	public void onCreate() {

		
	}


	public void onStart(Intent i,int id){
		super.onStart(i,id);
	    final String FileName= i.getStringExtra("FileName");
		//Toast.makeText(getApplicationContext(), "备份zhong --", 1).show();	
		smsInfoService = new SmsInfoservice(this);
		smsinfos = smsInfoService.getSmsInfos();
	
		super.onCreate();
		if(smsinfos.size()>0){
		new Thread(){

			

			@Override
			public void run() {
				String SD_path=Environment.getExternalStorageDirectory()
						.getPath();
				
				 //获取SharedPreferences对象
		        Context ctx = backupSmsService.this;       
		        SharedPreferences sp = ctx.getSharedPreferences("SMS", MODE_PRIVATE);
		       String smsxml= sp.getString("SMS_PATH", null);
		        //存入数据
		        Editor editor = sp.edit();
		        if(smsxml==null){
		        	editor.putString("SMS_PATH", SD_path+"/sms/back/");
				      
			        editor.commit();
		        }
		        
				String smsxml2=sp.getString("SMS_PATH", null);
				Log.i("baiud", smsxml+"-------"+smsxml2);
				File file=new File(smsxml2);
				if(!file.exists()){
					file.mkdirs();
				
					
				}
				
				
					    
			//	sqlhelper=new CreateDB(getApplicationContext(), FileName+".db", null, 1);
				mDb = SQLiteDatabase.openOrCreateDatabase(smsxml2+FileName+".db",null);
				mDb.execSQL(NAME_TABLE_CREATE);
			 //   db=sqlhelper.getWritableDatabase();
							
							backsms();
					
			}
		
		}.start();}
		else{
			Toast.makeText(getApplicationContext(), "短信为空", 1).show();
			
		}
		
}
	
	
	public void backsms(){
		
		try {
			
			
		

		//	File file = new File(FileName);
//			XmlSerializer serializer = Xml.newSerializer();
//			FileOutputStream os = new FileOutputStream(file);
//			serializer.setOutput(os, "utf-8");
//			serializer.startDocument("utf-8", true);
//			
//			serializer.startTag(null, "smss");
//			serializer.startTag(null, "count");
//			serializer.text(smsinfos.size()+"");
//			serializer.endTag(null, "count");
			for(SmsInfo info : smsinfos){
				ContentValues values = new ContentValues();
				values.put("address",info.getAddress());
				values.put("body", info.getBody());
				values.put("date", info.getDate());
				values.put("type", info.getType());
		
				mDb.insert("test", null, values);
//				byte[] b=info.getBody().getBytes();
//				os.write(b);
//				os.write("\n".getBytes());
				Log.i("sms",info.getAddress()+"--"+info.getDate());
//				serializer.startTag(null, "sms");
//				serializer.startTag(null, "id");
//				serializer.text(info.getId());
//				serializer.endTag(null, "id");
//				
//				serializer.startTag(null, "address");
//				serializer.text(info.getAddress().toString());
//				serializer.endTag(null, "address");
//				
//				serializer.startTag(null, "date");
//				serializer.text(info.getDate());
//			serializer.endTag(null, "date");
//				
//				serializer.startTag(null, "type");
//				serializer.text(info.getType()+"");
//				serializer.endTag(null, "type");
//				
//				serializer.startTag(null, "body");
//				serializer.text(info.getBody());
//				serializer.endTag(null, "body");
//				serializer.text("\n");
//				serializer.endTag(null, "sms");
//				
//				
			}
//			serializer.endTag(null, "smss");
//			serializer.endDocument();
//			//把文件缓冲区的数据写出去
//			os.flush();
//			os.close();
			Looper.prepare();
			int sum=smsinfos.size();
			Toast.makeText(getApplicationContext(), sum+"短信备份完成", 1).show();
			Looper.loop();
		} catch (Exception e) {
			e.printStackTrace();
			Looper.prepare();
			Toast.makeText(getApplicationContext(), "备份失败", 1).show();
			Looper.loop();
	
		}
	}
}
