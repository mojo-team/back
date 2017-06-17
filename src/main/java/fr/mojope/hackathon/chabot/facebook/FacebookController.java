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
@RequestMapping("/push")
public class FacebookController {
	
	@Autowired
	private ScriptingManager scriptingManager;
	
	@Autowired
	private FacebookSender facebookSender;
	
	@RequestMapping("/incident")
	public void requestIncident() {
		
	}
	
	@RequestMapping("/avis")
	public String requestAvis() {
		return facebookSender.askForReview(scriptingManager.getLastUserId(), scriptingManager.getLastUserFirstName());
	}
}