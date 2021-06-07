package at.htl.ondemand.model.form;

import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

public class ScheduleForm {

    @FormParam("eventTypeId")
    @PartType(MediaType.TEXT_PLAIN)
    public String eventTypeId = "3";

    @FormParam("campaignId")
    @PartType(MediaType.TEXT_PLAIN)
    public String campaignId;

    @FormParam("isPriority")
    @PartType(MediaType.TEXT_PLAIN)
    public String priority = ConfigProvider.getConfig().getOptionalValue("on-demand.priority", String.class).orElse("0");

    @FormParam("displayGroupIds[]")
    @PartType(MediaType.TEXT_PLAIN)
    public String displayGroupId;

    @FormParam("dayPartId")
    @PartType(MediaType.TEXT_PLAIN)
    public String dayPartId = "2";

    public ScheduleForm(String campaignId, String displayGroupId) {
        this.campaignId = campaignId;
        this.displayGroupId = displayGroupId;
    }
}
