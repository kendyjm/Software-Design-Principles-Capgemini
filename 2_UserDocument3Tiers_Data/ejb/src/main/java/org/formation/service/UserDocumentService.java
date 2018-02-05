package org.formation.service;

import java.util.Date;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.formation.model.Document;
import org.formation.model.Member;

/**
 * Session Bean implementation class UserDocumentService
 */
@Stateless
@LocalBean
public class UserDocumentService implements UserDocumentServiceRemote, UserDocumentServiceLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserDocumentService() {
		// TODO Auto-generated constructor stub
	}

	public Member authenticate(Member user) throws AuthenticationException {
		// Authenticate users or throw exception, if user does not exist
		return null;
	}
	
	public Member registerUser(Member newMember) throws AlreadyExistException {
		Query q = em.createQuery("from Member m where m.email = :email ");
		q.setParameter("email", newMember.getEmail());
		try {
			q.getSingleResult();
			throw new AlreadyExistException();
		} catch (PersistenceException e) {
			// All rigth no one exist with this email
		}
		newMember.setRegisteredDate(new Date());
		try {
			em.persist(newMember);
		} catch (PersistenceException e) {
			throw new AlreadyExistException(e);
		}
		return newMember;
	}
	
	public void uploadFile(Member user, Document document) {
		// Upload a file and associate it with the member
	}

	/* (non-Javadoc)
	 * @see org.formation.service.UserDocumentServiceIF#getDocuments(org.formation.model.Member)
	 */
	@Override
	public Set<Document> getDocuments(Member member) {
		Query q = em.createQuery("from Member m left join fetch m.documents where m = :member");
		q.setParameter("member", member);
		Member m = (Member)q.getSingleResult();
		return m.getDocuments();
	}
	
	
	
}
