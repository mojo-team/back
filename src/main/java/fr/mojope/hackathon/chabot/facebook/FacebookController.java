package fr.mojope.hackathon.chabot.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.mojope.hackathon.chabot.apiai.jsonwrapper.request.Request;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response;
import fr.mojope.hackathon.chabot.scripting.ScriptingManager;

@RestController
public class FacebookController {
	
	private boolean firstTimeIncident = true;
	
	@Autowired
	private ScriptingManager scriptingManager;
	
	@Autowired
	private FacebookSender facebookSender;
	
	@RequestMapping("/pushincident")
	public String requestIncident() {
		if(firstTimeIncident) {
			firstTimeIncident = false;
			facebookSender.sendMessage(scriptingManager.getLastUserId(), String.format("Don't worry %s, Robert is here for you. First, I need to know what's wrong : you can tell me by picking one of the common reasons, or you can directly write your problem.", scriptingManager.getLastUserFirstName()));
			facebookSender.sendMessageQuickReplies(scriptingManager.getLastUserId(), "I can also see that you are currently in a meeting room. I'm adding it to the list.", "Actual room", "Precedent room", "Room reservation", "Other service", "Billing", "Sodexo card");
		}else {
			facebookSender.sendMessageQuickReplies(scriptingManager.getLastUserId(), String.format("Don't worry %s, Robert is here for you. First, I need to know what's wrong : you can tell me by picking one of the common reasons, or you can directly write your problem.", scriptingManager.getLastUserFirstName()), "Precedent room", "Room reservation", "Other service", "Billing", "Sodexo card");
		}
		return "";
	}
	
	@RequestMapping("/pushavis")
	public String requestAvis() {
		facebookSender.askForReview(scriptingManager.getLastUserId(), scriptingManager.getLastUserFirstName());
		return "";
	}
	
	@RequestMapping("/pushafterwork")
	public String requestAfterWork() {
		return facebookSender.askForReview(scriptingManager.getLastUserId(), scriptingManager.getLastUserFirstName());
	}
}
