package com.hardik.newyear.service;

import com.hardik.newyear.client.SpaceXClient;
import com.hardik.newyear.record.SpaceXLaunch;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MailingService {

    private final SpaceXClient spaceXClient;
    private final MimeEmailService emailService;

    public  MailingService(SpaceXClient spaceXClient, MimeEmailService emailService) {
        this.spaceXClient = spaceXClient;
        this.emailService = emailService;
    }

    public String getUserEmail(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    public String sendLatestLaunchMail(){

        SpaceXLaunch latest = spaceXClient.getLatestLaunch();
        emailService.sendLatestLaunchEmail(getUserEmail(), latest);
        return "Notification sent for: " + latest.name();
    }

}
