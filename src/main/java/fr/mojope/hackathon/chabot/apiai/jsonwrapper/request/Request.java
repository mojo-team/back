package fr.mojope.hackathon.chabot.apiai.jsonwrapper.request;

import java.util.Map.Entry;

public class Request {
	private String timestamp;
	private String sessionId;
	private Result result;
	private OriginalRequest originalRequest;
	
	/**
	 * Id unique du résultat
	 */
	private String id;
	

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Request :\n"
				+ "timestamp=" + timestamp + "\n"
				+ "sessionId=" + sessionId + "\n"
				+ "id=" + id + "\n"
				+ "result.score=" + result.getScore() + "\n"
				+ "result.metadata.intent=" + result.getMetadata().getIntentName() + "\n"
				+ "result.parameters=");
		for(Entry<String, String> entry : result.getParameters().entrySet()) {
			sb.append("\n" + entry.getKey() + " - " + entry.getValue());
		}
		return sb.toString();
	}

	public OriginalRequest getOriginalRequest() {
		return originalRequest;
	}

	public void setOriginalRequest(OriginalRequest originalRequest) {
		this.originalRequest = originalRequest;
	}

}
