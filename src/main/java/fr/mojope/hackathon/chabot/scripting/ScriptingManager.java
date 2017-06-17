package fr.mojope.hackathon.chabot.scripting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mojope.hackathon.chabot.apiai.jsonwrapper.request.Request;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response.Context;
import fr.mojope.hackathon.chabot.facebook.FacebookSender;

@Service
public class ScriptingManager {

	@Autowired
	private FacebookSender facebookSender;
	
	@Autowired
	private ApiCall apiCall;
	
	private String lastUserId;
	private String lastUserFirstName;
	private String lastUserLastName;
	
	public Response getResponse(Request request) {
		String intent = request.getResult().getMetadata().getIntentName();
	 	String userId = request.getOriginalRequest().getData().getSender().getId();
	 	
	 	if(lastUserFirstName == null || lastUserFirstName.isEmpty() || lastUserId != userId) {
		 	lastUserFirstName = facebookSender.getFirstName(userId);
		 	lastUserLastName = facebookSender.getLastName(userId);;
	 	}
	 	lastUserId = userId;
	 	
	 	
	 	if(intent.equalsIgnoreCase("TestMyHook")) {
	 		facebookSender.askReviewMessage(userId, "Ok");
	 	}
	 	if(intent.equalsIgnoreCase("AskingForReviewConfirmation")) {
    		String lastReservation = apiCall.getLastReservation(lastUserFirstName, lastUserLastName);
	 		facebookSender.askReviewMessage(userId, lastReservation);
	 		return null;
	 	}
	 	if(intent.equalsIgnoreCase("NotationGiven")) {
	 		String param = request.getResult().getParameters().get("notation");
	 		int note = Integer.parseInt(param.substring(0,1));
	 		switch(note) {
	 			case 1 : 
	 			case 2 : facebookSender.sendMessage(userId, "I'm so sorry to hear that :'( . Can ou give more details in one message please, I will send it to a manager.");
	 				break;
	 			case 3 :
	 			case 4 : facebookSender.sendMessage(userId, "Ok, Can you briefly explain, in one message, how we can improve to get to a 5 ?");
	 				break;
	 			case 5 : facebookSender.sendMessage(userId, "So glad to hear that :-) ! You can make a comment if you want, I will send it to the team !");
	 				break;
	 		}
	 		Response response = new Response();
			Context cont = response.new Context();
			cont.setLifespan(3);
			cont.setName("AskComment");
			
			List<Context> contexts = new ArrayList<>();
			contexts.add(cont);
			response.setContextOut(contexts);
			return response;
	 	}
    	
    	
//    	Response response = new Response();
//    	response.setSpeech("" +facebookSender.getFirstName(userId));
    	
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

	public String getLastUserLastName() {
		return lastUserLastName;
	}

	public void setLastUserLastName(String lastUserLastName) {
		this.lastUserLastName = lastUserLastName;
	}
	
}
