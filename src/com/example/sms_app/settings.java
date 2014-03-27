package com.example.sms_app;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class settings extends Activity implements OnItemClickListener  {
	private EditText edt;
	private Button update;
	ListView settinglist;
	String arr[]={"备份路径","自动备份"};
	private ArrayList<String> list = new ArrayList<String>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings); 

		list.add("备份路径");
		list.add("自动备份");

		settinglist=(ListView) findViewById(R.id.setting_list);
		settinglist.setAdapter(new ArrayAdapter<String>(this, R.layout.me_item,R.id.title,arr));



		settinglist.setOnItemClickListener(this);


	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		if(list.get(position).equals("备份路径"));
		{
			AlertDialog.Builder a=new AlertDialog.Builder(this) ;
			a.setTitle("文件备份路径") ; 
			a.setIcon(android.R.drawable.ic_btn_speak_now)  ;
			final EditText e	=new EditText(this);
			e.setText("/sdcard/sms/");
			a.setView(e) ; 
			a.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stu
					
				}
			}) ; 
			a.setNegativeButton("取消", null) ; 
			a.show();    
		}
		
		 if(list.get(position).equals("自动备份")){
			
			
		}
  
	}
}
