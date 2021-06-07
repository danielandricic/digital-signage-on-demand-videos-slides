package at.htl.ondemand.interceptor;

import at.htl.ondemand.service.XiboTokenService;

import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class XiboAuthenticationFeature implements DynamicFeature {

    @Inject
    XiboTokenService xiboTokenService;

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        XiboAuthentication authentication = resourceInfo.getResourceMethod().getAnnotation(XiboAuthentication.class);
        if (authentication == null) {
            return;
        }

        XiboAuthenticationFilter filter = new XiboAuthenticationFilter();
        context.register(filter);
    }


    private class XiboAuthenticationFilter implements ClientRequestFilter {

        @Override
        public void filter(ClientRequestContext requestContext) {
            String token = XiboAuthenticationFeature.this.xiboTokenService.getToken();
            requestContext.getHeaders().putSingle("Authorization", "Bearer " + token);
        }
    }
}
