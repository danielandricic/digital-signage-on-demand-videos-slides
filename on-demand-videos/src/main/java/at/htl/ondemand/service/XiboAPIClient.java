package at.htl.ondemand.service;

import at.htl.ondemand.model.xibo.Display;
import at.htl.ondemand.model.xibo.Layout;
import at.htl.ondemand.model.xibo.DisplayGroup;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.List;

@RegisterRestClient
public interface XiboAPIClient {

    @GET
    @Path("/displayGroup")
    List<DisplayGroup> getDisplayGroupByTag(@QueryParam("tags") String tag);

    @GET
    @Path("/layout")
    List<Layout> getAllLayouts(
            @QueryParam("tags") String tags,
            @QueryParam("embed") String embed,
            @QueryParam("retired") Integer retired
    );

    @GET
    @Path("layout")
    List<Layout> getLayoutById(
            @QueryParam("layoutId") Long layoutId,
            @QueryParam("tags") String tags,
            @QueryParam("embed") String embed,
            @QueryParam("retired") Integer retired
    );

    @PUT
    @Path("/layout/checkout/{layoutId}")
    Layout checkoutLayoutById(
            @PathParam("layoutId") Long layoutId
    );

    @GET
    @Path("/layout")
    List<Layout> getLayoutByParentId(
            @QueryParam("parentId") Long parentId,
            @QueryParam("embed") String embed,
            @QueryParam("retired") Integer retired
    );

    @GET
    @Path("/display")
    List<Display> getDisplaysByDisplaygroupId(
            @QueryParam("displayGroupId") Long displayGroupId
    );
}