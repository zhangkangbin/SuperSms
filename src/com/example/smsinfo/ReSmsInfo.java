package com.example.smsinfo;




/**
 * type 1 .代表的是接受 2.代表发送
 * 
 * @author xiaobao
 * 
 */
public class ReSmsInfo {
	private String name;
	private long size;
	private String date;
	

	public ReSmsInfo() {
	}

	public ReSmsInfo(String name, long size, String date) {
		this.name = name;
		this.size = size;
		this.date = date;
	
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public long getsize() {
		return size;
	}

	public void setsize(long size) {
		this.size = size;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	


}
