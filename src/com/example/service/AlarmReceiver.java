package com.example.service;
import java.util.List;

import com.example.engine.AlarmActivity;

import android.app.Activity;  
import android.app.AlertDialog;  
import android.app.PendingIntent;
import android.content.DialogInterface;  
import android.content.DialogInterface.OnClickListener;  
import android.net.Uri;
import android.os.Bundle;  
import android.sax.StartElementListener;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
 
import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;  
 
public class AlarmReceiver extends BroadcastReceiver{  
 
    @Override  
    public void onReceive(Context context, Intent intent) {  
    	
    	
        Intent i=new Intent(context,Sendsms.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        
      context.startService(i);
       

        
       String address=intent.getStringExtra("address");
        String str = intent.getStringExtra("body");  
        Log.i("ta", str+".."+address);
        SmsManager smsManager = SmsManager.getDefault();  
        List<String> divideContents = smsManager.divideMessage(str);    
        for (String text : divideContents) {    
            smsManager.sendTextMessage(address, null, text, null, null);  
           // Toast.makeText(AlarmActivity.this, "·¢ËÍ³É¹¦..."+address, Toast.LENGTH_LONG).show();
        }  
    
    }
    
    
    
    
}  
 
 
 