package fr.mojope.hackathon.chabot.apiai;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.mojope.hackathon.chabot.apiai.jsonwrapper.request.Request;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response;


@RestController
@RequestMapping("/request")
public class ApiaiController {

	 @RequestMapping(method = RequestMethod.POST)
	    public Response request(@RequestBody Request request) {
	    	System.out.println("Request received from api.ai (intent : " + request.getResult().getMetadata().getIntentName() + ")");
	    	Response response = new Response();
	    	response.setDisplayText("DisplayText");
	    	response.setSpeech("Speech");
	    	return response;
	    }
}
