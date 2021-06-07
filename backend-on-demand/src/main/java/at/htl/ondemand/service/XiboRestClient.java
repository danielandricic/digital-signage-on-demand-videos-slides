package at.htl.ondemand.service;

import at.htl.ondemand.interceptor.XiboAuthentication;
import at.htl.ondemand.model.form.EmbeddedForm;
import at.htl.ondemand.model.form.LayoutForm;
import at.htl.ondemand.model.form.ScheduleForm;
import at.htl.ondemand.model.form.XiboTokenForm;
import at.htl.ondemand.model.xibo.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
public interface XiboRestClient {

    @POST
    @Path("authorize/access_token")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    XiboToken getAccessToken(@MultipartForm XiboTokenForm data);

    @GET
    @Path("display")
    @XiboAuthentication
    List<Display> getDisplays(@QueryParam("tags") String tags);

    @GET
    @Path("library")
    @XiboAuthentication
    List<Media> searchLibrary(@QueryParam("type") String type, @QueryParam("tags") String tags);


    @GET
    @Path("layout")
    @XiboAuthentication
    List<Layout> getChildLayout(@QueryParam("parentId") Long parentId, @QueryParam("embed") String embed);

    @POST
    @Path("layout")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @XiboAuthentication
    Layout createLayout(@MultipartForm LayoutForm data);

    @DELETE
    @Path("layout/{layoutId}")
    @XiboAuthentication
    void deleteLayout(@PathParam("layoutId") Long LayoutId);

    @PUT
    @Path("layout/publish/{layoutId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @XiboAuthentication
    void publishLayout(@PathParam("layoutId") Long layoutId, @FormParam("publishNow") int publishNow);

    @POST
    @Path("schedule")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @XiboAuthentication
    void scheduleOverlay(@MultipartForm ScheduleForm data);

    @POST
    @Path("playlist/widget/{type}/{playlistId}")
    @XiboAuthentication
    Widget createWidget(@PathParam("type") String type, @PathParam("playlistId") Long playlistId);

    @PUT
    @Path("playlist/widget/{widgetId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @XiboAuthentication
    void updateEmbedded(@PathParam("widgetId") Long widgetId, @Form EmbeddedForm data);
}
