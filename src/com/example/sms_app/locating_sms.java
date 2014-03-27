package com.example.sms_app;

import java.util.List;

import com.example.adapter.SmsLocatingAdapter;
import com.example.engine.SmsInfoservice;
import com.example.smsinfo.SmsInfo;






import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

public class locating_sms extends Activity {
	EditText edt_locating;
	private ListView lv;
	SmsLocatingAdapter adpter;
	SmsInfoservice	smsInfoService ;
	List<SmsInfo> smsinfos;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locating);

		smsInfoService= new SmsInfoservice(this);

		lv = (ListView) this.findViewById(R.id.list_locating);

		edt_locating=(EditText) findViewById(R.id.edt_locating);


	
		edt_locating.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {



				smsinfos=smsInfoService.getlocating(s.toString());
				adpter=new SmsLocatingAdapter(getApplicationContext(), smsinfos);

	     	lv.setAdapter(adpter);
			}

			@Override
			public void beforeTextChanged(CharSequence text, int arg1, int arg2,
					int arg3) {


			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});



	}


}
