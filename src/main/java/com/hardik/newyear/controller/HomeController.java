package com.hardik.newyear.controller;

import com.hardik.newyear.entity.User;
import com.hardik.newyear.service.MimeEmailService;
import com.hardik.newyear.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final MimeEmailService mimeEmailService;
    private final UserService userService;

    public HomeController(MimeEmailService mimeEmailService, UserService userService) {
        this.mimeEmailService = mimeEmailService;
        this.userService = userService;
    }

    @GetMapping()
    public String hello(){
        return "hello";
    }

    @GetMapping("/auth")
    public String auth(){
        return "secured endpoint";
    }

    @GetMapping("/mime")
    public String mime(String to, String subject, String text){
        mimeEmailService.sendEmail(to, subject, text);
        return "Mail sent succesfully using MIME!";
    }

    @GetMapping("/ott/sent")
    public String ott(){
        return "sent";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registration(@PathVariable String email){
        try{
            userService.createUser(email);
            return ResponseEntity.ok().body("User registered successfully");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
