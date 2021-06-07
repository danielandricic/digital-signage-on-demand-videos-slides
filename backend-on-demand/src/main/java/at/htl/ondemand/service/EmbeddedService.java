package at.htl.ondemand.service;


import at.htl.ondemand.model.form.EmbeddedForm;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmbeddedService {

    private String htmlTemplate;

    @Inject
    Logger log;

    @ConfigProperty(name = "on-demand.api-url")
    String apiUrl;

    @PostConstruct
    public void init() {
        InputStream resourceStream = this.getClass().getResourceAsStream("/on-demand.html");
        this.htmlTemplate = new BufferedReader(new InputStreamReader(resourceStream))
                .lines()
                .collect(Collectors.joining("\n"));

        this.log.infov("Template html read from resources [{0}]", this.htmlTemplate);
    }

    public EmbeddedForm generateEmbeddedHtml(Long videoId, String duration, String uuid) {
        String html = this.htmlTemplate
                .replace("{{media-id}}", String.valueOf(videoId))
                .replace("{{url}}", this.apiUrl + "/" + uuid);
        return new EmbeddedForm(html, duration);
    }
}
