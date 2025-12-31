package com.hardik.newyear.client;

import com.hardik.newyear.record.SpaceXLaunch;
import com.hardik.newyear.record.SpaceXRocket;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface SpaceXClient {

    @GetExchange("/v5/launches")
    List<SpaceXLaunch> getAllLaunches();

    @GetExchange("/v5/launches/latest")
    SpaceXLaunch getLatestLaunch();

    @GetExchange("/v4/rockets")
    List<SpaceXRocket> getAllRockets();

    @GetExchange("/v4/rockets/{id}")
    SpaceXRocket getRocketById(String id);
}