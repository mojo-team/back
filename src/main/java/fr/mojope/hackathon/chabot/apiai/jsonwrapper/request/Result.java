package fr.mojope.hackathon.chabot.apiai.jsonwrapper.request;

import java.util.LinkedHashMap;

public class Result {
	private LinkedHashMap<String, String> parameters;
	private double score;
	private Metadata metadata;
	private String resolvedQuery;
	
	public Result() {}

	public LinkedHashMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(LinkedHashMap<String, String> parameters) {
		this.parameters = parameters;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getResolvedQuery() {
		return resolvedQuery;
	}

	public void setResolvedQuery(String resolvedQuery) {
		this.resolvedQuery = resolvedQuery;
	}
	
	
}
