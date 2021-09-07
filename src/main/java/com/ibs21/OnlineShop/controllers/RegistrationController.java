package com.ibs21.OnlineShop.controllers;

import com.ibs21.OnlineShop.domain.User;
import com.ibs21.OnlineShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user, Model model, @RequestParam("role") boolean role) {

        if (!userService.addUser(user, role)) {
            String message = "Пользователь уже существует";
            model.addAttribute("message", message);
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "Пользователь теперь активен");
        } else {
            model.addAttribute("message", "Код активации не найден");
        }
        return "login";
    }

}
