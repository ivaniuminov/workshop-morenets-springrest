package it.discovery.monitoring;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    private Map<String, Object> data = new HashMap<>();

    {
        data.put("Artifact", "Workshop April 2010");
        data.put("Name", "Ivan Iuminov");
        data.put("Topic", "Spring Boot");
        data.put("Version", "1.0");
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetails(data);
    }
}
