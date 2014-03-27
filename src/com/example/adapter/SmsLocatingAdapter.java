package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.sms_app.R;
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

public class SmsLocatingAdapter  extends BaseAdapter{
	
	private Context context;
	private LayoutInflater inflater;
	private static TextView tv_name;
	private static TextView tv_context;
	
	List<SmsInfo>  smsinfos;
	String chazhao;
	public SmsLocatingAdapter(Context context,List<SmsInfo>  smsinfos){
	
			this.context = context;
		
			 this.smsinfos= smsinfos;
		
	}
	

	public int getCount() {
		// TODO Auto-generated method stub
		return  smsinfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return smsinfos.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
/**
 * 
 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SmsInfo info = smsinfos.get(position);
		View view = View.inflate(context, position, null);
		tv_name =  (TextView) view.findViewById(R.id.phone_locating);
		tv_context =  (TextView) view.findViewById(R.id.text_locating);


	
			tv_name.setText(info.getAddress());
			tv_context.setText(info.getBody());
			
		
			
		
		
		
		return view;
	}

}
