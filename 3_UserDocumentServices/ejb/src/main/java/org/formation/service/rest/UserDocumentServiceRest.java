package org.formation.service.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.formation.model.Member;
import org.formation.service.UserDocumentServiceLocal;

import io.swagger.annotations.Api;

/*
 * Accessible ici http://localhost:8080/userDocument-ejb-endpoint/rest/docs
 */
@Path("/userDocs")
@Api(value = "/userDocs", description="User document service")
public class UserDocumentServiceRest {

	@EJB
	UserDocumentServiceLocal userDocumentService;
	
	@POST
	@Produces("APPLICATION/JSON")
    public Response registerMember(Member member) {
		
		Response.ResponseBuilder builder = null;
        
        try {
        	member = userDocumentService.registerUser(member);
            // Create an "ok" response with the persisted contact
            builder = Response.ok(member);
        } catch (Exception e) {
            // Handle generic exceptions
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }

        return builder.build();
	}
	
    @GET
	public String getHome() {
		return "<html><body><h1>Hello you really Hit me</h1></body></html>";
	}
}
