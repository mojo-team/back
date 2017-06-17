package fr.mojope.hackathon.chabot.scripting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.mojope.hackathon.chabot.apiai.jsonwrapper.request.Request;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response.Context;
import fr.mojope.hackathon.chabot.facebook.FacebookSender;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.Element;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.Element.Button;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponse;
import fr.mojope.hackathon.chabot.facebook.jsonwrapper.FacebookResponseButton;

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
	 		facebookSender.sendMessage(userId, "Ok");
	 		return null;
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
    	if(intent.equalsIgnoreCase("NotationCommentGiven")) {
    		facebookSender.sendMeBackMessage(userId);
    		return null;
    	}
    	if(intent.equalsIgnoreCase("IncidentTypeGiven")) {
    		String param = request.getResult().getParameters().get("ProblemType");
    		
    		if(param.equalsIgnoreCase("actual room")) {
    			facebookSender.sendMessageQuickReplies(userId, "Oh man, we're so sorry :( ! Can you tell me what's wrong about the room ? We will take care of it ASAP !", "Not clean", "Missing equipment", "Too much noise", "Not safe", "Can't access it");
    			Response response = new Response();
    			Context cont = response.new Context();
    			cont.setLifespan(3);
    			cont.setName("ActualRoom");
    			
    			List<Context> contexts = new ArrayList<>();
    			contexts.add(cont);
    			response.setContextOut(contexts);
    			return response;
    		}else if(param.equalsIgnoreCase("Precedent room")) {
    			facebookSender.sendMessageQuickReplies(userId, "I'm sorry to hear that :( Can you tell me what was the problem with the room ? We will try to fix that ASAP.", "Not clean", "Missing equipment", "Too much noise", "Not safe", "Can't access it");
    			Response response = new Response();
    			Context cont = response.new Context();
    			cont.setLifespan(3);
    			cont.setName("PrecedentRoom");
    			
    			List<Context> contexts = new ArrayList<>();
    			contexts.add(cont);
    			response.setContextOut(contexts);
    			return response;
    		}else{
    			facebookSender.sendMessage(userId, "Ok, I'm transmitting your request to the right person, he will call you ASAP. Deeply sorry for the inconvenience :-/");
    		}
    		return null;
    	}
    	if(intent.equalsIgnoreCase("ActualRoomProblem")) {
    		facebookSender.sendMessage(userId, "Ok. Don't worry, we are sending someone right away. He/She will arrive in no time.");
    		return null;
    	}
    	if(intent.equalsIgnoreCase("PrecedentRoomProblem")) {
    		facebookSender.sendMessage(userId, "Ok. I will send someone to check the room, and a manager will call you ASAP for refunding you.");
    		return null;
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
