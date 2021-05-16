package at.htl.ondemand.service;

import at.htl.ondemand.model.WebclientAccess;
import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.SecurityIdentity;
import org.jose4j.jwt.MalformedClaimException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import java.security.Principal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class TagsService {

    @Inject
    SecurityIdentity securityIdentity;

    private final Jsonb jsonb;

    public TagsService() {
        this.jsonb = JsonbBuilder.create();
    }

    public String getReadableTags() {
        WebclientAccess webclientAccess = this.getWebclientAccess();
        return Stream.concat(webclientAccess.write.stream(), webclientAccess.read.stream())
                .collect(Collectors.joining(","));
    }

    private WebclientAccess getWebclientAccess() {
        Principal principal = this.securityIdentity.getPrincipal();
        if(principal instanceof OidcJwtCallerPrincipal) {
            OidcJwtCallerPrincipal oidcJwtCallerPrincipal = (OidcJwtCallerPrincipal) principal;

            try {
                String webclientAccessValue = oidcJwtCallerPrincipal.getClaims()
                    .getClaimValue("webclient_access", String.class);

                return this.jsonb.fromJson(webclientAccessValue, WebclientAccess.class);
            } catch(MalformedClaimException | JsonbException e) {
                e.printStackTrace();
            }
        }

        return new WebclientAccess();
    }

    public String getWriteableTags() {
        WebclientAccess webclientAccess = this.getWebclientAccess();
        return String.join(",", webclientAccess.write);
    }
}
