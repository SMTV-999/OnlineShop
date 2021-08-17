package com.ibs21.OnlineShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {


    /*
    стартовая проверка добавления коммитов
     */
    @GetMapping("/")
    public String start() {
        return "start";
    }
}
