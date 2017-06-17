package fr.mojope.hackathon.chabot.scripting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mojope.hackathon.chabot.apiai.jsonwrapper.request.Request;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response;
import fr.mojope.hackathon.chabot.facebook.FacebookSender;

@Service
public class ScriptingManager {

	@Autowired
	private FacebookSender facebookSender;
	
	@Autowired
	private ApiCall apiCall;
	
	private String lastUserId;
	private String lastUserFirstName;
	
	public Response getResponse(Request request) {
		String intent = request.getResult().getMetadata().getIntentName();
	 	String userId = request.getOriginalRequest().getData().getSender().getId();
	 	lastUserId = userId;
	 	
	 	String firstName = facebookSender.getFirstName(userId);
	 	lastUserFirstName = firstName;
	 	String lastName = facebookSender.getLastName(userId);
	 	
	 	if(intent.equalsIgnoreCase("AskingForReviewConfirmation")) {
    		String lastReservation = apiCall.getLastReservation(firstName, lastName);
	 		facebookSender.askReviewMessage(userId, lastReservation);
	 	}
	 	
    	
    	
    	Response response = new Response();
    	response.setSpeech("" +facebookSender.getFirstName(userId));
    	
    	return null;
	}

	public String getLastUserId() {
		return lastUserId;
	}

	public void setLastUserId(String lastUserId) {
		this.lastUserId = lastUserId;
	}

	public String getLastUserFirstName() {
		return lastUserFirstName;
	}

	public void setLastUserFirstName(String lastUserFirstName) {
		this.lastUserFirstName = lastUserFirstName;
	}
	
}
