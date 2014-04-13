package com.example.sms_app;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.ReSmsAdapter;
import com.example.adapter.SmsLocatingAdapter;
import com.example.engine.SmsInfoservice;
import com.example.smsinfo.ReSmsInfo;
import com.example.smsinfo.SmsInfo;
import com.example.util.SMSFile;
import com.example.util.tool;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
	final String[] mItems = {"删除","还原"};
	
	
	Handler myHandler = new Handler() {  
        public void handleMessage(Message msg) {   
             switch (msg.what) {   
                  case 1:  

          			Log.i("file", "通知更新了。。。");
          			//更新List
          			initUi();
          	
          			
                       break;   
             }   
             super.handleMessage(msg);   
        }   
   }; 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.smsrestore);
	
		initUi();
	
		

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int p,
					long arg3) {

				// Toast.makeText(getApplicationContext(), "还原成功"+smsinfos.get(p).getdate(), Toast.LENGTH_LONG).show();
         //一为路径，二为文件名
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


	public void Diag(final String path, final String name){
		
		
		
		

		AlertDialog.Builder   builder=	new AlertDialog.Builder(this);   

	
		 builder.setTitle("选择操作");  

	        builder.setItems(mItems, new DialogInterface.OnClickListener() {  

	            public void onClick(DialogInterface dialog, int which) {  

	                //点击后弹出窗口选择了第几项  

	              //  showDialog("你选择的id为" + which + " , " + mItems[which]); 
	            	if(which==0){
	            		Log.i("file", path+name);
	            		SMSFile delete=new SMSFile();
	            	Boolean b =delete.delete(path);
	            	if(b){
	            		
	            		tool.showShort(Smsrestore.this, "删除成功");
	            	}
	            	else{
	            		tool.showShort(Smsrestore.this, "删除失败");
	            		
	            	}
	            	
	            		
	            		
	            		
	            		
	            		 myHandler.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								 Message msg=new Message();
			                        msg.what=1;
			                        myHandler.sendMessage(msg);
			                        Log.i("file", "通知他更新。。。");
							}
						});
	            		
	            	}
	            	
	            	
	            	
	            	else{

	    				re(path,name);
	            		
	            	}

	            }  

	        });  

	     



		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  

			public void onClick(DialogInterface dialog, int whichButton) {  

				//这里添加点击确定后的逻辑  



			}  

		});  
		builder.create().show();  
	}
	
	
	public void initUi(){
		smsInfoService=new SmsInfoservice(this);
		smsinfos=smsInfoService.resmsList();
		listView = (ListView) findViewById(R.id.list);
		adapter = new ReSmsAdapter ( Smsrestore .this,smsinfos);
		listView.setAdapter(adapter);
		
		
	}
}