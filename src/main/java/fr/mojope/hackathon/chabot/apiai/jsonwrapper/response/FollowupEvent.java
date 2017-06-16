package fr.mojope.hackathon.chabot.apiai.jsonwrapper.response;

import java.util.LinkedHashMap;

public class FollowupEvent {
	private String name;
	private LinkedHashMap<String, String> data;
	
	public FollowupEvent() {}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public LinkedHashMap<String, String> getData() {
		return data;
	}

	public void setData(LinkedHashMap<String, String> data) {
		this.data = data;
	}
	
}
