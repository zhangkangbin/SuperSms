package com.example.sms_app;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.ReSmsAdapter;
import com.example.adapter.SmsLocatingAdapter;
import com.example.engine.SmsInfoservice;
import com.example.smsinfo.ReSmsInfo;
import com.example.smsinfo.SmsInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Smsrestore extends Activity{
	private ListView listView;
	ReSmsAdapter adapter;
	SmsInfoservice	smsInfoService ;
	List<ReSmsInfo> smsinfos;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.smsrestore);
		smsInfoService=new SmsInfoservice(this);
		smsinfos=smsInfoService.resmsList();
		listView = (ListView) findViewById(R.id.list);
		adapter = new ReSmsAdapter (this,smsinfos);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int p,
					long arg3) {

				// Toast.makeText(getApplicationContext(), "还原成功"+smsinfos.get(p).getdate(), Toast.LENGTH_LONG).show();

				Diag(smsinfos.get(p).getdate(),smsinfos.get(p).getname());

				Log.i("test","111"+smsinfos.get(p).getdate()+smsinfos.get(p).getname());

			}
		});
	}


	@SuppressWarnings("deprecation")
	public void re(final String path,final String name){

		final ProgressDialog pd = new ProgressDialog(this);

		pd.setCancelable(false);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("正在还原....");
		pd.setButton("隐藏", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub

			}
		});

		pd.show();
		smsInfoService=new SmsInfoservice(this);
		new Thread(){

			public void run(){

				try {
					smsInfoService.restoreSms(path, name, pd);
					Log.i("test","222"+path+name);
					pd.dismiss();
					Looper.prepare();
					Toast.makeText(getApplicationContext(), "还原成功", 0).show();
					Looper.loop();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					pd.dismiss();
					Looper.prepare();
					Toast.makeText(getApplicationContext(), "还原失败", 0).show();
					Looper.loop();
				}


			}



		}.start();


	}


	public void Diag(final String string, final String string2){

		AlertDialog.Builder   builder=	new AlertDialog.Builder(this);   

		// builder.setTitle("关于"); 
		builder.setMessage("确定要还原这个备份文件的短信？");

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  

			public void onClick(DialogInterface dialog, int whichButton) {  

				//这里添加点击确定后的逻辑  


				re(string,string2);
			}  

		});  
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  

			public void onClick(DialogInterface dialog, int whichButton) {  

				//这里添加点击确定后的逻辑  



			}  

		});  
		builder.create().show();  
	}
}
