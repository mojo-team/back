package fr.mojope.hackathon.chabot.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.mojope.hackathon.chabot.facebook.jsonwrapper.Element;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.Element.Button;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponse;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.QuickReply;

@Service
public class FacebookSender {

	private static final String token = "EAASZAVGTIcW4BAMlbxXrYDR1kZB7f4U08bCgJn0caj8SsfbyKssoJNw9jnzEavkC4U0tZAaH2ZAntojZBmaS0lyXUVE9TtLSzgYt5FH6H5ZC882otv5ZCm8s2pGKPo0Ry9ZCZB9q7Le9c49mt9hgLNZCTyRmTXGZCw4a3vhXaM87siZAJAZDZD";
	private static final String address = "https://graph.facebook.com/v2.6/me/messages?access_token=";
	
	private static final String UserProfileAPI = "https://graph.facebook.com/v2.6/%s?access_token=";
	
	
	public String getName(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(String.format(UserProfileAPI, userId) + token, String.class);
	}
	
	public String sendAskReviewMessage (String userId) {
		return sendMessageQuickReplies(userId, "What did you think about your last reservation ?", "1 - Awful", "2 - Bad", "3 - Ok", "4 - Good", "5 - Excellent !");
	}
	
	public String sendMessage(String userId, String message) {
		FacebookResponse response = new FacebookResponse();
		response.setRecipient(response.new Recipient());
		response.getRecipient().setId(userId);
		response.setMessage(response.new Message());
		response.getMessage().setText(message);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(address + token, response, String.class);
	}
	
	public String sendMessageQuickReplies(String userId, String message, String ... options) {
		FacebookResponse response = new FacebookResponse();
		response.setRecipient(response.new Recipient());
		response.getRecipient().setId(userId);
		response.setMessage(response.new Message());
		response.getMessage().setText(message);
		
		List<QuickReply> quickReplies = new ArrayList<>();
		for(String s : options) {
			QuickReply reply = new QuickReply();
			reply.setContent_type("text");
			reply.setTitle(s);
			reply.setPayload(s);
			quickReplies.add(reply);
		}
		response.getMessage().setQuick_replies(quickReplies);
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(address + token, response, String.class);
	}
	
}
