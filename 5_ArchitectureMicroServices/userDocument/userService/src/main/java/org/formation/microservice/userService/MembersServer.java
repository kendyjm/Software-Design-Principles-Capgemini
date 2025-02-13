package org.formation.microservice.userService;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;


/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link MembersConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients
@Import(MembersConfiguration.class)
public class MembersServer {

	@Autowired
	protected MemberRepository memberRepository;

	protected Logger logger = Logger.getLogger(MembersServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for members-server.properties or
		// members-server.yml
		System.setProperty("spring.config.name", "members-server");

		SpringApplication.run(MembersServer.class, args);
		
	}
}
