package org.formation.microservice.documentService;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository for Member data implemented using Spring Data JPA.
 * 
 * @author David THIBAU from Paul Chapman
 */
public interface DocumentRepository extends Repository<Document, Long> {

	/**
	 * Find an Document with the id.
	 * 
	 * @param id
	 * @return
	 */
	public Document findById(long id);

	/**
	 * Find Documents by their owner
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	//
	// A COMPLETER
	//
	
	/**
	 * Find All Documents 
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	public List<Document> findAll();
	/**
	 * Fetch the number of Members known to the system.
	 * 
	 * @return The number of Members.
	 */
	@Query("SELECT count(*) from Document")
	public int countDocuments();
}
