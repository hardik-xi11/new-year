package com.hardik.newyear.controller;

import com.hardik.newyear.client.SpaceXClient;
import com.hardik.newyear.record.SpaceXLaunch;
import com.hardik.newyear.record.SpaceXRocket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpaceXController {

    private final SpaceXClient spaceXClient;

    public SpaceXController(SpaceXClient spaceXClient) {
        this.spaceXClient = spaceXClient;
    }

    @GetMapping("/launches/all")
    public ResponseEntity<List<SpaceXLaunch>> getAllLaunches() {
        return ResponseEntity.ok(spaceXClient.getAllLaunches());
    }

    @GetMapping("/launches/latest")
    public ResponseEntity<SpaceXLaunch> getLatestLaunch() {
        return ResponseEntity.ok(spaceXClient.getLatestLaunch());
    }

    @GetMapping("/rockets/all")
    public ResponseEntity<List<SpaceXRocket>> getAllRockets() {
        return ResponseEntity.ok(spaceXClient.getAllRockets());
    }

    // ID: 5e9d0d95eda69974db09d1ed
    @GetMapping("/rockets/{id}")
    public ResponseEntity<SpaceXRocket> getRocketById(@PathVariable String id) {
        return ResponseEntity.ok(spaceXClient.getRocketById(id));
    }

}
