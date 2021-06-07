package at.htl.ondemand.model.form;

import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

public class XiboTokenForm {

    @FormParam("client_id")
    @PartType(MediaType.TEXT_PLAIN)
    public String clientId = ConfigProvider.getConfig().getValue("on-demand.xibo.client-id", String.class);

    @FormParam("client_secret")
    @PartType(MediaType.TEXT_PLAIN)
    public String clientSecret = ConfigProvider.getConfig().getValue("on-demand.xibo.client-secret", String.class);

    @FormParam("grant_type")
    @PartType(MediaType.TEXT_PLAIN)
    public String grantType = "client_credentials";

    private XiboTokenForm() {
    }

    public static XiboTokenForm get() {
        return new XiboTokenForm();
    }
}
