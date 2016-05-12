package com.example.jvmint.mbean;

import java.beans.ConstructorProperties;

public class ServiceStats {

	private int id;
	private String action;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@ConstructorProperties({"id", "action"}) 
	public ServiceStats(int id, String action) {
		super();
		this.id = id;
		this.action = action;
	} 

}
