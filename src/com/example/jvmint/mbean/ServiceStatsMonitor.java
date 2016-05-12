package com.example.jvmint.mbean;

import com.example.jvmint.service.RestService;

public class ServiceStatsMonitor implements IServiceStatsMonitor {
	
	private RestService restService;
	
	public ServiceStatsMonitor(RestService restService) {
		this.restService = restService;
	}

	public ServiceStats getServiceStats() {
		return new ServiceStats(restService.getId(), restService.getAction());
	}
}
