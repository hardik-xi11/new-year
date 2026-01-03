package com.hardik.newyear.service;

import com.hardik.newyear.record.SpaceXLaunch;
import com.hardik.newyear.record.SpacexQueryResult;
import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MimeEmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MimeEmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);

            mailSender.send(message);
        }
        catch (MessagingException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void sendLatestLaunchEmail(String to, SpaceXLaunch launch) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            StringOutput output = new StringOutput();
            templateEngine.render("latest-launch.jte", launch, output);
            String htmlBody = output.toString();

            helper.setTo(to);
            helper.setSubject("🚀 SpaceX Launch Update: " + launch.name());
            helper.setText(htmlBody,true);

            mailSender.send(message);
        }
        catch (MessagingException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void sendUpcomingLaunchesEmail(String to, SpacexQueryResult<SpaceXLaunch> launch) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            StringOutput output = new StringOutput();
            templateEngine.render("upcoming-launches.jte", launch, output);
            String htmlBody = output.toString();

            helper.setTo(to);
            helper.setSubject("🚀 SpaceX Launch Schedule");
            helper.setText(htmlBody,true);

            mailSender.send(message);
        }
        catch (MessagingException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
