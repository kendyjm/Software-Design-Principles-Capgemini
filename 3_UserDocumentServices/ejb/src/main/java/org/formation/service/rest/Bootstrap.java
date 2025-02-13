package org.formation.service.rest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import io.swagger.models.Contact;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.models.auth.OAuth2Definition;

public class Bootstrap extends HttpServlet {

  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
            .title("Swagger Petstore")
            .description("This is a sample server Petstore server.  You can find out more about Swagger " +
                    "at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, " +
                    "you can use the api key `special-key` to test the authorization filters.")
            .termsOfService("http://swagger.io/terms/")
            .contact(new Contact()
                    .email("apiteam@swagger.io"))
            .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

    ServletContext context = config.getServletContext();
    Swagger swagger = new Swagger()
            .info(info);
    swagger.securityDefinition("petstore_auth",
            new OAuth2Definition()
                    .implicit("http://localhost:8002/oauth/dialog")
                    .scope("email", "Access to your email address")
                    .scope("pets", "Access to your pets"));
    swagger.tag(new Tag()
            .name("userDocs")
            .description("Everything about your UserDocs")
            .externalDocs(new ExternalDocs("Find out more", "http://swagger.io")));
    context.setAttribute("swagger", swagger);
    /* or use new mechanism @since 1.5.7 */
    //new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}
