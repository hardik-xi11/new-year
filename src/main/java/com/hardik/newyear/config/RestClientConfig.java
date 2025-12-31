package com.hardik.newyear.config;

import com.hardik.newyear.client.SpaceXClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

// TODO: Replace the Bean with @ImportHttpService (and pass in the interface as an argument) annotation with Spring 7
@Configuration
public class RestClientConfig {

    @Bean
    public SpaceXClient spaceXClient(RestClient.Builder restClient) {
        RestClient client = restClient
                .baseUrl("https://api.spacexdata.com")
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(SpaceXClient.class);
    }
}
