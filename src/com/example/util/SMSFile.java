package com.example.util;

import java.io.File;

import android.util.Log;

public class SMSFile {
	
	
	public boolean delete(String path){
	Log.i("file", path);
		File f=new File(path);
		if(	f.exists())
		{
			f.delete();
			
			Log.i("file", "文件delete成功");
			return true;
		}
		else{
			
			Log.i("file", "没有这个文件");
			return false;
		}
	
		
		
		
		
		
	}

}
