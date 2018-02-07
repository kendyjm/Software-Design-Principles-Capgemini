package org.formation.microservice.userService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.formation.microservice.userService.notification.Email;
import org.formation.microservice.userService.notification.NotificationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;



/**
 * A RESTFul controller for accessing Member information.
 * 
 * @author Paul Chapman
 */
@RestController
public class MembersController {

	protected Logger logger = LoggerFactory.getLogger(MembersController.class);
	protected MemberRepository memberRepository;
	private NotificationClient notificationClient;

	/**
	 * Create an instance plugging in the respository of Members.
	 * 
	 * @param MemberRepository
	 *            An Member repository implementation.
	 */
	@Autowired
	public MembersController(MemberRepository MemberRepository, NotificationClient notificationClient) {
		this.memberRepository = MemberRepository;
		this.notificationClient = notificationClient;

		logger.info("MemberRepository says system has "
				+ MemberRepository.countMembers() + " Members");
	}

	/**
	 * Fetch an Member with the specified Member number.
	 * 
	 * @param MemberNumber
	 *            A numeric, 9 digit Member number.
	 * @return The Member if found.
	 * @throws MemberNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/Members/{memberId}")
	public Member byNumber(@PathVariable("memberId") long memberId) {

		logger.info("Members-service byNumber() invoked: " + memberId);
		Member member = memberRepository.findById(memberId);
		logger.info("Members-service byNumber() found: " + member);

		if (member == null)
			throw new MemberNotFoundException(""+memberId);
		else {
			return member;
		}
	}
	
	@RequestMapping("/displayAll")
	public List<Member> displayAll() {
		logger.info("Members-service displayAll() invoked");
		List<Member> members = memberRepository.findAll();
		return members;
	}

	/**
	 * Fetch Members with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../Members/owner/a</code> will find any
	 * Members with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of Members.
	 * @throws MemberNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/Members/owner/{name}")
	public List<Member> byOwner(@PathVariable("name") String partialName) {
		logger.info("Members-service byOwner() invoked:  {}" , partialName);
		List<Member> members = memberRepository.findByNomContainingIgnoreCase(partialName);
		logger.info("Members-service byOwner() invoked with [{}] found {} members" , partialName, members.size());
		return members;
	}


	@RequestMapping(path = "/authenticate", method = RequestMethod.POST, consumes = "application/json")
	public Member authenticate(@Valid @RequestBody User user) {
		logger.info("Members-service authenticate() invoked with email:{}" , user.getEmail());
		Member member = memberRepository.findFirstByEmailAndPassword(user.getEmail(), user.getPassword());
		logger.info("Members-service authenticate() invoked with email:{} has found member {}" , member);
		return member;
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Member register(@Valid @RequestBody Member pMember) {
		logger.info("Members-service register() invoked with email:{}" , pMember.getEmail());
		
		Member member = memberRepository.findFirstByEmailAndPassword(pMember.getEmail(), pMember.getPassword());
		if(member == null) {
			Email mail = new Email();
			mail.setEmail(pMember.getEmail());
			mail.setContent("Welcome on board!");
			notificationClient.sendSimple(mail);
			
			return memberRepository.save(pMember);
		}
		
		return null;
	}
}
