package org.formation.microservice.userService.notification;

import org.springframework.stereotype.Service;

/*
 * @Component serait suffisant
 */
@Service
public class NotificationClientFallBack implements NotificationClient {

	public String sendSimple(Email email) {
		return "OK";
	}
}
