package at.htl.ondemand.service;

import at.htl.ondemand.model.form.XiboTokenForm;
import at.htl.ondemand.model.xibo.XiboToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
public class XiboTokenService {

    @Inject
    @RestClient
    XiboRestClient xiboRestClient;

    @Inject
    Logger log;

    private String token;
    private LocalDateTime expirationDate = LocalDateTime.MIN;

    public String getToken() {
        if (this.expirationDate.isBefore(LocalDateTime.now())) {
            XiboToken xiboToken = this.xiboRestClient.getAccessToken(XiboTokenForm.get());
            this.token = xiboToken.access_token;
            this.log.infov("Fetched new token [{0}]", this.token);
            this.expirationDate = LocalDateTime.now().plusSeconds(xiboToken.expires_in);
        }

        return this.token;
    }
}
