package at.htl.ondemand.ressource;

import at.htl.ondemand.model.form.LoginForm;
import at.htl.ondemand.service.AuthenticationService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.resource.spi.ConfigProperty;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationRessource {

    /*
    Wird zwar noch nicht benötigt, aber wird später für die Keycloak
    Authentifizierung benötigt.
    */

    @Inject
    @RestClient
    AuthenticationService authenticationService;

    @POST
    @Path("/login")
    public Object login(LoginForm loginForm) {
        return new Object();
    }
}
