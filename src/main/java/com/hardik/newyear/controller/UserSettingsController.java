package com.hardik.newyear.controller;

import com.hardik.newyear.entity.User;
import com.hardik.newyear.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class UserSettingsController {

    private final UserRepository userRepository;

    public UserSettingsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/toggle-subscription")
    public String toggleSubscription(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        user.setSubscribed(!user.getSubscribed());
        userRepository.save(user);

        return "redirect:/dashboard";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        userRepository.delete(user);

        return ":/";
    }
}