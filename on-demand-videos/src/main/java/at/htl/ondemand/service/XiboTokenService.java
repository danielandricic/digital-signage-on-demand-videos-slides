package at.htl.ondemand.service;

import at.htl.ondemand.model.form.XiboTokenBody;
import at.htl.ondemand.model.xibo.XiboToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
public class XiboTokenService {

    /*
        Hier wird der Access Token den man für den Zugang
        in das Xibo-System braucht,
        in einen STring umgewandelt.
        Davor wird er aus der application.properties mit der
        Annotation @ConfigProperty(name="webclient.xibo.client-secret-id/key")
        ausgelesen.
     */

    @Inject
    @RestClient
    XiboAPIClient client;

    private String token;
    private LocalDateTime expirationDate = LocalDateTime.MIN;

    public String getToken() {
        if(this.expirationDate.isBefore(LocalDateTime.now())) {
            XiboToken xiboToken = this.client.getAccessToken(new XiboTokenBody());
            this.token = xiboToken.access_token;
            this.expirationDate = LocalDateTime.now().plusSeconds(xiboToken.expires_in);
        }

        return this.token;
    }

}
