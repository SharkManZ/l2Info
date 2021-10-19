package ru.shark.home.l2info.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import ru.shark.home.l2info.rest.RaceEndpoint;
import ru.shark.home.l2info.rest.WeaponEndpoint;

@Configuration
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        register(WeaponEndpoint.class);
        register(RaceEndpoint.class);
    }
}
