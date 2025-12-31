package com.hardik.newyear.service;

import com.hardik.newyear.record.SpaceXLaunch;
import com.hardik.newyear.record.SpaceXRocket;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class SpaceXService {

    private final RestClient restClient;

    public SpaceXService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.spacexdata.com")
                .defaultHeader("User-Agent", "Spring-Boot-App")
                .build();
    }

    public List<SpaceXLaunch> findAllLaunches() {
        return restClient.get()
                .uri("/v5/launches")
                .retrieve()
                .body(new ParameterizedTypeReference<List<SpaceXLaunch>>() {});
    }

    public SpaceXLaunch findLatestLaunch() {
        return restClient.get()
                .uri("/v5/launches/latest")
                .retrieve()
                .body(SpaceXLaunch.class);
    }

    public List<SpaceXRocket> findAllRockets() {
        return restClient.get()
                .uri("/v4/rockets")
                .retrieve()
                .body(new ParameterizedTypeReference<List<SpaceXRocket>>() {});
    }

    public void clearApiCache(String apiKey) {
        restClient.delete()
                .uri("/admin/cache")
                .header("spacex-key", apiKey)
                .retrieve()
                .toBodilessEntity();
    }
}