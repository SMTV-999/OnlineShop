package com.ibs21.OnlineShop.controllers;

import com.ibs21.OnlineShop.domain.Role;
import com.ibs21.OnlineShop.domain.User;
import com.ibs21.OnlineShop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user, Model model) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null) {
            String message = "Пользователь уже существует";
            model.addAttribute("message", message);
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

}
