package fr.mojope.hackathon.chabot.apiai.jsonwrapper.response;

import java.util.List;

public class Response {
	private String speech;
    private String displayText;
    private FollowupEvent followupEvent = new FollowupEvent();
    
	private List<Context> contextOut;
	
	public class Context {
		private String name;
		private int lifespan;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getLifespan() {
			return lifespan;
		}
		public void setLifespan(int lifespan) {
			this.lifespan = lifespan;
		}
	}
	
	public List<Context> getContextOut() {
		return contextOut;
	}

	public void setContextOut(List<Context> contextOut) {
		this.contextOut = contextOut;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public FollowupEvent getFollowupEvent() {
		return followupEvent;
	}

	public void setFollowupEvent(FollowupEvent followupEvent) {
		this.followupEvent = followupEvent;
	}
    
}
