package com.example.sms_app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);



		// 完成窗体的全屏显示 // 取消掉状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		new Thread(){

			@Override
			public void run() {
				super.run();
				try {
					sleep(1000);
				
					home();
					finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
	}

	
public void home(){
	Intent i=new Intent(this,home.class);
	startActivity(i);
}
}
