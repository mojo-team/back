package fr.mojope.hackathon.chabot.apiai.entitycreation;

import java.util.List;

public class Body {
	private String name;
	private List<Entry> entries;
	
	public class Entry {
		private String value;
		private List<String> synonyms;
		
		
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public List<String> getSynonyms() {
			return synonyms;
		}
		public void setSynonyms(List<String> synonyms) {
			this.synonyms = synonyms;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
}