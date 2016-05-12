package com.example.jvmint.mbean;

public interface ServiceMonitorMBean {
	
	public void setId(int id);
	public int getId();
	
	public String getAction();
	
	public void clear();

}
