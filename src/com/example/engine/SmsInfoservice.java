package com.example.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Parser;
import org.xmlpull.v1.XmlPullParser;

import com.example.smsinfo.ReSmsInfo;
import com.example.smsinfo.SmsInfo;



import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

public class SmsInfoservice {
	Context con;
	CreateDB mOpenHelper;
	SQLiteDatabase db;
	private ContentResolver conResolver;

	public SmsInfoservice (Context con){
		this.con=con;



	}

	public List<SmsInfo> getSmsInfos(){

		List<SmsInfo> smsInfos=new ArrayList<SmsInfo>();
		ContentResolver resolver=con.getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		Cursor cursor = resolver.query(uri, new String[]{"_id","address","date","type","body"},
				null, null, "date desc");
		SmsInfo smsinfo;
		while(cursor.moveToNext()){
			String id = cursor.getString(0);
			String address = cursor.getString(1);
			//Log.i("sms", address);
			String date = cursor.getString(2);
			int type = cursor.getInt(3);
			String body = cursor.getString(4);
			smsinfo = new SmsInfo(id, address, date, type, body);
			smsInfos.add(smsinfo);
			smsinfo = null;

		}

		return smsInfos;

	}
	/**
	 * path sms的路径
	 * * 还原短信信息  ,
	 * @param path 短信备份文件对应的路径 
	 * 
	 * */
	public void restoreSms(String path,String name,ProgressDialog pd) throws Exception{
		//	File file=new File(path);
		//	ContentValues values=null;
		//	FileInputStream fis=new FileInputStream(file);
		//	XmlPullParser parser=Xml.newPullParser();
		//	parser.setInput(fis, "utf-8");
		//	int type=parser.getEventType();
		//	int currentcount=0;
		//	while(type!=XmlPullParser.END_DOCUMENT){
		//		switch(type){
		//		
		//		case XmlPullParser.START_TAG:
		//			if("count".equals(parser.getName())){
		//				
		//				String count =parser.nextText();
		//				pd.setMax(Integer.parseInt(count));
		//			}
		//			if("sms".equals(parser.getName())){
		//			 values=new ContentValues();
		//			}
		//			else if("address".equals(parser.getName())){
		//				values.put("address", parser.nextText());
		//				
		//				
		//			}
		//			else if("date".equals(parser.getName())){
		//				values.put("address", parser.nextText());		
		//			}else if("type".equals(parser.getName())){
		//				values.put("type", parser.nextText());
		//			}else if("body".equals(parser.getName())){
		//				values.put("body", parser.nextText());
		//			}
		//			
		//			break;
		//		
		//		case XmlPullParser.END_TAG:
		//			if("sms".equals(parser.getName())){
		//				ContentResolver resolver=con.getContentResolver();
		//				resolver.insert(Uri.parse("content://sms/"), values);
		//				values=null;
		//				currentcount++;
		//				pd.setProgress(currentcount);
		//			
		//			}
		//			break;
		//		}
		//		type=parser.next();

		//}
		 Log.i("test","333"+path+name);
		db = SQLiteDatabase.openDatabase(path, null, 0);
		//mOpenHelper=new CreateDB(con, name);
	//	db = mOpenHelper.getReadableDatabase();  
		Cursor result=db.rawQuery("SELECT * FROM test", null); 

		result.moveToFirst(); 
		int count = result.getCount();
		Log.i("sms","------"+count);

		ContentValues values=null;

		int currentcount=0;
		conResolver=con.getContentResolver();
		while (!result.isAfterLast()) { 
			int id=result.getInt(0); 
			pd.setMax(count);
			String address=result.getString(1); 
			String body=result.getString(2); 
			String date=result.getString(3);
			int type=result.getInt(4);

			// 判断短信数据库中是否已包含该条短信，如果有，则不需要恢复

			//	 Cursor c = conResolver.query(Uri.parse("content://sms"), new String[]{"date"}, 
			//			 ""+ "=?", new String[]{date}, null);

			// do something useful with these 
			values=new ContentValues();
			values.put("address", address);
			values.put("date", date);
			values.put("body", body);
			values.put("type", type);
			values.put("read", 1);

			Log.i("sms",address+"--"+body+"--"+date);
			ContentResolver resolver=con.getContentResolver();
			resolver.insert(Uri.parse("content://sms/"), values);
			values=null;
			currentcount++;
			pd.setProgress(currentcount);
			result.moveToNext(); 


		} 

		result.close(); 

	}




	public List<SmsInfo> getlocating(String s){

		List<SmsInfo> smsInfos=new ArrayList<SmsInfo>();
		ContentResolver resolver=con.getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		Cursor cursor = resolver.query(uri, new String[]{"_id","address","date","type","body"},
				null, null, "date desc");
		SmsInfo smsinfo;
		while(cursor.moveToNext()){

			String id = cursor.getString(0);
			String address = cursor.getString(1);
			Log.i("sms", address);
			String date = cursor.getString(2);
			int type = cursor.getInt(3);
			String body = cursor.getString(4);
			smsinfo = new SmsInfo(id, address, date, type, body);
			int fan=body.indexOf(s);
			if(fan!=-1){
				smsInfos.add(smsinfo);
				smsinfo = null;
			}
		}

		return smsInfos;

	}


	@SuppressLint("SdCardPath")
	public List<ReSmsInfo> resmsList(){
		List<ReSmsInfo> resmsInfos=new ArrayList<ReSmsInfo>();
		ReSmsInfo reinfo;




		File file = new File("/sdcard/sms/back/");
		if(file.isDirectory()){
			File [] fileArray = file.listFiles();
			if(null != fileArray && 0 != fileArray.length){
				for(int i = 0; i < fileArray.length; i++){
					Log.i("test",fileArray[i]+"---"+fileArray[i].getName());
					reinfo=new ReSmsInfo(fileArray[i].getName(), fileArray[i].length(), fileArray[i].toString());

					resmsInfos.add(reinfo);

				}


			}
			else if (fileArray.length==0){
				reinfo=new ReSmsInfo("没有任何短信备份", 0, "");

				resmsInfos.add(reinfo);

      
            
			}

		}


		return resmsInfos;


	}
}
