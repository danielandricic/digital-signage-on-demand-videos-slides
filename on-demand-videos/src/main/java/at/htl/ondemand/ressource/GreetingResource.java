package at.htl.ondemand.ressource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.logging.Logger;

@Path("/greeting")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class.getSimpleName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }

    /*
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/display/{id}")
    public Response getDisplayById(@Context UriInfo info, @PathParam("id") Integer id0) {
        UriBuilder builder = info
                .getAbsolutePathBuilder();



        return Response
                .ok()
                .build();
    }*/
}