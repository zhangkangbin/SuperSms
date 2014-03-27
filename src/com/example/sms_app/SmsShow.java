package com.example.sms_app;





import com.example.widget.MySmsWidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TabHost;
import android.widget.Toast;


public class SmsShow extends Activity  {
	private EditText edt;
	private Button update;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sms_show); 
		edt=(EditText) findViewById(R.id.show_sms_edt);
		update=(Button) findViewById(R.id.btn_update);


		update.setOnClickListener(new OnClickListener() {
 

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String updateString=edt.getText().toString();
				RemoteViews views = new RemoteViews(SmsShow.this.getPackageName(), R.layout.widget);
				views.setTextViewText(R.id.edt_widget2, updateString);

				ComponentName widget = new ComponentName(SmsShow.this,
						MySmsWidget.class);
				AppWidgetManager manager = AppWidgetManager
						.getInstance(SmsShow.this);
				manager.updateAppWidget(widget, views);
				Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_LONG).show();
				//SmsShow.this.finish();

			}
		});
	}

}
