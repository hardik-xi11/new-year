package com.hardik.newyear.config;

import com.hardik.newyear.record.SpaceXLaunch;
import com.hardik.newyear.service.MimeEmailService;
import com.hardik.newyear.service.UserService;
import com.hardik.newyear.client.SpaceXClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpaceXScheduler {

    private final SpaceXClient spaceXClient;
    private final MimeEmailService emailService;
    private final UserService userService;

    public SpaceXScheduler(SpaceXClient spaceXClient, MimeEmailService emailService, UserService userService) {
        this.spaceXClient = spaceXClient;
        this.emailService = emailService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 8 */7 * *", zone = "Asia/Kolkata")
    public void sendWeeklyLaunchUpdate() {
        System.out.println("Starting automated SpaceX weekly mailer...");

        SpaceXLaunch latest = spaceXClient.getLatestLaunch();

        userService.findAll().forEach(user -> {
            System.out.println("Sending update to: " + user.getEmail());
            emailService.sendLatestLaunchEmail(user.getEmail(), latest);
        });

        System.out.println("Weekly updates sent successfully.");
    }
}