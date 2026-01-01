package com.hardik.newyear.client;

import com.hardik.newyear.record.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

@HttpExchange(url="https://api.spacexdata.com", accept="application/json")
public interface SpaceXClient {

    @GetExchange("/v5/launches")
    List<SpaceXLaunch> getAllLaunches();

    @GetExchange("/v5/launches/latest")
    SpaceXLaunch getLatestLaunch();

    @GetExchange("/v4/ships")
    List<SpaceXShip> getShips(@RequestParam Map<String, Object> params);

    @GetExchange("/v4/rockets")
    List<SpaceXRocket> getAllRockets();

    @GetExchange("/v4/rockets/{id}")
    SpaceXRocket getRocketById(@PathVariable("id") String id);

    @PostExchange("/v5/launches/query")
    SpacexQueryResult<SpaceXLaunch> queryLaunches(@RequestBody SpacexQueryRequest request);
}