package at.htl.ondemand.service;

import at.htl.ondemand.model.xibo.DisplayGroup;
import at.htl.ondemand.model.xibo.Layout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class XiboAPIService {

    @Inject
    @RestClient
    XiboAPIClient client;

    @Inject
    Logger logger;

    @Inject
    TagsService tagsService;

    public List<Layout> getReadableLayouts() {
        String readableTags = this.tagsService.getReadableTags();
        return this.client.getAllLayouts(readableTags, "regions,playlists,widgets", 0);
    }

    public Layout getReadableLayoutById(Long layoutId) {
        String readableTags = this.tagsService.getReadableTags();
        List<Layout> layouts = this.client.getLayoutById(layoutId, readableTags, "regions,playlists,widget", 0);

        if(layouts.size() != 1) return null;
        Layout childLayout = layouts.get(0);

        Layout parentLayout;
        if(childLayout.publishedStatusId == 1) {
            parentLayout = this.client.checkoutLayoutById(childLayout.layoutId);
        }
        else {
            parentLayout = this.client
                    .getLayoutByParentId(childLayout.layoutId, "regions,playlists,widgets", 0)
                    .get(0);
        }

        List<DisplayGroup> displayGroups = this.client.getDisplayGroupByTag(parentLayout.layout);

        if(displayGroups.isEmpty()) {
            logger.warning("Warning: No Displays found!");
        }
        else if(displayGroups.size() != 1) {
            logger.warning("Warning: More than one Display, has the same tag!");
        }
        else {
            DisplayGroup displayGroup = displayGroups.get(0);
            parentLayout.displays = this.client.getDisplaysByDisplaygroupId(displayGroup.displayGroupId);
        }

        return parentLayout;
    }

}
