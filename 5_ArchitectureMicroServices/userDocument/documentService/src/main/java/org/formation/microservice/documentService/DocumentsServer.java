package org.formation.microservice.documentService;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;


/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link DocumentsConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableEurekaClient
@Import(DocumentsConfiguration.class)
public class DocumentsServer {

	@Autowired
	protected DocumentRepository documentRepository;

	protected Logger logger = Logger.getLogger(DocumentsServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for members-server.properties or
		// members-server.yml
		System.setProperty("spring.config.name", "documents-server");

		SpringApplication.run(DocumentsServer.class, args);
		
	}
}
