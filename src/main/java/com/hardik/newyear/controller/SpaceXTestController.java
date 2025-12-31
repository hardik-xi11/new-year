package com.hardik.newyear.controller;

import com.hardik.newyear.record.SpaceXLaunch;
import com.hardik.newyear.record.SpaceXRocket;
import com.hardik.newyear.service.SpaceXService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test/spacex")
public class SpaceXTestController {

    private final SpaceXService spaceXService;

    public SpaceXTestController(SpaceXService spaceXService) {
        this.spaceXService = spaceXService;
    }

    @GetMapping("/launches")
    public List<SpaceXLaunch> testLaunches() {
        return spaceXService.findAllLaunches();
    }

    @GetMapping("/latest")
    public SpaceXLaunch testLatest() {
        return spaceXService.findLatestLaunch();
    }

    @GetMapping("/rockets")
    public List<SpaceXRocket> testRockets() {
        return spaceXService.findAllRockets();
    }
}