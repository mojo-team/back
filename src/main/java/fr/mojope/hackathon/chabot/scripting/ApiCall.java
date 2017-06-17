package fr.mojope.hackathon.chabot.scripting;

import org.springframework.stereotype.Service;

@Service
public class ApiCall {

	public String getLastReservation(String firstName, String lastName) {
		return "Arc de Triomphe";
	}
	
	public boolean userActuallyInMeetingRoom(String firstName, String lastName) {
		return true;
	}
	
}
