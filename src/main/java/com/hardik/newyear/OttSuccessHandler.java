package com.hardik.newyear;

import com.hardik.newyear.service.MimeEmailService;
import com.hardik.newyear.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class OttSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OttSuccessHandler.class);
    private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");

    private final MimeEmailService emailService;
    private final UserService userService;

    public OttSuccessHandler(MimeEmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws IOException, ServletException {



        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(request.getRequestURL().toString())
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue());

        String magicLink = builder.toUriString();

        System.out.println("Requst uri method testing: " + request.getRequestURL());
        System.out.println(magicLink);
        System.out.println("Token generated for user: " + oneTimeToken.getUsername());

        String email = getUserEmail(oneTimeToken.getUsername());
        emailService.sendEmail(email, "Your Spring Security One Time Token", "Use the following link to sign in into the application: " + magicLink);
        this.redirectHandler.handle(request, response, oneTimeToken);

    }

    private String getUserEmail(String email) {
        // this would be a database lookup for username
        try {
            userService.createUser(email);
        } catch (IllegalArgumentException e) {
            // User already exists, that's fine
            log.debug("User {} already exists", email);
        }
        log.info("Retrieving email for user: {}", email);
        return email;
    }
}