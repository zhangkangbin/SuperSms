package com.example.sms_app;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.ReSmsAdapter;
import com.example.adapter.SlideCutListView;
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

import com.example.adapter.SlideCutListView.RemoveDirection;
import com.example.adapter.SlideCutListView.RemoveListener;







public class Smsrestore extends Activity implements RemoveListener{
	private SlideCutListView slideCutListView ;
	private ArrayAdapter<String> adapter;
	private List<String> dataSourceList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		slideCutListView = (SlideCutListView) findViewById(R.id.slideCutListView);
		slideCutListView.setRemoveListener(this);
		
		for(int i=0; i<20; i++){
			dataSourceList.add("����ɾ��" + i); 
		}
		
		adapter = new ArrayAdapter<String>(this, R.layout.me_item, R.id.list, dataSourceList);
		slideCutListView.setAdapter(adapter);
		
		slideCutListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(Smsrestore.this, dataSourceList.get(position), Toast.LENGTH_SHORT).show();
			}
		});
	}

	
	//����ɾ��֮��Ļص�����
	@Override
	public void removeItem(RemoveDirection direction, int position) {
		adapter.remove(adapter.getItem(position));
		switch (direction) {
		case RIGHT:
			Toast.makeText(this, "����ɾ��  "+ position, Toast.LENGTH_SHORT).show();
			break;
		case LEFT:
			Toast.makeText(this, "����ɾ��  "+ position, Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		
	}	


}
