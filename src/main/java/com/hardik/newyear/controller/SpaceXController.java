package com.hardik.newyear.controller;

import com.hardik.newyear.client.SpaceXClient;
import com.hardik.newyear.record.SpaceXLaunch;
import com.hardik.newyear.record.SpaceXRocket;
import com.hardik.newyear.service.MailingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

    @GetMapping("/rockets/all")
    public ResponseEntity<List<SpaceXRocket>> getAllRockets() {
        return ResponseEntity.ok(spaceXClient.getAllRockets());
    }

    // ID: 5e9d0d95eda69974db09d1ed
    @GetMapping("/rockets/{id}")
    public ResponseEntity<SpaceXRocket> getRocketById(@PathVariable String id) {
        return ResponseEntity.ok(spaceXClient.getRocketById(id));
    }

    @GetMapping("/latest-launch-template")
    public String mailBody(Model model){

        SpaceXLaunch latest = spaceXClient.getLatestLaunch();
        model.addAttribute("launch", latest);

        return "latest-launch";
    }

    @GetMapping("/notify/latest-launch")
    @ResponseBody
    public String notifyUser() {
        return mailingService.sendLatestLaunchMail();
    }

}
