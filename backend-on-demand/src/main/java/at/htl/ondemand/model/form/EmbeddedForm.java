package at.htl.ondemand.model.form;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

public class EmbeddedForm {

    @FormParam("embedHtml")
    @PartType(MediaType.TEXT_PLAIN)
    public String html;

    @FormParam("duration")
    @PartType(MediaType.TEXT_PLAIN)
    public String duration;

    @FormParam("useDuration")
    @PartType(MediaType.TEXT_PLAIN)
    public String useDuration = "1";

    @FormParam("transparency")
    @PartType(MediaType.TEXT_PLAIN)
    public String transparency = "1";

    public EmbeddedForm(String html, String duration) {
        this.html = html;
        this.duration = duration;
    }
}
