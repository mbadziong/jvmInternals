package com.example.jvmint;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.example.jvmint.mbean.ServiceMonitor;
import com.example.jvmint.mbean.ServiceStatsMonitor;
import com.example.jvmint.service.RestService;

public class Main {


	public static void main(String[] args) throws Exception {
		
		RestService restService = new RestService();
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		
		ObjectName monitorName = new ObjectName("com.example.jvmint:type=Server,name=RestService");
		ServiceMonitor sm = new ServiceMonitor(restService);
		mbs.registerMBean(sm, monitorName);
		
		ObjectName statsMonitorName = new ObjectName("com.example.jvmint:type=Server,name=ServiceStats");
		ServiceStatsMonitor ssm = new ServiceStatsMonitor(restService);
		mbs.registerMBean(ssm, statsMonitorName);
		
		
		while (true){
			restService.doSomething();
			Thread.sleep(5000);
		}

	}

}
