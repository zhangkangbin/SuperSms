package com.example.adapter;



import java.util.ArrayList;
import java.util.List;

import com.example.sms_app.R;
import com.example.smsinfo.ReSmsInfo;
import com.example.smsinfo.SmsInfo;




import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ReSmsAdapter  extends BaseAdapter{

	private static final String TAG = "MainUIAdapter";
	private Context context;
	private LayoutInflater inflater;
	List<ReSmsInfo>  smsinfos;

	private static TextView tv_name;
	private static TextView tv_size;
	private static TextView tv_date;

	
	public ReSmsAdapter(Context context,	List<ReSmsInfo>  smsinfos) {
		this.context = context;
		this.smsinfos=smsinfos;
		inflater = LayoutInflater.from(context);
		
	}



	public int getCount() {
		
		return smsinfos.size();
	}

	public Object getItem(int position) {
		
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// getview的方法被调用了多少次?
		// 9
		// gridview 控件bug 
		// won't fix 
		// 使用静态的变量引用 减少内存中申请的引用的个数 
		
		Log.i(TAG,"getview "+ position);
		View view = inflater.inflate(R.layout.resms_adpter, null);

		tv_name =  (TextView) view.findViewById(R.id.textView1);
		tv_size =  (TextView) view.findViewById(R.id.textView2);
		tv_date =  (TextView) view.findViewById(R.id.textView3);

		tv_name.setText(smsinfos.get(position).getname());
		tv_size.setText(smsinfos.get(position).getdate());
		tv_date.setText(smsinfos.get(position).getsize()+"");
	
		return view;
	}

}