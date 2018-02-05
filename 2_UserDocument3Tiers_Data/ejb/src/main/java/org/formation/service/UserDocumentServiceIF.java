package org.formation.service;

import java.util.Set;

import org.formation.model.Document;
import org.formation.model.Member;

public interface UserDocumentServiceIF {

	/**
	 * Authenticate user or throw Exception
	 * @param user
	 * @return
	 * @throws AuthenticationException
	 */
	public Member authenticate(Member user) throws AuthenticationException;
	/**
	 * Register a new user in data base.
	 * @param newUser
	 * @return
	 * @throws AlreadyExistException
	 */
	public Member registerUser(Member newUser) throws AlreadyExistException;
	
	/**
	 * Get all the documents of a particular user.
	 * @param user
	 * @return
	 */
	public Set<Document> getDocuments(Member user);
	/**
	 * Add a document in the repository of the user
	 * @param user
	 * @param document
	 */
	public void uploadFile(Member user, Document document);
}
