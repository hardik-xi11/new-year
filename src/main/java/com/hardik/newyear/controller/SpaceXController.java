package com.hardik.newyear.controller;

import com.hardik.newyear.client.SpaceXClient;
import com.hardik.newyear.record.*;
import com.hardik.newyear.service.MailingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dash")
public class SpaceXController {

    private final SpaceXClient spaceXClient;
    private final MailingService mailingService;


    public SpaceXController(SpaceXClient spaceXClient, MailingService mailingService) {
        this.spaceXClient = spaceXClient;
        this.mailingService = mailingService;
    }

    @GetMapping("/launches/all")
    public ResponseEntity<List<SpaceXLaunch>> getAllLaunches() {
        return ResponseEntity.ok(spaceXClient.getAllLaunches());
    }

    @GetMapping("/launches/latest")
    public ResponseEntity<SpaceXLaunch> getLatestLaunch() {
        return ResponseEntity.ok(spaceXClient.getLatestLaunch());
    }

    @GetMapping("/upcoming-launches")
    public ResponseEntity<SpacexQueryResult<SpaceXLaunch>> getUpcomingMissions() {
        SpacexQueryRequest request = new SpacexQueryRequest(
                Map.of("upcoming", true),
                new SpacexQueryRequest.QueryOptions(
                        5, // Limit to 5
                        1, // Page 1
                        Map.of("flight_number", 1), // Sort Ascending
                        List.of("rocket") // POPULATE: Replace Rocket ID with full Rocket Object
                )
        );
        return ResponseEntity.ok(spaceXClient.queryLaunches(request));
    }

    @GetMapping("/latest-launch-template")
    public String mailBody(Model model){

        SpaceXLaunch latest = spaceXClient.getLatestLaunch();
        model.addAttribute("launch", latest);

        return "latest-launch";
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

    @GetMapping("/notify/latest-launch")
    @ResponseBody
    public String notifyUser() {
        return mailingService.sendLatestLaunchMail();
    }

    @GetMapping("/fleet")
    public ResponseEntity<List<SpaceXShip>> getActiveFleet() {
        return ResponseEntity.ok(spaceXClient.getShips(Map.of("active", true)));
    }

    @GetMapping("/starship-status")
    public ResponseEntity<List<SpaceXRocket>> getStarshipStatus() {
        // Query param style: /v4/rockets?name=Starship
        return ResponseEntity.ok(spaceXClient.getAllRockets().stream()
                .filter(r -> r.name().contains("Starship"))
                .toList());
    }

}