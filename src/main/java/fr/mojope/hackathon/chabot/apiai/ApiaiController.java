package fr.mojope.hackathon.chabot.apiai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.mojope.hackathon.chabot.apiai.jsonwrapper.request.Request;
import fr.mojope.hackathon.chabot.apiai.jsonwrapper.response.Response;
import fr.mojope.hackathon.chabot.scripting.ScriptingManager;


@RestController
@RequestMapping("/request")
public class ApiaiController {
	
	@Autowired
	private ScriptingManager scriptingManager;
	
	@RequestMapping(method = RequestMethod.POST)
	public Response request(@RequestBody Request request) {
		 	
	   	return scriptingManager.getResponse(request);
	}
}
