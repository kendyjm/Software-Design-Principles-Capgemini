package org.formation.service;

import java.util.Date;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.formation.model.Document;
import org.formation.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session Bean implementation class UserDocumentService
 * 
 * @WebService est suffisant pour avoir le wsdl ici:
 * http://localhost:8080/userDocument-ejb-endpoint/UserDocumentService?wsdl
 */
@Stateless
@LocalBean
@WebService
public class UserDocumentService implements UserDocumentServiceRemote, UserDocumentServiceLocal {

	private static Logger logger = LoggerFactory.getLogger(UserDocumentService.class);
	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserDocumentService() {
		// TODO Auto-generated constructor stub
	}

	public Member authenticate(Member user) throws AuthenticationException {
		logger.info("authenticate member {}", user);
		
		Query q = em.createQuery("from Member m where m.email = :email and m.password = :password");
		q.setParameter("email", user.getEmail());
		q.setParameter("password", user.getPassword());
		Member ret = null;
		try {
			ret = (Member)q.getSingleResult();
			
			// fix lazy loading exception, quick & dirty:
			// si on veut faire mieux voir plutôt du côté de @XmlTransient 
			ret.setDocuments(getDocuments(ret));
		} catch (PersistenceException e) {
			throw new AuthenticationException(e);
		}
		
		logger.info("authenticated member {}", user);
		return ret;
	}
	
	/**
	 * implicitement il y a un @WebMethod ici grâce au @WebService sur la class
	 */
	public Member registerUser(Member newMember) throws AlreadyExistException {
		logger.info("register member {}", newMember);
		
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
		
		logger.info("registered member {}", newMember);
		
		return newMember;
	}
	
	/*
	 * je veux exclure cette méthode, donc j'utilise l'annotation @WebMethod avec exclude=true
	 * (non-Javadoc)
	 * @see org.formation.service.UserDocumentServiceIF#uploadFile(org.formation.model.Member, org.formation.model.Document)
	 */
	@WebMethod(exclude=true)
	public void uploadFile(Member user, Document document) {
		user = em.find(Member.class, user.getId());
		user.addDocument(document);
	}

	@Override
	@WebMethod(exclude=true)
	public Set<Document> getDocuments(Member member) {
		Query q = em.createQuery("from Member m left join fetch m.documents where m = :member");
		q.setParameter("member", member);
		Member m = (Member)q.getSingleResult();
		return m.getDocuments();
	}
	
	
	
}
