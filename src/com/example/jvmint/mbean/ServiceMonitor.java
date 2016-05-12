package com.example.jvmint.mbean;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import com.example.jvmint.service.RestService;

public class ServiceMonitor extends NotificationBroadcasterSupport implements
		ServiceMonitorMBean {

	private RestService restService;
	private int sequenceNumber = 0;

	public ServiceMonitor(RestService restService) {
		this.restService = restService;
	}

	public void setId(int id) {
	
		if (id > 500) {
			Notification notification = new AttributeChangeNotification(this,
					sequenceNumber++, System.currentTimeMillis(),
					"Przekroczenie id, beda bledy. Odrzucam zmiane!", "id", "int", restService.getId(),
					id);
			notification.setUserData("zmiana id  na " + id);
			sendNotification(notification);
			
			return;
		}		
		restService.setId(id);
	}

	public MBeanNotificationInfo[] getNotificationInfo() {
		String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };

		String name = AttributeChangeNotification.class.getName();
		String description = "Zmiana wartosci atrybutu";
		MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
				description);
		return new MBeanNotificationInfo[] { info };
	}

	public int getId() {
		return restService.getId();
	}

	public void clear() {
		restService.setId(0);
	}

	public String getAction() {
		// TODO Auto-generated method stub
		return restService.getAction();
	}

}
