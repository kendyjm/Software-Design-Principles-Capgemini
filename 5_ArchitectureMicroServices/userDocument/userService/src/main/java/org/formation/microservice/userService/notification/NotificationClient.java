package org.formation.microservice.userService.notification;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * TODO rajouter le fallback ! important en micro services!
 */
@FeignClient(name="notification-service", fallback=NotificationClientFallBack.class)
public interface NotificationClient {

	/*
	 * ce endpoint /sendSimple existant dans le projet notificaiton-service\NotificationController
	 */
	@RequestMapping(method= RequestMethod.POST, value = "/sendSimple", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sendSimple(Email email);
}
