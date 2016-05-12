package com.example.jvmint.service;

import com.example.jvmint.rest.MyRestClient;

public class RestService {

	private int id = 1;
	private String action = "comments";
	private MyRestClient restClient;
	
	public RestService() {
		super();
		this.restClient = new MyRestClient();
	}
	
	public RestService(int id, String action) {
		super();
		this.id = id;
		this.action = action;
		this.restClient = new MyRestClient();
	}
	
	public void doSomething() {
		this.restClient.run(this.id, this.action);
	}

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
}
