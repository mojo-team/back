package fr.mojope.hackathon.chabot.apiai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.mojope.hackathon.chabot.apiai.jsonwrapper.request.Request;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response;
import fr.mojope.hackathon.chabot.facebook.FacebookSender;


@RestController
@RequestMapping("/request")
public class ApiaiController {

	@Autowired
	private FacebookSender facebookSender;
	
	 @RequestMapping(method = RequestMethod.POST)
	    public Response request(@RequestBody Request request) {
		 	String intent = request.getResult().getMetadata().getIntentName();
		 	String userId = request.getOriginalRequest().getData().getSender().getId();
	    	//System.out.println("Request received from api.ai (intent : " + intent + ")");
	    	if(intent.equalsIgnoreCase("AskReview")) {
	    		facebookSender.sendAskReviewMessage(userId);
	    		return null;
	    	}
	    	
	    	Response response = new Response();
	    	response.setSpeech("Intent : " +intent);
	    	return response;
	    }
}
