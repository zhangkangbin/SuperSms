package com.example.service;

import java.util.Locale;

import com.example.sms_app.R;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

public class SpeakSmsService extends Service {

    private TextToSpeech mSpeech;
    private WindowManager wManager;
    private String a;
    private  String b;
    private  View view ;
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
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	
		
		 a=intent.getStringExtra("address");
		 b=intent.getStringExtra("body");
		
		  mSpeech = new TextToSpeech(this, new OnInitListener() {

	            @Override
	            public void onInit(int status) {
	                // TODO Auto-generated method stub
	                if (status == TextToSpeech.SUCCESS) {
	                    int result = mSpeech.setLanguage(Locale.ENGLISH);
	                    if (result == TextToSpeech.LANG_MISSING_DATA
	                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	                        Log.e("lanageTag", "没发现语音包");
	                    } else {
	                      
//	                        mSpeech.speak("i love you", TextToSpeech.QUEUE_FLUSH,
//	                                null);
	                    	windows();
	                    	 mSpeech.speak("发送人"+a+"信息内容"+b,
	                                 TextToSpeech.QUEUE_FLUSH, null);
	                    	 Log.i("test", a+b);
	                    	// wManager.removeView(view);
	                    }
	                }
	            }
	        });
	}

	
	public void windows(){
		

		//首先，得到WindoeManager对象：
	 wManager = (WindowManager) getApplicationContext().getSystemService( Context.WINDOW_SERVICE);

	//	其次，得到WindowManager.LayoutParams对象，为后续设置相关参数做准备：
		WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();

		//接着，设置相关的窗口布局参数，要实现悬浮窗口效果，主要需要设置的参数有：
		wmParams.type = LayoutParams.TYPE_PHONE; // 设置window type
		wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

		/*
		 * 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
		 */
		//wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		wmParams.gravity = Gravity.CENTER| Gravity. CENTER_VERTICAL; // 调整悬浮窗口至右侧中间
		// 以屏幕左上角为原点，设置x、y初始值
		wmParams.x = 0;
		wmParams.y = 0;

		// 设置悬浮窗口长宽数据
		wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;;
		wmParams.height =WindowManager.LayoutParams.WRAP_CONTENT;;

	//	然后，就可以将需要加到悬浮窗口中的View加入到窗口中了：
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 view = inflater.inflate(R.layout.windows, null);
		Button btn1=(Button) view.findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wManager.removeView(view);
				Toast.makeText(getApplicationContext(), "stop...",0).show();
				Intent i=new Intent(SpeakSmsService.this,SpeakSmsService.class);
				stopService(i);
				 
				System.exit(0);
		
			}
		});
		
		
	
		
			wManager.addView(view,wmParams);


		
	}
	
}
