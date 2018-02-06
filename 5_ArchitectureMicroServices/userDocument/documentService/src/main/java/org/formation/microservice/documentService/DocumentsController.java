package org.formation.microservice.documentService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * A RESTFul controller for accessing Document information.
 * 
 * @author David Thibau from Paul Chapman
 */
@RestController
public class DocumentsController {

	protected Logger logger = Logger.getLogger(DocumentsController.class
			.getName());
	protected DocumentRepository documentRepository;

	/**
	 * @param documentRepository
	 */
	@Autowired
	public DocumentsController(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;

		logger.info("DocumentRepository says system has "
				+ documentRepository.countDocuments() + " Documents");
	}



	/**
	 * @param owner
	 * @return
	 */
	@RequestMapping("/Documents/owner/{name}/")
	public List<Document> getDocuments(@PathVariable("name") String owner) {
		
		// A COMPLETER
		return new ArrayList<Document>();
		
	}
	
	@RequestMapping("/Documents")
	public List<Document> getAllDocuments() {
		logger.info("Documents-service getAll() invoked: ");

		List<Document> documents = documentRepository
				.findAll();
		logger.info("Documents-service byOwner() found: " + documents );

		return documents;
		
	}


}
