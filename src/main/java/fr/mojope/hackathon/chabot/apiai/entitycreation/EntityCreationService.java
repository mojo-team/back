package fr.mojope.hackathon.chabot.apiai.entitycreation;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import fr.mojope.hackathon.chabot.apiai.entitycreation.Body;
import fr.mojope.hackathon.chabot.apiai.entitycreation.Body.Entry;


@Service
public class EntityCreationService {
	
	private static String address = "https://api.api.ai/v1/entities?v=20150910";
	private static String token = "5c363d28d81d4367a3181a6ecd96710e";

	public static void sendEntries(String entityName, Set<String> values) {
		Body body = new Body();
		body.setName(entityName);
		body.setEntries(new ArrayList<Entry>());
		for(String s : values) {
			Entry entry = body.new Entry();
			entry.setValue(s);
			body.getEntries().add(entry);
		}
		RestTemplate template = new RestTemplate();
		System.out.println(template.postForObject(address, body, String.class, getHttpEntity()));
	}
	
	private static HttpEntity<?> getHttpEntity() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", "Bearer " + token);
		headers.add("Content-Type", "application/json; charset)utf-8");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		return entity;
	}
}
