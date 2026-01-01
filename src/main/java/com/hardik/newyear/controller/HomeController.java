package com.hardik.newyear.controller;

import com.hardik.newyear.service.MimeEmailService;
import com.hardik.newyear.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeTokenService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    private final MimeEmailService mimeEmailService;
    private final UserService userService;
    private final OneTimeTokenService oneTimeTokenService;

    public HomeController(MimeEmailService mimeEmailService, UserService userService, OneTimeTokenService oneTimeTokenService) {
        this.mimeEmailService = mimeEmailService;
        this.userService = userService;
        this.oneTimeTokenService = oneTimeTokenService;
    }

    @GetMapping()
    public String hello() {
        return "hello";
    }

    @GetMapping("/auth")
    public String auth() {
        return "secured endpoint";
    }

    @GetMapping("/ott/sent")
    public String ott() {
        return "sent";
    }

    @PostMapping("/register/{email}")
    public ResponseEntity<String> registration(@PathVariable String email) {

        if (userService.findUser(email) != null) {
            oneTimeTokenService.generate(new GenerateOneTimeTokenRequest(email));
            return ResponseEntity.ok().body("User already exists, proceeding to login");
        }

        try {
            userService.createUser(email);
            oneTimeTokenService.generate(new GenerateOneTimeTokenRequest(email));
            return ResponseEntity.ok().body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
