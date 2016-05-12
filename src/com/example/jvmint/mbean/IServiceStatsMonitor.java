package com.example.jvmint.mbean;

import javax.management.MXBean;

@MXBean
public interface IServiceStatsMonitor {
	
	ServiceStats getServiceStats();

}
