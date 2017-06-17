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
	
	private boolean neverChat = true;
	
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
	 			case 1 : facebookSender.sendMessage(userId, "I'm so sorry to hear that :'( . Can you give me, in one message, the reasons behind your disappointment ? I will send those to a manager in order to improve our services.");
	 				break;
	 			case 2 : facebookSender.sendMessage(userId, "I'm so sorry to hear that :'( . Can you give me, in one message, the reasons behind your disappointment ? I will send those to a manager in order to improve our services.");
	 				break;
	 			case 3 :facebookSender.sendMessage(userId, "Ok. Can you briefly explain to me, in one message, how we can improve to get to a 5 ?");
 					break;
	 			case 4 : facebookSender.sendMessage(userId, "Ok great. Can you briefly explain to me, in one message, how we can improve to get to a 5 ?");
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
    			if(apiCall.userActuallyInMeetingRoom(lastUserFirstName, lastUserLastName)) {
    				facebookSender.sendMessageQuickReplies(userId, "Oh man, we're so sorry :( ! Can you tell me what's wrong about the room ? We will take care of it ASAP !", "Not clean", "Missing equipment", "Too much noise", "Not safe", "Can't access it");
        			Response response = new Response();
        			Context cont = response.new Context();
        			cont.setLifespan(3);
        			cont.setName("ActualRoom");
        			
        			List<Context> contexts = new ArrayList<>();
        			contexts.add(cont);
        			response.setContextOut(contexts);
        			return response;
    			}else{
    				facebookSender.sendMessage(userId, "Sorry, but I don't think you're in a meeting room right now.");
    				return null;
    			}
    			
    		}else if(param.equalsIgnoreCase("Precedent room")) {
    			//facebookSender.sendMessage(userId, "Sorry, I don't find any older reservation at your name.");
    			facebookSender.sendMessageQuickReplies(userId, "I'm sorry to hear that :( Can you tell me what was the problem with the room ? We will send someone to fix it.", "Not clean", "Missing equipment", "Too much noise", "Not safe", "Can't access it");
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
    		facebookSender.sendMessage(userId, "Ok. Don't worry, I'm sending someone right away. He/She will arrive in no time.");
    		return null;
    	}
    	if(intent.equalsIgnoreCase("PrecedentRoomProblem")) {
    		facebookSender.sendMessage(userId, "Ok. I will send someone to check the room, and a manager will call you ASAP to discuss you about refund.");
    		return null;
    	}
    	
    	if(intent.equalsIgnoreCase("Chatting")) {
    		if(neverChat) {
    			neverChat = false;
    			facebookSender.sendMessage(userId, "Hey, nice to meet you mate, I'm Robert, your company's Spotter manager ;-)");
    			facebookSender.sendMessage(userId, "I'm here to help you in your daily activities. I can collect your feedbacks about our services, and reach the right person for you if you have a problem, at any time.");
    			facebookSender.sendMessage(userId, "But I can also chat with you, in order to get to know you better !");
    		}
    		
    		
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
