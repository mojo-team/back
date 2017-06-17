package fr.mojope.hackathon.chabot.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.mojope.hackathon.chabot.facebook.jsonwrapper.Element;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.Element.Button;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponseButton.Message;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponseButton.Recipient;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponseButton.Message.Attachment;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponseButton.Message.Attachment.Payload;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponse;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponseButton;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.IdentificationApi;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.QuickReply;

@Service
public class FacebookSender {

	private static final String token = "EAASZAVGTIcW4BAMlbxXrYDR1kZB7f4U08bCgJn0caj8SsfbyKssoJNw9jnzEavkC4U0tZAaH2ZAntojZBmaS0lyXUVE9TtLSzgYt5FH6H5ZC882otv5ZCm8s2pGKPo0Ry9ZCZB9q7Le9c49mt9hgLNZCTyRmTXGZCw4a3vhXaM87siZAJAZDZD";
	private static final String address = "https://graph.facebook.com/v2.6/me/messages?access_token=";
	
	private static final String UserProfileAPI = "https://graph.facebook.com/v2.6/%s?access_token=";
	
	
	public String askForReview(String userId, String firstName) {
		return sendMessage(userId, String.format("Hi %s! I can see that you just finished your meeting. Do you have time to give me a quick feedback ?", firstName));
	}
	
	public String askReviewMessage (String userId, String lastReservation) {
		return sendMessageQuickReplies(userId, String.format("So, how was your meeting room at %s ?", lastReservation), "1 - Awful", "2 - Bad", "3 - Ok", "4 - Good", "5 - Excellent !");
	}
	
	public String sendMeBackMessage(String userId) {
		FacebookResponseButton response = new FacebookResponseButton();
		response.setRecipient(response.new Recipient());
		response.getRecipient().setId(userId);
		Element elem = new Element();
		Button button = elem.new Button();
		button.setTitle("Send me back to Spotter !");
		button.setType("web_url");
		button.setUrl("https://mojopeinc.firebaseapp.com");
		response.setMessage(response.new Message());
		response.getMessage().setAttachment(response.getMessage().new Attachment());
		response.getMessage().getAttachment().setPayload(response.getMessage().getAttachment().new Payload());
		response.getMessage().getAttachment().getPayload().setButtons(new ArrayList<>(Arrays.asList(button)));
		response.getMessage().getAttachment().getPayload().setText("Noted, we stay in touch.");
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(address + token, response, String.class);
	}
	
	public String getFirstName(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		IdentificationApi id = restTemplate.getForObject(String.format(UserProfileAPI, userId) + token, IdentificationApi.class);
		return id.getFirst_name();
	}
	
	public String getLastName(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		IdentificationApi id = restTemplate.getForObject(String.format(UserProfileAPI, userId) + token, IdentificationApi.class);
		return id.getLast_name();
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
